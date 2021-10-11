package ry.rudenko.service.impl;


import ry.rudenko.model.entity.Account;
import ry.rudenko.repository.AccountRepository;
import ry.rudenko.service.AccountService;

public class AccountServiceImpl implements AccountService {
  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public Account findById(Long id) {
    return accountRepository.findById(id);
  }
}
