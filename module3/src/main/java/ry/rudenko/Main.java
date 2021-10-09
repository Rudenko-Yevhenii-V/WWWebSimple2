package ry.rudenko;


import java.text.ParseException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.model.entity.User;
import ry.rudenko.util.BuildHibernateSessionFactory;

public class Main {
  private static final Logger log = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) {
    log.info("Main info");
    log.warn("Main warn");
    log.error("Main error");
    System.out.println("Main.main");
    final SessionFactory sessionFactory = BuildHibernateSessionFactory.buildSessionFactory();
    final Session session = sessionFactory.openSession();
    final Transaction transaction = session.getTransaction();
//    transaction.begin();
//    User user1 = User.builder()
//        .name("user 1")
//        .build();
//    session.save(user1);
//    User user2 = User.builder()
//        .name("user 2")
//        .build();
//    session.save(user2);
//    User user3 = User.builder()
//        .name("user 3")
//        .build();
//    session.save(user3);
//    User user4 = User.builder()
//        .name("user 4")
//        .build();
//    session.save(user4);
//
//    transaction.commit();
//    session.close();

    final User user = session.get(User.class, 1L);
    System.out.println("user.getName() = " + user.getName());


  }


}


//Задача: построить консольное приложение для управления личными финансами.
//    Сущности бизнес-домена:
//    Пользователь, Счет, Операция, Категория Доходов, Категория Расходов.

//    У каждого пользователя может быть несколько счетов, и он может проводить операции по
//    одному из них.
//    Операция может иметь в себя категорию доходов, если операция приносит прибыль, либо
//    категорию расходов в противном случае.
//    Операции с нулевым оборотом недопустимы.
//    Операции без категории или с невалидной для типа операции (доход/расход) категорией
//    недопустимы.
//    Приложение должно хранить информацию о всех вышеописанных сущностях в БД, а также
//    хранить лог действий пользователя в локальном файле.
//    Необходимо реализовать два режима работы приложения:
//    1) Добавление новой операции существующим пользователем
//    2) Экспорт выписки по счету в csv формате (должен создавать выходной файл .csv).
//    Выписка должна включать список операций за определенный период.
//    Первый режим реализовать при помощи JPA/Hibernate, второй - при помощи JDBC
//    В обоих случаях, пользователь должен передать в приложение, посредством аргументов
//    командной строки или переменных среды: идентификатор пользователя (его id, email,
//    или phone number, на выбор разработчика), а также имя пользователя и пароль для
//    подключения к БД.
//    Для дат в БД использовать тип timestamp, в java - Instant. Текстовый формат дат -
//    ISO_LOCAL_DATE_TIME из java.time.
//    Данные для тестирования ввести в БД можно вручную (пользователи, счета и категории
//    операций)