package ry.rudenko.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.impl.AccountRepositoryImpl;
import ry.rudenko.repository.impl.OperationRepositoryImpl;
import ry.rudenko.repository.impl.UserRepositoryImpl;
import ry.rudenko.service.impl.OperationServiceImpl;
import ry.rudenko.util.BuildHibernateSessionFactory;

public class OperationController {

  private static final Logger log = LoggerFactory.getLogger(OperationController.class);
  final Session session = BuildHibernateSessionFactory.buildSessionFactory().openSession();

  public void createOperation(String phone) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      final User user = new UserRepositoryImpl(session).findByPhone(phone);
      System.out.println(" Hello " + user.getName());
      System.out.println("select your account:");
      Account selectedAccount = null;
      final List<Account> accountsByUser = new AccountRepositoryImpl(session).findByUserId(user);
      for (Account account : accountsByUser) {
        System.out.println(
            "account number: " + account.getId() + " =>  balance=" + account.getBalance());
      }
      System.out.println("Enter number of account:");
      String idAccount = reader.readLine();
      for (Account account : accountsByUser) {
        if (String.valueOf(account.getId()).equals(idAccount)) {
          selectedAccount = account;
        }
      }
      System.out.println("If you going to do operation with account, enter 1");
      System.out.println("If you going to print history from account, enter 2");
      System.out.println("If you going to EXIT, enter ANY");
      switch (reader.readLine()){
        case "1": System.out.println("Enter amount of operation: ");
        break;
        case "2": {

          new OutputCsvController().createCsv(selectedAccount.getId(), getStart(reader), getEnd(reader));
          System.out.println("Good bue!");
          Thread.sleep(2000);
          System.exit(0);
        };
        break;
        default:{
          System.out.println("Wrong choice... Good bue!");
          Thread.sleep(2000);
          System.exit(0);
        }
      }

      final BigInteger amount = new BigInteger(reader.readLine());
      assert selectedAccount != null;
      final Operation operation = new OperationServiceImpl(
          new OperationRepositoryImpl(session)).addOperation(
          selectedAccount.getId()
          , choiceTypeOfOperation(reader)
          , amount
          , choiceIncome(reader)
          , enterActionType(reader)
      );
    } catch (EmptySessionException | IOException | InterruptedException e) {
      log.error("Session did not transferred! ", e);
      throw new RuntimeException(e);
    }

  }

  private String getEnd(BufferedReader reader) throws IOException {
    System.out.println("ENTER time to (yyyy-MM-dd HH:mm:ss):  ");
    return reader.readLine();
  }

  private String getStart(BufferedReader reader) throws IOException {
    System.out.println("ENTER time from (yyyy-MM-dd HH:mm:ss):  ");
    return reader.readLine();
  }

  private String enterActionType(BufferedReader reader) throws IOException {
    System.out.println("ENTER action type (for example - buy coffee): ");
    return reader.readLine();
  }

  private Boolean choiceIncome(BufferedReader reader) throws IOException {
    System.out.println("profit or waste? \nIf profit enter 1 else waste");
    return reader.readLine().equals("1");
  }

  private String choiceTypeOfOperation(BufferedReader reader) throws IOException {
    System.out.println("Choice operation's type:");
    System.out.println("mandatory     , enter 1");
    System.out.println("occasionally  , enter 2");
    System.out.println("other         , enter 3");
    switch (reader.readLine()) {
      case "1":
        return "mandatory ";
      case "2":
        return "occasionally";
      case "3":
        return "other";
      default: {
        System.out.println("wrong choice, try again...");
        choiceTypeOfOperation(reader);
      }
    }
    return "";
  }

}
