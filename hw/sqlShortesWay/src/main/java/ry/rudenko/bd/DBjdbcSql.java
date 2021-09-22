package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ry.rudenko.entity.Location;

public class DBjdbcSql implements Idb {

  private static final DBjdbcSql instance = new DBjdbcSql();

  private DBjdbcSql() {
  }

  public static DBjdbcSql getInstance() {
    return instance;
  }

  @Override
  public String findAll(Connection connection) {

    try (PreparedStatement insertContact = connection.prepareStatement(
        "SELECT solutions.cost, problems.from_id, problems.to_id\n"
            + "FROM solutions LEFT JOIN\n"
            + "    problems  on problems.id = solutions.problem_id;",
        PreparedStatement.RETURN_GENERATED_KEYS
    )) {
      final ResultSet resultSet = insertContact.executeQuery();
      while (resultSet.next()) {
        int cost = resultSet.getInt("cost");
        int from_id = resultSet.getInt("from_id");
        int to_id = resultSet.getInt("to_id");
        System.out.println("price = " +cost
            + " from " + from_id + " to " + to_id);
      }
      connection.commit();
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      throw new RuntimeException(e);
    }
    return "return";
  }
}
