package ry.rudenko.util;


import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;
import ry.rudenko.Main;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Category;
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
      configuration.setProperty("hibernate.connection.username", Main.getDbName());
      configuration.setProperty("hibernate.connection.password", Main.getDbPass());
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      final StandardServiceRegistry serviceRegistry = builder.configure().build();
      Metadata metadata = new MetadataSources(serviceRegistry)
          .addAnnotatedClass(Account.class)
          .addAnnotatedClass(Category.class)
          .addAnnotatedClass(IncomeCategory.class)
          .addAnnotatedClass(ExpenseCategory.class)
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



















