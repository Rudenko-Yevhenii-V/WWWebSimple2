package ry.rudenko.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.repository.OperationRepository;
import ry.rudenko.service.AccountService;
import ry.rudenko.service.impl.AccountServiceImpl;

public class OperationRepositoryImpl implements OperationRepository {

  private static final Logger log = LoggerFactory.getLogger(OperationRepositoryImpl.class);

  private Session session;
  final Transaction transaction;

  public OperationRepositoryImpl(Session session) throws EmptySessionException {
    if(session.isOpen()){
      this.session = session;
      transaction = session.getTransaction();
    }else {
      log.error("Session not transferred!");
        throw new EmptySessionException("Session not transferred!");
    }

  }

  @Override
  public Operation findById(Long id) {
    return session.get(Operation.class, id);
  }

  @Override
  public Operation addOperation(Operation operation) {
          transaction.begin();
    try {
      session.save(operation);
      new AccountServiceImpl(new AccountRepositoryImpl(session)).updete(operation.getAccount());
      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
      System.err.println("rollback" + e);
      log.error("Error during transaction", e);
    }
    return operation;
  }

  public Session getSession() {
    return session;
  }
}
