package ry.rudenko.dao.impl;


import java.sql.Connection;
import ry.rudenko.bd.DBjdbcSqlLocations;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.entity.Location;

public class LocationDaoImpl implements LocationDao {

  @Override
  public void create(Location location, Connection connection) {
    System.out.println("LocationDaoImpl.create");
    DBjdbcSqlLocations.getInstance().create(location, connection);
  }
}
