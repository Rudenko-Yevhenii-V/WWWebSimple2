package ry.rudenko.dao;


import java.sql.Connection;
import ry.rudenko.entity.Location;

public interface LocationDao {
 void create(Location location, Connection connection);
}
