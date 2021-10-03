package ry.rudenko.yevhenii.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.Teacher;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;


public class TeacherRepository {
  private Session session;

//  @Override
//  public List<BaseEntity> findAll() {
//    return null;
//  }
//
//  @Override
//  public BaseEntity findById(UUID id) {
//    return BuildHibernateSessionFactory.buildSessionFactory().openSession().get(Teacher.class, id);
//  }
//
//  @Override
//  public void save(BaseEntity baseEntity) {
//
//  }
//
//  @Override
//  public void update(BaseEntity baseEntity) {
//
//  }
//
//  @Override
//  public void delete(BaseEntity baseEntity) {
//
//  }

  public TeacherRepository() {
  }
}
