package ry.rudenko.util;


import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.ExpenseCategory;
import ry.rudenko.model.entity.IncomeCategory;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;


public class BuildHibernateSessionFactory {
  private static final Logger log = LoggerFactory.logger(BuildHibernateSessionFactory.class);
  public BuildHibernateSessionFactory() {
  }
  public static SessionFactory buildSessionFactory(){
    try{
      Configuration configuration = new Configuration();
      configuration.setProperty("connection.driver_class","org.postgresql.Driver");
      configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5444/db_HW_1");
      configuration.setProperty("hibernate.connection.username", "rttr");
      configuration.setProperty("hibernate.connection.password", "root");
      configuration.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
      configuration.setProperty("hibernate.hbm2ddl.auto", "update");
      configuration.setProperty("show_sql", "true");
      configuration.setProperty("format_sql", "true");
      configuration.setProperty(" hibernate.connection.pool_size", "1");
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      final StandardServiceRegistry serviceRegistry = builder.configure().build();
      Metadata metadata = new MetadataSources(serviceRegistry)
          .addAnnotatedClass(Account.class)
          .addAnnotatedClass(ExpenseCategory.class)
          .addAnnotatedClass(IncomeCategory.class)
          .addAnnotatedClass(Operation.class)
          .addAnnotatedClass(User.class)
          .getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    }catch (Throwable e){
      log.error("SessionFactory creation failed.", e);
      throw  new ExceptionInInitializerError(e);
    }
  }
}



















