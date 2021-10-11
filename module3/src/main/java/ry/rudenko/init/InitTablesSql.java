package ry.rudenko.init;


import java.math.BigInteger;
import java.time.Instant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Category;
import ry.rudenko.model.entity.ExpenseCategory;
import ry.rudenko.model.entity.IncomeCategory;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;
import ry.rudenko.util.BuildHibernateSessionFactory;


public class InitTablesSql {
  private static final Logger log = LoggerFactory.getLogger(InitTablesSql.class);

  public void initTablesSql() {
    SessionFactory sessionFactory = BuildHibernateSessionFactory.buildSessionFactory();
    Session session = sessionFactory.openSession();
    final Transaction transaction = session.getTransaction();
    transaction.begin();
//    try {


//    ExpenseCategory expenseCategory = new ExpenseCategory();
//    expenseCategory.setActionType("buy some21");
//      session.save(expenseCategory);
//
//    IncomeCategory incomeCategory = new IncomeCategory();
//    incomeCategory.setActionType("sold some21");
//    session.save(incomeCategory);
//
//    ExpenseCategory expenseCategory1 = new ExpenseCategory();
//    expenseCategory1.setActionType("buy fgf1");
//    session.save(expenseCategory1);
//
//    IncomeCategory incomeCategory1 = new IncomeCategory();
//    incomeCategory1.setActionType("sold rtr1");
//    session.save(incomeCategory1);
    transaction.commit();
    transaction.begin();


    User user1 = User.builder()
        .phone("+380997548625")
        .name("user1")
        .build();
    session.save(user1);
    User user2 = User.builder()
        .phone("12")
        .name("user2")
        .build();
    session.save(user2);
    Account account1 = Account.builder()
        .balance(new BigInteger("1000"))
        .user(user1)
        .build();
    session.save(account1);

    Account account2 = Account.builder()
        .balance(new BigInteger("2000"))
        .user(user1)
        .build();
    session.save(account2);

    Account account3 = Account.builder()
        .balance(new BigInteger("3000"))
        .user(user2)
        .build();
    session.save(account3);


//
//    Operation operation1 = Operation.builder()
//        .date(Instant.now())
//        .type("mandatory spending")
//        .amount(new BigInteger("222222"))
//        .category(incomeCategory1)
////        .expenseCategory(expenseCategory1)
//        .account(account1)
//        .build();
//    session.save(operation1);
//
//    Operation operation2 = Operation.builder()
//        .date(Instant.now())
//        .type("ad hoc expenses")
//        .amount(new BigInteger("2"))
//        .expenseCategory(expenseCategory1)
//        .account(account1)
//        .build();
//    session.save(operation2);
//
//    Operation operation3 = Operation.builder()
//        .date(Instant.now())
//        .type("other")
//        .amount(new BigInteger("2"))
//        .expenseCategory(expenseCategory)
//        .account(account1)
//        .build();
//    session.save(operation3);


    transaction.commit();
      session.close();
//    } catch (ParseException e) {
//      transaction.rollback();
//      log.error("Transaction rollback", e);
//      throw new RuntimeException("Transaction rollback");
//    }
  }
}
