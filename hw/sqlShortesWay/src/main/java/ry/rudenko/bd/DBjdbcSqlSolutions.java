package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import ry.rudenko.entity.Solution;

public class DBjdbcSqlSolutions implements IdbSolutions{

  private static final DBjdbcSqlSolutions instance = new DBjdbcSqlSolutions();

  private DBjdbcSqlSolutions() {
  }

  public static DBjdbcSqlSolutions getInstance() {
    return instance;
  }

  @Override
  public void create(List<Solution> baseEntityList, Connection connection) {
    for (Solution solution : baseEntityList) {
      try (PreparedStatement insertContact = connection.prepareStatement(
          "UPDATE solutions SET cost = ? WHERE problem_id = ? ",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        insertContact.setInt(1, solution.cost());
        insertContact.setInt(2, solution.problem_id());
        int row = insertContact.executeUpdate();
        if (row > 0) {
          System.err.println("Successful!!!!!!!!");
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
    }

  }
}