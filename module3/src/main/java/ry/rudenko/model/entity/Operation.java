package ry.rudenko.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
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

//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "operations")
public class Operation implements Serializable {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column
//  private Long id;
//
//  @ManyToOne
//  @JoinColumn(name="account_id", nullable = false)
//  private Account account;
//
//  @Column(nullable=false)
//  private Instant date;
//
//  @Column(nullable=false)
//  private Long amount;
//
//  @Column(nullable=false)
//  private Long currency;
//
//  @Column(nullable=false)
//  private String type;
//
//  @ManyToMany
//  List<IncomeCategory> incomeCategories;
//
//
//  @ManyToMany
//  List<ExpenseCategory> expenseCategories;
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public Account getAccount() {
//    return account;
//  }
//
//  public void setAccount(Account account) {
//    this.account = account;
//  }
//
//  public Instant getDate() {
//    return date;
//  }
//
//  public void setDate(Instant date) {
//    this.date = date;
//  }
//
//  public Long getAmount() {
//    return amount;
//  }
//
//  public void setAmount(Long amount) {
//    this.amount = amount;
//  }
//
//  public Long getCurrency() {
//    return currency;
//  }
//
//  public void setCurrency(Long currency) {
//    this.currency = currency;
//  }
//
//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
//
//  public List<IncomeCategory> getIncomeCategories() {
//    return incomeCategories;
//  }
//
//  public void setIncomeCategories(List<IncomeCategory> incomeCategories) {
//    this.incomeCategories = incomeCategories;
//  }
//
//  public List<ExpenseCategory> getExpenseCategories() {
//    return expenseCategories;
//  }
//
//  public void setExpenseCategories(
//      List<ExpenseCategory> expenseCategories) {
//    this.expenseCategories = expenseCategories;
//  }
}
