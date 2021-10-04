package ry.rudenko.yevhenii.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "group", schema = "public")
public class Group  {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "group_id", updatable = false, nullable = false)
  private UUID id;

  @Column(nullable = false)
  private String name;



  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Student> students;

  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Course> courses;
  @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,
      fetch = FetchType.LAZY, orphanRemoval = true)
  private List<Lesson> lessons;

}
