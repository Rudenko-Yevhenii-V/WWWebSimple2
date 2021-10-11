package ry.rudenko.model.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("1")
public class IncomeCategory extends Category implements Serializable {
  public IncomeCategory(String actionType) {
    super.setActionType(actionType);
  }
}
