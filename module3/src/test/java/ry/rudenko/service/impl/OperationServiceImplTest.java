package ry.rudenko.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ry.rudenko.JPATest;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.impl.OperationRepositoryImpl;
import ry.rudenko.repository.impl.UserRepositoryImpl;

class OperationServiceImplTest  extends JPATest {

  private OperationServiceImpl operationService;
  private Account account;
  User user2;
  @BeforeEach
  void setUp() {
    try {
      operationService = new OperationServiceImpl(new OperationRepositoryImpl(session));

    } catch (EmptySessionException e) {
      throw new RuntimeException(e);
    }
  }


  @Test
  void addOperation() {
    user2 = User.builder()
        .phone("12")
        .name("Test NAME")
        .build();
    assertDoesNotThrow(() -> new UserRepositoryImpl(session).save(user2));
    account = Account.builder()
        .user(user2)
        .balance(new BigInteger("2000"))
        .build();
    session.getTransaction().begin();
    session.save(account);
    session.getTransaction().commit();
    final Operation operation = operationService.addOperation(
        account.getId()
        , "other"
        , new BigInteger("400")
        , true
        , "buy coffee"
    );
    final Operation operation1 = assertDoesNotThrow(
        () -> operationService.findById(operation.getId()));
    assertAll(
        "test Operation addOperation",
        () -> assertEquals(operation1.getCategory(), operation.getCategory()),
        () -> assertEquals(operation1.getId(), operation.getId()),
        () -> assertEquals(operation1.getDate(), operation.getDate())
    );
  }

  @Test
  void operationRepository() {
  }
}