package ry.rudenko.db.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.util.LoadProperty;

public class MigrateDB {


  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

  public void createDB() {
    Properties props = LoadProperty.loadProperties();
    String url = props.getProperty("url");
    try (Connection connection = DriverManager.getConnection(url, props)) {
      try (Statement stmt = connection.createStatement()) {
        String sqllocations = "CREATE TABLE IF NOT EXISTS locations " +
            "(id SERIAL not NULL, " +
            "name text UNIQUE NOT NULL , " +
            "PRIMARY KEY ( id ))";

        String sqlroutes = "CREATE TABLE IF NOT EXISTS routes " +
            "(id SERIAL not NULL, " +
            "from_id INTEGER REFERENCES locations(id)  NOT NULL, " +
            "to_id INTEGER REFERENCES locations(id)  NOT NULL, " +
            "cost INTEGER NOT NULL, " +
            "PRIMARY KEY ( id ))";

        String sqlproblems = "CREATE TABLE IF NOT EXISTS problems " +
            "(id SERIAL not NULL, " +
            "from_id INTEGER REFERENCES locations(id)  NOT NULL, " +
            "to_id INTEGER REFERENCES locations(id)  NOT NULL, " +
            "PRIMARY KEY ( id ))";

        String sqlsolutions = "CREATE TABLE IF NOT EXISTS solutions("
            + "problem_id SERIAL PRIMARY KEY, cost INTEGER, "
            + "FOREIGN KEY (problem_id) REFERENCES problems (Id))";
        try {
          stmt.executeUpdate(sqllocations);
          stmt.executeUpdate(sqlroutes);
          stmt.executeUpdate(sqlproblems);
          stmt.executeUpdate(sqlsolutions);
        } catch (SQLException e) {
          LOGGER_ERROR.error(" connection : " + e);
          e.printStackTrace();
        }
        System.out.println("Created table in given database...");
      } catch (SQLException throwables) {
        LOGGER_ERROR.error(" statement : " + throwables);
        throwables.printStackTrace();
      }
    } catch (SQLException throwables) {
      LOGGER_ERROR.error(" connection : " + throwables);
      throwables.printStackTrace();
    }
  }
}