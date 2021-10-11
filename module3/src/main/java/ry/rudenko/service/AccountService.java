package ry.rudenko.service;


import ry.rudenko.model.entity.Account;

public interface AccountService {
  Account findById(Long id);
  void updete(Account account);
}