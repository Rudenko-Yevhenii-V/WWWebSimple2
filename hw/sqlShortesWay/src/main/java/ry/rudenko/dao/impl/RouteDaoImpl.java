package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.bd.DBjdbcSqlLocations;
import ry.rudenko.bd.DBjdbcSqlRoutes;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.dao.RouteDao;
import ry.rudenko.entity.Location;
import ry.rudenko.entity.Route;

public class RouteDaoImpl implements RouteDao {

  @Override
  public void create(List<Route> routes, Connection connection) {
    DBjdbcSqlRoutes.getInstance().create(routes, connection);
  }
}
