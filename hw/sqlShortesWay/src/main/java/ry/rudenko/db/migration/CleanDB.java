package ry.rudenko.db.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.util.LoadProperty;

public class CleanDB {

  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

  public void clean() {
    Properties props = LoadProperty.loadProperties();
    String url = props.getProperty("url");
    try (Connection connection = DriverManager.getConnection(url, props)) {
      try (Statement stmt = connection.createStatement()) {
        String sql = "DROP TABLE IF EXISTS locations, routes, problems, solutions CASCADE";
        stmt.executeUpdate(sql);
        System.out.println("cleaned database...");
      }
    } catch (SQLException throwables) {
      LOGGER_ERROR.error(" Statement : " + throwables);
      throwables.printStackTrace();
    }
  }
}