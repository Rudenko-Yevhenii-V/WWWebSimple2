package ry.rudenko.yevhenii.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Group;
import ry.rudenko.yevhenii.entity.Teacher;
import ry.rudenko.yevhenii.entity.Lesson;
import ry.rudenko.yevhenii.entity.Mark;
import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.entity.Theme;

public class BuildHibernateSessionFactory {

  public BuildHibernateSessionFactory() {
  }

  public static SessionFactory buildSessionFactory(){
    try{
      StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

          .configure().build();
//        .configure("hibernate.cfg.xml").build()){
      Metadata metadata = new MetadataSources(serviceRegistry)
          .addAnnotatedClass(Course.class)
          .addAnnotatedClass(Group.class)
          .addAnnotatedClass(Lesson.class)
          .addAnnotatedClass(Teacher.class)
          .addAnnotatedClass(Mark.class)
          .addAnnotatedClass(Theme.class)
          .addAnnotatedClass(Student.class)
          .getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    }catch (Throwable e){
//      log  Initial SessionFactory creation failted.
      throw  new ExceptionInInitializerError(e);
    }
  }

}



















