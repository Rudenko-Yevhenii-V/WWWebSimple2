package ry.rudenko;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class JPATest {

  public Session session;

  static SessionFactory sessionFactory;


  @BeforeAll
  static void setupFactories() {
    Configuration config = new Configuration();
    config.configure();
    sessionFactory = config.buildSessionFactory();

  }

  @BeforeEach
  void setupSession() {
    session = sessionFactory.openSession();
  }

  @AfterEach
  void closeSession() {
    session.close();
  }

  @AfterAll
  static void closeFactories() {
    sessionFactory.close();
  }
}