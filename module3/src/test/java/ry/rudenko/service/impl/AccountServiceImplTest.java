package ry.rudenko.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ry.rudenko.JPATest;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.impl.AccountRepositoryImpl;
import ry.rudenko.repository.impl.UserRepositoryImpl;


class AccountServiceImplTest extends JPATest {

  private AccountServiceImpl subjectAccount;
  private Account accountForUpdate;
  private UserRepositoryImpl userRepository;

  @BeforeEach
  void setUp() {
    try {
      subjectAccount = new AccountServiceImpl(new AccountRepositoryImpl(session));
      userRepository = new UserRepositoryImpl(session);
    } catch (EmptySessionException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @DisplayName("is update Account")
  void updete() {
    User user = User.builder()
        .phone("123")
        .name("Test NAME")
        .build();
    assertDoesNotThrow(() -> userRepository.save(user));
    User user2 = User.builder()
        .phone("12w")
        .name("Test NAMEbe")
        .build();
    assertDoesNotThrow(() -> userRepository.save(user2));
    accountForUpdate = Account.builder()
        .user(user2)
        .balance(new BigInteger("2000"))
        .build();
    session.getTransaction().begin();
    session.save(accountForUpdate);
    session.getTransaction().commit();
    session.getTransaction().begin();
    accountForUpdate.setBalance(new BigInteger("333333333"));
    Account account = assertDoesNotThrow(() -> subjectAccount.updete(accountForUpdate));
    session.getTransaction().commit();
    assertAll(
        "test Account.update(account)",
        () -> assertEquals(accountForUpdate.getBalance(), account.getBalance()),
        () -> assertEquals(accountForUpdate.getId(), account.getId()),
        () -> assertEquals(accountForUpdate.getUser(), account.getUser())
    );
  }
}