package ry.rudenko.yevhenii.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BuildHibernateSessionFactory {
  private static SessionFactory buildSessionFactory(){
    try(StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
        .configure("hibernate.cfg.xml").build()){
      Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    }catch (Throwable e){
//      log
      throw  new ExceptionInInitializerError(e);
    }
  }

}



















