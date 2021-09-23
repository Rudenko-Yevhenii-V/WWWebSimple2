package ry.rudenko.service.impl;


import java.util.List;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.entity.Location;
import ry.rudenko.service.LocationService;

public record LocationServiceImpl(LocationDao locationDao) implements
    LocationService {

  @Override
  public void create(List<Location> locations) {
    locationDao.create(locations);
  }
}
