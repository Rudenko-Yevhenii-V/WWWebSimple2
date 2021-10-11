package ry.rudenko.service;


import java.util.UUID;
import ry.rudenko.model.entity.Account;

public interface AccountService {

  Account findById(UUID id);

  void updete(Account account);
}