package ry.rudenko.model.entity;


import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "income_or_expense",
    discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "category")
public abstract class Category {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "category_id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "action_type", nullable = false, unique = true)
  private String actionType;

  @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY,
      mappedBy = "category")
  private List<Operation> operations;


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getActionType() {
    return actionType;
  }

  public void setActionType(String actionType) {
    this.actionType = actionType;
  }

  public List<Operation> getOperations() {
    return operations;
  }

  public void setOperations(List<Operation> operations) {
    this.operations = operations;
  }

  public Category(UUID id, String actionType,
      List<Operation> operations) {
    this.id = id;
    this.actionType = actionType;
    this.operations = operations;
  }
}
