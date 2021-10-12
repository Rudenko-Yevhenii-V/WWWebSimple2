package ry.rudenko.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.User;
import ry.rudenko.service.impl.AccountServiceImpl;
import ry.rudenko.JPATest;


class UserRepositoryImplTest extends JPATest {
  private UserRepositoryImpl userRepository;
  private User user;
  String phone = "12";
  @BeforeEach
  void setUp() {
    try {
      userRepository = new UserRepositoryImpl(session);
    } catch (EmptySessionException e) {
      throw  new RuntimeException(e);
    }
  }

@Order(1)
  @Test
  void save() {
    user = User.builder()
        .phone(phone)
        .name("Test NAME")
        .build();
    assertDoesNotThrow(() -> userRepository.save(user));
    final User byPhone = userRepository.findByPhone("12");
    assertAll(
        "test userRepository.save(user)",
        () -> assertNotNull(byPhone.getId()),
        () -> assertEquals(user.getName(), byPhone.getName()),
        () -> assertEquals(user.getPhone(), byPhone.getPhone())
    );
  }
  @Order(2)
  @Test
  void findByPhone() {
    final User user = assertDoesNotThrow(() -> userRepository.findByPhone(phone));
    assertNotNull(user.getName());
  }
}