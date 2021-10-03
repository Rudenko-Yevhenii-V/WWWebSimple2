package ry.rudenko.yevhenii.repository;


import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;

public class StudentRepository {
  private Session session;


  public StudentRepository() {
  }
}
