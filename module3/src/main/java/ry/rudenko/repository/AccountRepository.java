package ry.rudenko.repository;


import java.util.List;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.model.entity.User;

public interface AccountRepository {
  Account findById(Long id);
  Account update(Account account);
  List<Account> findByUserId(User user);
}
