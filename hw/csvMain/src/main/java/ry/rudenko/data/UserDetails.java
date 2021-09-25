package ry.rudenko.data;

import ry.rudenko.annotations.CsvMapping;
import ry.rudenko.util.Gender;

public class UserDetails {
  @CsvMapping("id")
  public String name;

  @CsvMapping("firstName")
  private int age;

  @CsvMapping("middleName")
  public String gender;

  @CsvMapping("lastName")
  private String occupation;

  @CsvMapping("birthDate")
  private String birthDate;

  @CsvMapping("gender")
  private Gender gen;

  @CsvMapping("active")
  private String active;

  @CsvMapping("engagementScore")
  private String engagementScore;

  public UserDetails(){}

//  public UserDetails(String name) {
//    this.name = name;
//
//  }


  @Override
  public String toString() {
    return "UserDetails{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", gender='" + gender + '\'' +
        ", occupation='" + occupation + '\'' +
        ", birthDate='" + birthDate + '\'' +
        ", gen=" + gen +
        ", active='" + active + '\'' +
        ", engagementScore='" + engagementScore + '\'' +
        '}';
  }
}
