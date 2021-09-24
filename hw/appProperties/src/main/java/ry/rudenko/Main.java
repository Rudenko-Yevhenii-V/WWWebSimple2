package ry.rudenko;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;
import ry.rudenko.initializer.Initializer;

public class Main {

  public static void main(String[] args) {
    System.out.println("Main.main");
    Initializer initializer = new Initializer();



    AppProperties appProperties = new AppProperties();
    System.out.println("appProperties.getValue1() = " + appProperties.getValue1());

    Properties props = new Properties();

    try(InputStream input = Main.class.getResourceAsStream("/app.properties")) {
      props.load(input);
      initializer.map(AppProperties.class, props);
      final String value1 = props.getProperty("value1");
//      System.out.println("value1 = " + value1);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
//  Misha A-level teacher Aspect, [22.09.21 20:52]
//  public <T> map(Class<T> type, Properties props)
//
//  Misha A-level teacher Aspect, [22.09.21 20:56]
//      factory.map(AppProperties.class, props)
//
//  Misha A-level teacher Aspect, [22.09.21 21:00]
//  day=TUESDAY
//
//  Misha A-level teacher Aspect, [22.09.21 21:00]
//  @PropertyKey("day")
//  DayOfWeek day
}
