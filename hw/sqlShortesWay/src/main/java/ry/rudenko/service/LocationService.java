package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Location;

public interface LocationService {

    void create(Location location, Connection connection);
    void update(Location location, Connection connection);
    void delete(String id, Connection connection);
    Location findById(String id, Connection connection);
    List<Location> findAll(Connection connection);
}
