package ry.rudenko.yevhenii.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "lessons", schema = "public")
public class Lesson {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;
  @Column
private Instant dateTime;

  public void setDateTime(String dateTime) {

    try {
      this.dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
          .parse(dateTime).toInstant();
    } catch (ParseException e) {
//      log
      e.printStackTrace();
    }
  }
}
