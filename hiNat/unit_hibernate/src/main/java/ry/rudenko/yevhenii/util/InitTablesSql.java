package ry.rudenko.yevhenii.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ry.rudenko.yevhenii.entity.Course;
import ry.rudenko.yevhenii.entity.Group;
import ry.rudenko.yevhenii.entity.Lesson;
import ry.rudenko.yevhenii.entity.Mark;
import ry.rudenko.yevhenii.entity.Student;
import ry.rudenko.yevhenii.entity.Teacher;
import ry.rudenko.yevhenii.entity.Theme;

public class InitTablesSql {

  public void initTablesSql() {
    SessionFactory sessionFactory = BuildHibernateSessionFactory.buildSessionFactory();
    Session session = sessionFactory.openSession();
    final Transaction transaction = session.getTransaction();
    transaction.begin();
    try {
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
      Group NIX7 = Group.builder()
          .name("NIX7")
          .build();
      session.save(NIX7);
      Course courseJavaCore = Course.builder()
          .name("JAVA core")
          .teacher(Egor)
          .group(NIX7)
          .build();
      session.save(courseJavaCore);
      Course courseJavaWeb = Course.builder()
          .name("JAVA web")
          .teacher(Misha)
          .group(NIX7)
          .build();
      session.save(courseJavaWeb);

      Theme sql = Theme.builder()
          .name("sql")
          .course(courseJavaWeb)
          .build();
      session.save(sql);
      Theme jdbc = Theme.builder()
          .name("jdbc")
          .course(courseJavaWeb)
          .build();
      session.save(jdbc);
      Theme hibernate = Theme.builder()
          .name("hibernate")
          .course(courseJavaWeb)
          .build();
      session.save(hibernate);
      Lesson lesson1 = Lesson.builder()
          .dateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
              .parse("2021-10-02 11:00:00").toInstant())
          .group(NIX7)
          .teacher(Misha)
          .theme(sql)
          .build();
      session.save(lesson1);
      Lesson lesson2 = Lesson.builder()
          .dateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
              .parse("2021-10-05 19:00:00").toInstant())
          .group(NIX7)
          .teacher(Misha)
          .theme(jdbc)
          .build();
      session.save(lesson2);
      Lesson lesson3 = Lesson.builder()
          .dateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
              .parse("2021-10-09 11:00:00").toInstant())
          .group(NIX7)
          .teacher(Misha)
          .theme(hibernate)
          .build();
      session.save(lesson3);
      Set<Lesson> lessons = new HashSet<>();
      lessons.add(lesson1);
      lessons.add(lesson2);
      lessons.add(lesson3);
      Student rudenko = Student.builder()
          .firstName("Yevhenii")
          .lastName("Rudenko")
          .phone(88l)
          .group(NIX7)
          .lessons(lessons)
          .build();
      session.save(rudenko);
      Student fancy = Student.builder()
          .firstName("Tanya")
          .phone(81l)
          .lastName("Fancy")
          .group(NIX7)
          .lessons(lessons)
          .build();
      session.save(fancy);
      Student kvitka = Student.builder()
          .firstName("alla")
          .lastName("kvitka")
          .phone(99L)
          .group(NIX7)
          .lessons(lessons)
          .build();
      session.save(kvitka);
      Student nast = Student.builder()
          .firstName("Liubov")
          .lastName("Nast")
          .phone(78L)
          .group(NIX7)
          .lessons(lessons)
          .build();
      session.save(nast);
      Student kilimnik = Student.builder()
          .firstName("Diana")
          .lastName("Kilimnik")
          .phone(67L)
          .group(NIX7)
          .lessons(lessons)
          .build();
      session.save(kilimnik);
      Mark mark1 = Mark.builder()
          .mark(1)
          .student(rudenko)
          .lesson(lesson1)
          .group(NIX7)
          .build();
      session.save(mark1);
      transaction.commit();
      session.close();
    } catch (
        ParseException e) {
      transaction.rollback();
      e.printStackTrace();
    }
  }
}
