package ry.rudenko;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;
import ry.rudenko.lib.initializer.Initializerl;

public class Main {

  public static void main(String[] args) {
    Initializerl initializer = new Initializerl();
    Properties props = new Properties();
    try (InputStream input = ry.rudenko.lib.Main.class.getResourceAsStream("/app.properties")) {
      props.load(input);
      final AppProperties appProperties = (AppProperties) initializer.map(AppProperties.class,
          props);
      System.out.println("appProperties = " + appProperties);
    } catch (IOException e) {
      System.err.println("InputStream" + e);
      throw new UncheckedIOException(e);
    }
  }
}
