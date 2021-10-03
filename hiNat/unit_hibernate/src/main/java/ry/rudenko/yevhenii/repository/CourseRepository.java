package ry.rudenko.yevhenii.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;


public class CourseRepository {
  private Session session;



  public CourseRepository() {
  }
}
