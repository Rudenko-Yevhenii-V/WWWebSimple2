package ry.rudenko.bd.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MigrateDB {

  static final String DB_URL = "jdbc:postgresql://localhost:5444/db_HW_1";
  static final String USER = "rttr";
  static final String PASS = "root";

  public void createDB() {

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement()
    ) {
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

      stmt.executeUpdate(sqllocations);
      stmt.executeUpdate(sqlroutes);
      stmt.executeUpdate(sqlproblems);
      stmt.executeUpdate(sqlsolutions);

      System.out.println("Created table in given database...");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}