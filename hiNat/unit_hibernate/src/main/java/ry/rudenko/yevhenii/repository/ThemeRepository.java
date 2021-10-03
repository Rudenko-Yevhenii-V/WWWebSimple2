package ry.rudenko.yevhenii.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.Theme;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;


public class ThemeRepository {
  private Session session;
//
//  @Override
//  public List<BaseEntity> findAll() {
//    return null;
//  }
//
//  @Override
//  public BaseEntity findById(UUID id) {
//    return BuildHibernateSessionFactory.buildSessionFactory().openSession().get(Theme.class, id);
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

  public ThemeRepository() {
  }
}
