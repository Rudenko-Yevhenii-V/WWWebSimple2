package ry.rudenko.model.entity;


import java.io.Serializable;
import java.util.List;
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

//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "accounts")
public class Account implements Serializable {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column
//  private Long id;
//
//  @ManyToOne
//  @JoinColumn(name="user_id", nullable = false)
//  private User user;
//
//  @Column(nullable=false)
//  private Long currency;
//
//  @OneToMany(mappedBy = "account")
//  private List<Operation> operations;
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
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
//  public List<Operation> getOperations() {
//    return operations;
//  }
//
//  public void setOperations(List<Operation> operations) {
//    this.operations = operations;
//  }
}
