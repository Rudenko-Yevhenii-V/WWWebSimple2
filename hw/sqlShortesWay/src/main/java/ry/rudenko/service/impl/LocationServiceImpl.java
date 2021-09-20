package ry.rudenko.service.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.dao.impl.LocationDaoImpl;
import ry.rudenko.entity.Location;
import ry.rudenko.service.LocationService;

public class LocationServiceImpl implements LocationService {
  private final LocationDao locationDao = new LocationDaoImpl();

  @Override
  public void create(Location location, Connection connection) {
    System.out.println("LocationServiceImpl.create");
    locationDao.create(location, connection);
  }

  @Override
  public void update(Location location, Connection connection) {

  }

  @Override
  public void delete(String id, Connection connection) {

  }

  @Override
  public Location findById(String id, Connection connection) {
    return null;
  }

  @Override
  public List<Location> findAll(Connection connection) {
    return null;
  }
}
