package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Route;

public interface RouteService {

    void create(List<Route> routes, Connection connection);

}
