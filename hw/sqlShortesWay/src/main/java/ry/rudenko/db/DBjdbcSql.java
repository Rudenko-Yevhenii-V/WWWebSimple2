package ry.rudenko.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBjdbcSql implements Idb {

  private static final DBjdbcSql instance = new DBjdbcSql();
  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

  private DBjdbcSql() {
  }

  public static DBjdbcSql getInstance() {
    return instance;
  }

  @Override
  public String findAll(Connection connection) {
    StringBuilder ret = new StringBuilder();

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
        ret.append("\nprice = ").append(cost).append("  => from ").append(from_id).append(" to ")
            .append(to_id);
      }
      connection.commit();
    } catch (SQLException e) {
      LOGGER_ERROR.error(" connection : " + e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
        LOGGER_ERROR.error(" connection rollback : " + e);
        ex.printStackTrace();
      }
      LOGGER_ERROR.error(" connection : " + e);
      throw new RuntimeException(e);
    }
    return ret.toString();
  }
}
