package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;
import ry.rudenko.dao.SolutionDao;
import ry.rudenko.entity.Solution;

public class SolutionDaoImpl implements SolutionDao {
  private final Connection connection;

  public SolutionDaoImpl(Supplier<Connection> connection) {
    this.connection = connection.get();
  }
  @Override
  public void create(List<Solution> solutionList) {
    for (Solution solution : solutionList) {
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
