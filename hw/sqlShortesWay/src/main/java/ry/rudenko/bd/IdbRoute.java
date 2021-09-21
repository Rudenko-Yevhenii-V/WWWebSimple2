package ry.rudenko.bd;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Route;

public interface IdbRoute {

  void create(List<Route> routeList, Connection connection);

}
