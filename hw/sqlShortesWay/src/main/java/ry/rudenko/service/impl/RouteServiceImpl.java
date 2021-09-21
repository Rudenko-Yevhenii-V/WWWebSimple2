package ry.rudenko.service.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.dao.RouteDao;
import ry.rudenko.dao.impl.RouteDaoImpl;
import ry.rudenko.entity.Route;
import ry.rudenko.service.RouteService;

public class RouteServiceImpl implements RouteService {
  private final RouteDao routeDao = new RouteDaoImpl();


  @Override
  public void create(List<Route> routes, Connection connection) {
    routeDao.create(routes, connection);
  }
}
