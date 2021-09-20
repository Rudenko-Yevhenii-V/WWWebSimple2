package ry.rudenko.bd.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CleanDB {
  static final String DB_URL = "jdbc:postgresql://localhost:5444/db_HW_1";
  static final String USER = "rttr";
  static final String PASS = "root";

  public static void main(String[] args) {
    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
    ) {
      String sql = "DROP TABLE IF EXISTS locations, routes, problems, solutions CASCADE;";

      stmt.executeUpdate(sql);

      System.out.println("cleaned database...");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}