package ry.rudenko.service.impl;


import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.model.entity.Account;
import ry.rudenko.repository.AccountRepository;
import ry.rudenko.service.AccountService;

public record AccountServiceImpl(
    AccountRepository accountRepository) implements AccountService {
  private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

  @Override
  public Account findById(UUID id) {
    return accountRepository.findById(id);
  }

  @Override
  public Account updete(Account account) {
    return accountRepository.update(account);
  }

}
