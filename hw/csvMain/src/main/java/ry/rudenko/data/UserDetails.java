package ry.rudenko.data;

import java.util.Calendar;
import java.util.Date;
import ry.rudenko.annotations.CsvMapping;
import ry.rudenko.util.Gender;

public class UserDetails {
  @CsvMapping("id")
  private int id;

  @CsvMapping("firstName")
  private String firstName;

  @CsvMapping("middleName")
  private String middleName;

  @CsvMapping("lastName")
  private String lastName;

  @CsvMapping("birthDate")
  private Calendar birthDate;

  @CsvMapping("gender")
  private Gender gender;

  @CsvMapping("active")
  private boolean active;

  @CsvMapping("engagementScore")
  private double engagementScore;

  public UserDetails(){}

  @Override
  public String toString() {
    return "UserDetails{" +
        "id='" + id + '\'' +
        ", firstName=" + firstName +
        ", middleName='" + middleName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", birthDate='" + birthDate.getTime() + '\'' +
        ", gender=" + gender +
        ", active='" + active + '\'' +
        ", engagementScore='" + engagementScore + '\'' +
        '}';
  }
}
