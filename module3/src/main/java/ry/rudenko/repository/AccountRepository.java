package ry.rudenko.repository;


import java.util.List;
import java.util.UUID;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.User;

public interface AccountRepository {
  Account findById(UUID id);
  Account update(Account account);
  List<Account> findByUserId(User user);
}
