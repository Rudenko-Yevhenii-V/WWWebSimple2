package ry.rudenko.yevhenii.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.Group;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;

public class GroupRepository {
  private Session session;


  public GroupRepository() {
  }
}
