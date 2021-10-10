package ry.rudenko.model.entity;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
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
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "account_id", updatable = false, nullable = false)
  private UUID id;

    @Column(nullable=false)
  private BigInteger balance;


//
//  @ManyToOne
//  @JoinColumn(name="user_id", nullable = false)
//  private User user;
//

//
//  @OneToMany(mappedBy = "account")
//  private List<Operation> operations;
//


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public BigInteger getBalance() {
    return balance;
  }

  public void setBalance(BigInteger balance) {
    this.balance = balance;
  }
}
