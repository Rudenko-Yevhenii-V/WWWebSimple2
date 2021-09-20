package ry.rudenko.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;
import ry.rudenko.JdbcBoxRunner;


public class LoadProperty {
  public static Properties loadProperties() {

    Properties props = new Properties();

    try(InputStream input = JdbcBoxRunner.class.getResourceAsStream("/jdbc.properties")) {
      props.load(input);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    return props;
  }

}
