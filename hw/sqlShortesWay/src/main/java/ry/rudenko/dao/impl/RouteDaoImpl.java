package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;
import ry.rudenko.dao.RouteDao;
import ry.rudenko.entity.Route;

public class RouteDaoImpl implements RouteDao {
  private final Connection connection;

  public RouteDaoImpl(Supplier<Connection> connection) {
    this.connection = connection.get();
  }
  @Override
  public void create(List<Route> baseEntityList) {
    for (Route route : baseEntityList) {
      try (PreparedStatement test = connection.prepareStatement(
          "SELECT id FROM routes WHERE id = ?",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        test.setInt(1, baseEntityList.size());
        final ResultSet resultSet = test.executeQuery();
        if(resultSet.next()){
          System.err.println("Route db EXIST !!!!!");
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
          "INSERT INTO routes (from_id, to_id, cost) VALUES (?,?,?) ON CONFLICT DO NOTHING",
          PreparedStatement.RETURN_GENERATED_KEYS
      )) {
        insertContact.setInt(1, route.from_id());
        insertContact.setInt(2, route.to_id());
        insertContact.setInt(3, route.cost());
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
    }
  }
}
