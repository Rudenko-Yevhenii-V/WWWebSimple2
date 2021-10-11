package ry.rudenko.repository;


import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Operation;

public interface AccountRepository {
  Account findById(Long id);
}
