package ry.rudenko.db;


import java.sql.Connection;

public interface Idb {

  String findAll(Connection connection);
}
