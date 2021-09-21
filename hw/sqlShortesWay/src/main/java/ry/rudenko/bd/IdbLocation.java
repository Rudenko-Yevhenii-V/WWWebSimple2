package ry.rudenko.bd;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Location;

public interface IdbLocation {
 void create(List<Location> locationListList,Connection connection);

}
