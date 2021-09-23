package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.entity.Location;

public class LocationDaoImpl implements LocationDao {


  private final Connection connection;

  public LocationDaoImpl(Supplier<Connection> connection) {
    this.connection = connection.get();
  }

  @Override
  public void create(List<Location> locationList) {
    for (Location location : locationList) {
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
