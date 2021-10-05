package ry.rudenko.yevhenii.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ry.rudenko.yevhenii.entity.Student;

public class StudentRepository {
  private Session session;

  public StudentRepository(SessionFactory sessionFactory) {
    this.session= sessionFactory.openSession();
  }
  public Student findById(Long id){
    return session.get(Student.class, id);
  }
//  with NaturalId
//  public Student findByPhone(Long phone){
//    return session.unwrap(Session.class)
//        .bySimpleNaturalId(Student.class)
//        .load(phone);
//  }

  public Student findByPhone(Long phone){
    final Query<Student> query = session.createQuery("SELECT s\n"
        + "FROM Student s\n"
        + "WHERE s.phone = 88", Student.class);
    final Student singleResult = query.getSingleResult();
    return singleResult;
  }


}
