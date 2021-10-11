package ry.rudenko.service.impl;


import java.math.BigInteger;
import java.time.Instant;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.controller.OperationController;
import ry.rudenko.exception.EmptySessionException;
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
  public Operation addOperation(String phone, String type, BigInteger amount) {
    Operation operation;
    try {
      operation = Operation.builder()
          .date(Instant.now())
          .type(type)
          .amount(amount)
          .category(
              new CategoryRepositoryImpl(operationRepository.getSession()).save(
                  new IncomeCategory("sold some2"))

          )
          .account(
              new AccountServiceImpl(new AccountRepositoryImpl(operationRepository.getSession()))
                  .findById(
  /////////
            1l
  /////////

              )
          )
          .build();
    } catch (EmptySessionException e) {
      log.error("Session did not transferred! ", e);
      throw new RuntimeException(e);
    }
    return operationRepository.addOperation(operation);
  }
}
