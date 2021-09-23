package ry.rudenko.service.impl;


import java.util.List;
import ry.rudenko.dao.RouteDao;
import ry.rudenko.entity.Route;
import ry.rudenko.service.RouteService;

public record RouteServiceImpl(RouteDao routeDao) implements RouteService {

  @Override
  public void create(List<Route> routes) {
    routeDao.create(routes);
  }
}
