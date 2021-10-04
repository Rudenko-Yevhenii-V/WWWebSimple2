package ry.rudenko.yevhenii;


import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.entity.Teacher;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;
import ry.rudenko.yevhenii.util.InitTablesSql;

public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory =  BuildHibernateSessionFactory.buildSessionFactory();
    Session session = sessionFactory.openSession();

    final Student student = session.get(Student.class, 1l);
    System.out.println("student.getFirstName() = " + student.getFirstName());
    System.out.println("student.getLastName() = " + student.getLastName());


    session.close();

  }


}













