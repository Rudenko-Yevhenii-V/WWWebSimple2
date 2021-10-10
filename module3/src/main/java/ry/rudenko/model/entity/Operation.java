package ry.rudenko.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "operations")
public class Operation implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "operation_id", updatable = false, nullable = false)
  private UUID id;

  @Column(nullable = false)
  private Instant date;

  @Column(nullable=false)
  private Long amount;

  @Column(nullable=false)
  private String type;

//
//  @ManyToOne
//  @JoinColumn(name="account_id", nullable = false)
//  private Account account;
//

//
//  @ManyToMany
//  List<IncomeCategory> incomeCategories;
//
//
//  @ManyToMany
//  List<ExpenseCategory> expenseCategories;
//


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
