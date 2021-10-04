package ry.rudenko.yevhenii.repository;


import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;

public class StudentRepository {
  private SessionFactory sessionFactory = BuildHibernateSessionFactory.buildSessionFactory();
  private Session session = sessionFactory.openSession();


  public StudentRepository() {
  }
  public Student findById(Long id){
    return session.get(Student.class, id);
  }

}
