package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Location;

public interface LocationService {

    void create(List<Location> locations, Connection connection);

}
