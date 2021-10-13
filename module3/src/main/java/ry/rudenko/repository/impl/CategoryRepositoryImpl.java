package ry.rudenko.repository.impl;


import java.util.function.Supplier;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Category;
import ry.rudenko.repository.CategoryRepository;

public class CategoryRepositoryImpl implements CategoryRepository {

  private static final Logger log = LoggerFactory.getLogger(CategoryRepositoryImpl.class);

  private final Session session;

  public CategoryRepositoryImpl(Supplier<Session> sessionSupplier) throws EmptySessionException {
    this.session = sessionSupplier.get();
    if (session.isOpen()) {
      log.info("Is open session? {}",session.isOpen());
    } else {
      log.error("Session not transferred!");
      throw new EmptySessionException("Session not transferred!");
    }
  }

  @Override
  public Category save(Category category) {
    final Query<Category> query = session.createQuery(
            "SELECT c from Category as c where c.actionType like :category", Category.class)
        .setParameter("category", category.getActionType());

    if (query.getResultList().size() < 1) {
      session.save(category);
      return category;
    } else {
      category = query.getSingleResult();
    }
    return category;
  }
}
