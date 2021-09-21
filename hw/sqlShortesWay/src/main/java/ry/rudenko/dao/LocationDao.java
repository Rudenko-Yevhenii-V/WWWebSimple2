package ry.rudenko.dao;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Location;

public interface LocationDao {
 void create(List<Location> locations, Connection connection);
}
