package ry.rudenko.yevhenii.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ry.rudenko.yevhenii.entity.Student;

public class StudentRepository {
  private Session session;

  public StudentRepository(SessionFactory sessionFactory) {
    this.session= sessionFactory.openSession();
  }
  public Student findById(Long id){
    return session.get(Student.class, id);
  }

}
