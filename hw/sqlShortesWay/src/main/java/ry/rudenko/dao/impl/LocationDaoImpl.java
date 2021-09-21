package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.bd.DBjdbcSqlLocations;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.entity.Location;

public class LocationDaoImpl implements LocationDao {

  @Override
  public void create(List<Location> locations, Connection connection) {
    DBjdbcSqlLocations.getInstance().create(locations, connection);
  }
}
