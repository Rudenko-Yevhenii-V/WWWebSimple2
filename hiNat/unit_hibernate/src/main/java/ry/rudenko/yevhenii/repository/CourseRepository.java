package ry.rudenko.yevhenii.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.BaseEntity;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Theme;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;


public class CourseRepository implements IRepository {
  private Session session;

  @Override
  public List<BaseEntity> findAll() {
    session = BuildHibernateSessionFactory.buildSessionFactory().openSession();
    List<BaseEntity> courses = session.createQuery("From Course").list();
    session.close();
    return courses;
  }

  @Override
  public BaseEntity findById(UUID id) {
    return BuildHibernateSessionFactory.buildSessionFactory().openSession().get(Course.class, id);
  }



  @Override
  public void save(BaseEntity baseEntity) {

  }

  @Override
  public void update(BaseEntity baseEntity) {

  }

  @Override
  public void delete(BaseEntity baseEntity) {

  }

  public CourseRepository() {
  }
}
