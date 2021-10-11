package ry.rudenko.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("2")
@Table(name = "expense_category")
public class ExpenseCategory extends Category implements Serializable {

//
//   @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,
//      mappedBy = "expenseCategory")
//  private List<Operation> operations;
//
//  public List<Operation> getOperations() {
//    return operations;
//  }
//
//
//  public void setOperations(List<Operation> operations) {
//    this.operations = operations;
//  }
}
