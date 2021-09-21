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
  public void create(List<Location> locations, Connection connection) {
    locationDao.create(locations, connection);
  }
}
