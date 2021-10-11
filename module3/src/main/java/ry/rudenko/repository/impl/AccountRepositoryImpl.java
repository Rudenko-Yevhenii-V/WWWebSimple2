package ry.rudenko.repository.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.repository.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {
  private static final Logger log = LoggerFactory.getLogger(OperationRepositoryImpl.class);

  private Session session;

  public AccountRepositoryImpl(Session session) throws EmptySessionException {
    if(session.isOpen()){
    this.session = session;
    }else {
      log.error("Session not transferred!");
      throw new EmptySessionException("Session not transferred!");
    }
  }
  @Override
  public Account findById(Long id) {
    return session.get(Account.class, id);
  }
}
