package ry.rudenko;


import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.controller.OperationController;
import ry.rudenko.exception.EmptyArgsException;
import ry.rudenko.init.InitTablesSql;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Category;
import ry.rudenko.model.entity.ExpenseCategory;
import ry.rudenko.model.entity.IncomeCategory;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.OperationRepository;
import ry.rudenko.repository.impl.OperationRepositoryImpl;
import ry.rudenko.service.OperationService;
import ry.rudenko.service.impl.OperationServiceImpl;
import ry.rudenko.util.BuildHibernateSessionFactory;

public class Main {

  private static String dbName = null;
  private static String dbPass = null;
  private static String phone = null;

  public static String getDbName() {
    return dbName;
  }

  public static String getDbPass() {
    return dbPass;
  }

  public static String getPhone() {
    return phone;
  }


  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    if (args.length < 3) {
      try {
        throw new EmptyArgsException("Please pass the data!");
      } catch (EmptyArgsException e) {
        log.error("Did not receive username and password for DB or user's phone number ! ", e);
        throw new RuntimeException(e);
      }
    }
    dbName = args[0];
    dbPass = args[1];
    phone = args[2];
//        new InitTablesSql().initTablesSql();
    new OperationController().createOperation(phone);

  }
}
