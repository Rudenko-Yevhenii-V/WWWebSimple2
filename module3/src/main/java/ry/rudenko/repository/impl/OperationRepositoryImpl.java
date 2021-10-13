package ry.rudenko.repository.impl;

import java.util.UUID;
import java.util.function.Supplier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Operation;
import ry.rudenko.repository.OperationRepository;
import ry.rudenko.service.impl.AccountServiceImpl;

public class OperationRepositoryImpl implements OperationRepository {

  private static final Logger log = LoggerFactory.getLogger(OperationRepositoryImpl.class);

  private final Session session;
  final Transaction transaction;

  public OperationRepositoryImpl(Supplier<Session> sessionSupplier) throws EmptySessionException {
    this.session = sessionSupplier.get();
    if (session.isOpen()) {
      log.info("Is open session? {}",session.isOpen());
      transaction = session.getTransaction();
    } else {
      log.error("Session not transferred!");
      throw new EmptySessionException("Session not transferred!");
    }
  }

  @Override
  public Operation findById(UUID id) {
    return session.get(Operation.class, id);
  }

  @Override
  public Operation addOperation(Operation operation) {
    transaction.begin();
    try {
      session.save(operation);
      new AccountServiceImpl(new AccountRepositoryImpl(() -> session)).updete(
          operation.getAccount());
      transaction.commit();
      System.out.println("Your transaction completed thank you!");
    } catch (Exception e) {
      transaction.rollback();
      System.err.println("rollback" + e);
      log.error("Error during transaction", e);
      throw new RuntimeException(e);
    }
    return operation;
  }

  public Session getSession() {
    return session;
  }
}
