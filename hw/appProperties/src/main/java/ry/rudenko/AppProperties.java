package ry.rudenko;

import ry.rudenko.lib.annotations.PropertyKey;

public class AppProperties {
  @PropertyKey("String")
  public String value1;

  @PropertyKey("int")
  public int value2;

  @PropertyKey("long")
  public long value3;

  @PropertyKey("double")
  public double value4;

  @Override
  public String toString() {
    return "AppProperties{" +
        "value1='" + value1 + '\'' +
        ", value2=" + value2 +
        ", value3=" + value3 +
        ", value4=" + value4 +
        '}';
  }
}
