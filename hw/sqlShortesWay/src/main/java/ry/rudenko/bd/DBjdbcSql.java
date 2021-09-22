package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBjdbcSql implements Idb {

  private static final DBjdbcSql instance = new DBjdbcSql();

  private DBjdbcSql() {
  }

  public static DBjdbcSql getInstance() {
    return instance;
  }

  @Override
  public String findAll(Connection connection) {
    String ret ="";

    try (PreparedStatement insertContact = connection.prepareStatement(
        """
            SELECT  cost AS "price",
                   (SELECT name from locations
                       where locations.id = (SELECT from_id FROM problems
                                             where solutions.problem_id = problems.id
                       ))AS "from",
                    (SELECT name from locations
                     where locations.id = (SELECT to_id FROM problems
                                           where solutions.problem_id = problems.id
                     )) AS "to"
            FROM solutions;""",
        PreparedStatement.RETURN_GENERATED_KEYS
    )) {
      final ResultSet resultSet = insertContact.executeQuery();
      while (resultSet.next()) {
        int cost = resultSet.getInt("price");
        String from_id = resultSet.getString("from");
        String to_id = resultSet.getString("to");
        ret = "price = " +cost
            + "  => from " + from_id + " to " + to_id;
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
    return ret;
  }
}
