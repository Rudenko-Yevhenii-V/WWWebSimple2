package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;
import ry.rudenko.dao.ProblemDao;
import ry.rudenko.entity.Problem;

public class ProblemDaoImpl implements ProblemDao {
  private final Connection connection;

  public ProblemDaoImpl(Supplier<Connection> connection) {
    this.connection = connection.get();
  }
  @Override
  public void create(List<Problem> problemList) {
    for (Problem problem : problemList) {
      try (PreparedStatement test = connection.prepareStatement(
          "SELECT id FROM problems WHERE id = ?",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        test.setInt(1, problemList.size());
        final ResultSet resultSet = test.executeQuery();
        if (resultSet.next()) {
          System.err.println("problems db EXIST !!!!!");
          return;
        }
      } catch (SQLException e) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        throw new RuntimeException(e);
      }
      try (PreparedStatement insertContact = connection.prepareStatement(
          "INSERT INTO problems (from_id, to_id) VALUES (?,?)",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        insertContact.setInt(1, problem.from_id());
        insertContact.setInt(2, problem.to_id());
        insertContact.execute();
        connection.commit();
      } catch (SQLException e) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        throw new RuntimeException(e);
      }
      try (PreparedStatement insertNullToSolution = connection.prepareStatement(
          "INSERT INTO solutions (cost) VALUES (null)",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        insertNullToSolution.execute();
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
