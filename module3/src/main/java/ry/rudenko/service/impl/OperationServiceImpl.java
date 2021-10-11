package ry.rudenko.service.impl;


import java.math.BigInteger;
import java.time.Instant;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.controller.OperationController;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Category;
import ry.rudenko.model.entity.ExpenseCategory;
import ry.rudenko.model.entity.IncomeCategory;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.impl.AccountRepositoryImpl;
import ry.rudenko.repository.impl.CategoryRepositoryImpl;
import ry.rudenko.repository.impl.OperationRepositoryImpl;
import ry.rudenko.service.OperationService;

public record OperationServiceImpl(
    OperationRepositoryImpl operationRepository) implements
    OperationService {
  private static final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);

  @Override
  public Operation findById(Long id) {
    return null;
  }

  @Override
  public Operation addOperation(Long idAccount, String type, BigInteger amount,
      Boolean income, String actionType) {
    Operation operation;
    try {
      final Category category = addCatrgory(income, actionType);
      operation = Operation.builder()
          .date(Instant.now())
          .type(type)
          .amount(amount)
          .category(category)
          .account(addAccount(idAccount, amount, category instanceof IncomeCategory))
          .build();
    } catch (EmptySessionException e) {
      log.error("Session did not transferred! ", e);
      throw new RuntimeException(e);
    }
    return operationRepository.addOperation(operation);
  }

  private Account addAccount(Long idAccount, BigInteger amount, boolean income) throws EmptySessionException {
    System.out.println("income = " + income);
    System.out.println("amount = " + amount);
    final Account account = new AccountServiceImpl(
        new AccountRepositoryImpl(operationRepository.getSession()))
        .findById(idAccount);
    System.out.println("account.getId()tttt = " + account.getId());
    System.out.println("account.getBalance() = " + account.getBalance());
    BigInteger balance = account.getBalance();
    final BigInteger bigInteger;
    if (income){
      bigInteger = balance.add(amount);
    }else {
       bigInteger = balance.subtract(amount);
    }
    System.out.println(bigInteger);
    account.setBalance(bigInteger);
    System.out.println("account.getId() = " + account.getId());
    System.out.println("account.getBalance() = " + account.getBalance());
    System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKK");
    return account;
  }

  private Category addCatrgory(Boolean income, String actionType) throws EmptySessionException {
    if (income){
      return  new CategoryRepositoryImpl(operationRepository.getSession()).save(
          new IncomeCategory(actionType));
    }else {
      return  new CategoryRepositoryImpl(operationRepository.getSession()).save(
          new ExpenseCategory(actionType));
    }
  }
}
