package ry.rudenko.model.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("expense")
@Table(name = "expense-category")
public class ExpenseCategory extends Category implements Serializable {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  @Column(name = "id_category")
//  private Long id;
//
//  @Column(name = "action_type_category")
//  private String actionType;
//

}
