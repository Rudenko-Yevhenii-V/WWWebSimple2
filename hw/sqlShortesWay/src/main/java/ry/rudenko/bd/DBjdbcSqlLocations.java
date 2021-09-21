package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import ry.rudenko.entity.BaseEntity;
import ry.rudenko.entity.Location;

public class DBjdbcSqlLocations implements IdbLocation {

  private static final DBjdbcSqlLocations instance = new DBjdbcSqlLocations();

  private DBjdbcSqlLocations() {
  }

  public static DBjdbcSqlLocations getInstance() {
    return instance;
  }

  @Override
  public void create(List<Location> locationList, Connection connection) {
    for (BaseEntity baseEntity : locationList) {
      Location location = (Location) baseEntity;
      try (PreparedStatement insertContact = connection.prepareStatement(
          "INSERT INTO locations (name) VALUES (?) ON CONFLICT DO NOTHING",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        insertContact.setString(1, location.name());
        int row = insertContact.executeUpdate();
        if (row < 1) {
          System.err.println("don't add to DB locations  !!!!!!!!");
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
