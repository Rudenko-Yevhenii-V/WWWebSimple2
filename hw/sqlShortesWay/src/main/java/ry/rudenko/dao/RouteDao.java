package ry.rudenko.dao;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Route;

public interface RouteDao {
 void create(List<Route> routes, Connection connection);
}
