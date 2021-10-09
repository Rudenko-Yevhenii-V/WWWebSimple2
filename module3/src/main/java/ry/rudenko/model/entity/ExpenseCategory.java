package ry.rudenko.model.entity;


import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "expenses-category")
public class ExpenseCategory implements Serializable {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column
//  private Long id;
//
//  @ManyToMany
//  List<Operation> operations;
//
//  @Column(nullable=false)
//  private String actionType;
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public List<Operation> getOperations() {
//    return operations;
//  }
//
//  public void setOperations(List<Operation> operations) {
//    this.operations = operations;
//  }
//
//  public String getActionType() {
//    return actionType;
//  }
//
//  public void setActionType(String actionType) {
//    this.actionType = actionType;
//  }
}
