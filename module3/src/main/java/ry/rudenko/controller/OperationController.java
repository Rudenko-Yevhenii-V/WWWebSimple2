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
      System.out.println("accountsByUser.size() = " + accountsByUser.size());
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
      System.out.println("Enter amount of operation: ");
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
    } catch (EmptySessionException | IOException e) {
      log.error("Session did not transferred! ", e);
      throw new RuntimeException(e);
    }

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
    System.out.println("mandatory spending, enter 1");
    System.out.println("ad hoc expenses   , enter 2");
    System.out.println("other             , enter 3");
    switch (reader.readLine()) {
      case "1":
        return "mandatory spending";
      case "2":
        return "ad hoc expenses";
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
