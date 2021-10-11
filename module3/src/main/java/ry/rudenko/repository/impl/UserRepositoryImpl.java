package ry.rudenko.repository.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.AccountRepository;
import ry.rudenko.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
  private static final Logger log = LoggerFactory.getLogger(OperationRepositoryImpl.class);

  private Session session;

  public UserRepositoryImpl(Session session) throws EmptySessionException {
    if(session.isOpen()){
    this.session = session;
    }else {
      log.error("Session not transferred!");
      throw new EmptySessionException("Session not transferred!");
    }
  }
  public User findByPhone(String phone){
    return session.unwrap(Session.class)
        .bySimpleNaturalId(User.class)
        .load(phone);
  }

}
