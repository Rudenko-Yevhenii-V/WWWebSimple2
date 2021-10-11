package ry.rudenko.service.impl;


import java.math.BigInteger;
import java.time.Instant;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Category;
import ry.rudenko.model.entity.ExpenseCategory;
import ry.rudenko.model.entity.IncomeCategory;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.repository.impl.AccountRepositoryImpl;
import ry.rudenko.repository.impl.CategoryRepositoryImpl;
import ry.rudenko.repository.impl.OperationRepositoryImpl;
import ry.rudenko.service.OperationService;

public record OperationServiceImpl(
    OperationRepositoryImpl operationRepository) implements
    OperationService {

  private static final Logger log = LoggerFactory.getLogger(OperationServiceImpl.class);

  @Override
  public Operation findById(UUID id) {
    return null;
  }

  @Override
  public Operation addOperation(UUID idAccount, String type, BigInteger amount,
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

  private Account addAccount(UUID idAccount, BigInteger amount, boolean income)
      throws EmptySessionException {
    final Account account = new AccountServiceImpl(
        new AccountRepositoryImpl(operationRepository.getSession()))
        .findById(idAccount);
    BigInteger balance = account.getBalance();
    final BigInteger bigInteger;
    if (income) {
      bigInteger = balance.add(amount);
    } else {
      bigInteger = balance.subtract(amount);
    }
    account.setBalance(bigInteger);
    return account;
  }

  private Category addCatrgory(Boolean income, String actionType) throws EmptySessionException {
    if (income) {
      return new CategoryRepositoryImpl(operationRepository.getSession()).save(
          new IncomeCategory(actionType));
    } else {
      return new CategoryRepositoryImpl(operationRepository.getSession()).save(
          new ExpenseCategory(actionType));
    }
  }
}
