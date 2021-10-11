package ry.rudenko.model.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id", updatable = false, nullable = false)
  private Long id;

  @Column(nullable = false)
  private BigInteger balance;

  @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "account", cascade = CascadeType.REFRESH,
      fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Operation> operations;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigInteger getBalance() {
    return balance;
  }

  public void setBalance(BigInteger balance) {
    this.balance = balance;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Operation> getOperations() {
    return operations;
  }

  public void setOperations(List<Operation> operations) {
    this.operations = operations;
  }

  @Override
  public String toString() {
    return "Account{" +
        "id=" + id +
        ", balance=" + balance +
        ", user=" + user +
        ", operations=" + operations +
        '}';
  }
}
