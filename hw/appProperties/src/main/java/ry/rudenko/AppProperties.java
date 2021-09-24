package ry.rudenko;

import java.time.DayOfWeek;
import ry.rudenko.annotations.PropertyKey;

public class AppProperties {
  @PropertyKey("value1")
  public String value1;

  public String getValue1() {
    return value1;
  }
//  @PropertyKey("day")
//  DayOfWeek day;

}
