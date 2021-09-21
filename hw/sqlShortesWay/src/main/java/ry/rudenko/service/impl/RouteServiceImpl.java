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

  @Override
  public void update(Route route, Connection connection) {

  }

  @Override
  public void delete(String id, Connection connection) {

  }

  @Override
  public Route findById(String id, Connection connection) {
    return null;
  }

  @Override
  public List<Route> findAll(Connection connection) {
    return null;
  }
}
