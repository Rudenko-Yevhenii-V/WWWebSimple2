package ry.rudenko.yevhenii;


import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Teacher;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;
import ry.rudenko.yevhenii.util.InitTablesSql;

public class Main {

  public static void main(String[] args) {
    new InitTablesSql().initTablesSql();
//    SessionFactory sessionFactory =  BuildHibernateSessionFactory.buildSessionFactory();
//    Session session = sessionFactory.openSession();
//
//    final Transaction transaction = session.getTransaction();
//    transaction.begin();
//
//
//
//
////    session.save(course);
//    transaction.commit();
//    session.close();
////    transaction.rollback();

  }


}













