package ry.rudenko.model.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("2")
public class ExpenseCategory extends Category implements Serializable {
  public ExpenseCategory(String actionType) {
    super.setActionType(actionType);
  }

}
