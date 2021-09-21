package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import ry.rudenko.entity.BaseEntity;
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
    System.out.println("DBjdbcSqlSolutions.create " +  baseEntityList);

//    Location location = (Location) baseEntity;
//    try (PreparedStatement insertContact = connection.prepareStatement(
//        "INSERT INTO locations (name) VALUES (?) ON CONFLICT DO NOTHING",
//        PreparedStatement.RETURN_GENERATED_KEYS
//    )) {
//      insertContact.setString(1, location.name());
//      int row = insertContact.executeUpdate();
//      if (row < 1) {
//        System.err.println("don't add to DB!!!!!!!!");
//      }
//      connection.commit();
//    } catch (SQLException e) {
//      try {
//        connection.rollback();
//      } catch (SQLException ex) {
//        ex.printStackTrace();
//      }
//      throw new RuntimeException(e);
//    }

  }
}