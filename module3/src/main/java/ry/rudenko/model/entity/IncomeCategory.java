package ry.rudenko.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("1")
@Table(name = "income_category")
public class IncomeCategory extends Category implements Serializable {
  public IncomeCategory(String actionType) {
    super.setActionType(actionType);
  }
//
//  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,
//      mappedBy = "incomeCategory")
//  private List<Operation> operations;
//
//  public List<Operation> getOperations() {
//    return operations;
//  }
//
//  public void setOperations(List<Operation> operations) {
//    this.operations = operations;
//  }
}
