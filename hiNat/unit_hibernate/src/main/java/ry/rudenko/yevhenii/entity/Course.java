package ry.rudenko.yevhenii.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course", schema = "public")
public class Course extends BaseEntity{

  private String name;

}
