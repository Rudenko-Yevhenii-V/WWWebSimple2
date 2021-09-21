package ry.rudenko.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;


public class LoadProperty {
  public static Properties loadProperties() {

    Properties props = new Properties();

    try(InputStream input = LoadProperty.class.getResourceAsStream("/jdbc.properties")) {
      props.load(input);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

    return props;
  }

}
