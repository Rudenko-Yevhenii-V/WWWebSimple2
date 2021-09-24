package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.dao.SolutionDao;
import ry.rudenko.entity.Solution;

public class SolutionDaoImpl implements SolutionDao {

  private final Connection connection;
  private static final Logger LOGGER_ERROR= LoggerFactory.getLogger("error");

  public SolutionDaoImpl(Supplier<Connection> connection) {
    this.connection = connection.get();
  }

  @Override
  public void create(List<Solution> solutionList) {
    try (PreparedStatement insertContact = connection.prepareStatement(
        "UPDATE solutions SET cost = ? WHERE problem_id = ? ",
        PreparedStatement.RETURN_GENERATED_KEYS
    )) {
      for (Solution solution : solutionList) {
        insertContact.setInt(1, solution.cost());
        insertContact.setInt(2, solution.problem_id());
        insertContact.addBatch();
      }
      insertContact.executeBatch();
      connection.commit();
    } catch (SQLException e) {
      LOGGER_ERROR.error("PreparedStatement" + e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
        LOGGER_ERROR.error("rollback" + e);
        ex.printStackTrace();
      }
      throw new RuntimeException(e);
    }
  }
}
