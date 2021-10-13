package ry.rudenko.repository.impl;


import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.exception.EmptySessionException;
import ry.rudenko.model.entity.Account;
import ry.rudenko.model.entity.User;
import ry.rudenko.repository.AccountRepository;

public class AccountRepositoryImpl implements AccountRepository {

  private static final Logger log = LoggerFactory.getLogger(AccountRepositoryImpl.class);

  private final Session session;

  public AccountRepositoryImpl(Supplier<Session> sessionSupplier) throws EmptySessionException {
    this.session = sessionSupplier.get();
    if (session.isOpen()) {
      log.info("Is open session? {}",session.isOpen());
    } else {
      log.error("Session not transferred!");
      throw new EmptySessionException("Session not transferred!");
    }
  }

  @Override
  public Account findById(UUID id) {
    return session.get(Account.class, id);
  }

  public List<Account> findByUserId(User user) {
    return session.createQuery(
            "select a from Account as a where a.user = :user", Account.class)
        .setParameter("user", user)
        .getResultList();
  }

  @Override
  public Account update(Account inAccount) {
    final Account account = session.get(Account.class, inAccount.getId());
    if(account == null){
      System.out.println("null");
    }
    assert account != null;
    account.setBalance(inAccount.getBalance());
    session.update(account);
    return account;
  }
}
