package ry.rudenko.yevhenii;


import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ry.rudenko.yevhenii.entity.BaseEntity;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Lesson;
import ry.rudenko.yevhenii.repository.CourseRepository;
import ry.rudenko.yevhenii.util.BuildHibernateSessionFactory;

public class Main {

  public static void main(String[] args) {

//    Configuration configuration = new Configuration().configure();
//
//    try(SessionFactory sessionFactory = configuration.buildSessionFactory()) {
//      EntityManager entityManager = sessionFactory.createEntityManager();
//      try {
//        entityManager.getTransaction().begin();
//        Course course = new Course();
//            course.setName("test Course");
//
////        Player player = new Player();
////        player.setName("Player One");
////        player.setScore(0);
////
////        entityManager.persist(player);
////
////        Guild guild = new Guild("G1");
////        guild.addPlayer(player);
//
//        entityManager.persist(course);
//        entityManager.merge(course);
//
//        entityManager.getTransaction().commit();
//      } catch (Exception e) {
//        entityManager.getTransaction().rollback();
//      }
//    }

    System.out.println("Main.main");
    SessionFactory sessionFactory =  BuildHibernateSessionFactory.buildSessionFactory();
    Session session = sessionFactory.openSession();

    final Transaction transaction = session.getTransaction();
    transaction.begin();
//    Lesson lesson = new Lesson();
//    lesson.setDateTime("2000-09-08 11:22:33");
            Course course = new Course();
            course.setName("test Course");
    session.save(course);
    transaction.commit();
    session.close();
//    transaction.rollback();
    CourseRepository courseRepository = new CourseRepository();
    final List<BaseEntity> all = courseRepository.findAll();
    System.out.println("all.size() = " + all.size());
    all.forEach(p-> {
      System.out.println(p.getId());
      final UUID id = p.getId();
      final Course byId =(Course)courseRepository.findById(id);
      System.out.println("byId.getName() = " + byId.getName());
    });
  }


}













