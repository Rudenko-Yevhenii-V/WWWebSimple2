package ry.rudenko.yevhenii.repository;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.Lesson;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;


public class LessonRepository {
  private Session session;



  public LessonRepository() {
  }
}
