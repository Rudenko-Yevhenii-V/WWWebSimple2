package ry.rudenko.service.impl;


import java.util.UUID;
import ry.rudenko.model.entity.Account;
import ry.rudenko.repository.AccountRepository;
import ry.rudenko.service.AccountService;

public record AccountServiceImpl(
    AccountRepository accountRepository) implements AccountService {

  @Override
  public Account findById(UUID id) {
    return accountRepository.findById(id);
  }

  @Override
  public void updete(Account account) {
    accountRepository.update(account);
  }

}
