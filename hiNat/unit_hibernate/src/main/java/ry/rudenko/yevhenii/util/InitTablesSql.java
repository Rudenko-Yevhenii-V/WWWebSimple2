package ry.rudenko.yevhenii.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Group;
import ry.rudenko.yevhenii.entity.Teacher;

public class InitTablesSql {
 public void initTablesSql(){
   SessionFactory sessionFactory =  BuildHibernateSessionFactory.buildSessionFactory();
   Session session = sessionFactory.openSession();

   final Transaction transaction = session.getTransaction();
   transaction.begin();
   Teacher Egor = Teacher.builder()
       .firstName("Egor")
       .lastName("LastName")
       .build();
   session.save(Egor);
   Teacher Misha = Teacher.builder()
       .firstName("Misha")
       .lastName("LastName")
       .build();
   session.save(Misha);
   Course courseJavaCore = Course.builder()
       .name("JAVA core")
       .teacher(Egor)
       .build();
   session.save(courseJavaCore);
   Course courseJavaWeb = Course.builder()
       .name("JAVA web")
       .teacher(Misha)
       .build();
   session.save(courseJavaWeb);
   Group NIX7 = Group.builder()
       .name("JAVA web")
       .course(courseJavaCore)
       .course(courseJavaWeb)
       .build();
   session.save(courseJavaWeb);


//    session.save(course);
   transaction.commit();
   session.close();
//    transaction.rollback();
 }
}
