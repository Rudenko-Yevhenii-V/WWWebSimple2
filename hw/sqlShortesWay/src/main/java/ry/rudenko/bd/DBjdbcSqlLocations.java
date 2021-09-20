package ry.rudenko.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ry.rudenko.entity.BaseEntity;
import ry.rudenko.entity.Location;

public class DBjdbcSqlLocations implements Idb {

  private static final DBjdbcSqlLocations instance = new DBjdbcSqlLocations();

  private DBjdbcSqlLocations() {
  }

  public static DBjdbcSqlLocations getInstance() {
    return instance;
  }

  @Override
  public void create(BaseEntity baseEntity,Connection connection) {
    System.out.println("DBjdbcSqlLocations.create");
    System.out.println("baseEntity = " + baseEntity);
    System.out.println("connection = " + connection);
    Location location = (Location) baseEntity;
    try(PreparedStatement insertContact = connection.prepareStatement(
                    "INSERT INTO locations (name) VALUES (?) ON CONFLICT DO NOTHING",
                    PreparedStatement.RETURN_GENERATED_KEYS
            )) {
                insertContact.setString(1,location.name());
      int row = insertContact.executeUpdate();
      System.out.println("row = " + row);

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
