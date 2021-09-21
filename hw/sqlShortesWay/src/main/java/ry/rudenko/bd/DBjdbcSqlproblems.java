package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ry.rudenko.entity.Problem;

public class DBjdbcSqlproblems implements Idbproblems {

  private static final DBjdbcSqlproblems instance = new DBjdbcSqlproblems();

  private DBjdbcSqlproblems() {
  }

  public static DBjdbcSqlproblems getInstance() {
    return instance;
  }

  @Override
  public void create(List<Problem> problemList, Connection connection) {
    for (Problem baseEntity : problemList) {
      Problem route = baseEntity;
      try (PreparedStatement test = connection.prepareStatement(
          "SELECT id FROM problems WHERE id = ?",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        test.setInt(1, problemList.size());
        final ResultSet resultSet = test.executeQuery();
        if(resultSet.next()){
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
        insertContact.setInt(1, route.from_id());
        insertContact.setInt(2, route.to_id());
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