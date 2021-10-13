package ry.rudenko.repository.impl;


import java.util.function.Supplier;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

  private static final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);

  private Session session;

  public UserRepositoryImpl(Supplier<Session> sessionSupplier) throws EmptySessionException {
    this.session = sessionSupplier.get();
    if (session.isOpen()) {
      log.info("Is open session? {}",session.isOpen());
    } else {
      log.error("Session not transferred!");
      throw new EmptySessionException("Session not transferred!");
    }
  }

  public User findByPhone(String phone) {
    return session.unwrap(Session.class)
        .bySimpleNaturalId(User.class)
        .load(phone);
  }

  @Override
  public User save(User user) {
    session.save(user);
    return user;
  }

}
