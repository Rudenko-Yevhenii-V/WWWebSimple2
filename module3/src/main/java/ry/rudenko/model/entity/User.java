package ry.rudenko.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column(nullable=false)
  private String name;

//  @Column(name="phoneNumber", unique=true, nullable=false)
//  private String phoneNumber;
//
//  @OneToMany(mappedBy = "user")
//  private List<Account> accounts;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  public String getPhoneNumber() {
//    return phoneNumber;
//  }
//
//  public void setPhoneNumber(String phoneNumber) {
//    this.phoneNumber = phoneNumber;
//  }
//
//  public List<Account> getAccounts() {
//    return accounts;
//  }
//
//  public void setAccounts(List<Account> accounts) {
//    this.accounts = accounts;
//  }
}
