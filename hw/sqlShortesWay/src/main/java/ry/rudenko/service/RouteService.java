package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Route;

public interface RouteService {

    void create(List<Route> routes, Connection connection);
    void update(Route route, Connection connection);
    void delete(String id, Connection connection);
    Route findById(String id, Connection connection);
    List<Route> findAll(Connection connection);
}
