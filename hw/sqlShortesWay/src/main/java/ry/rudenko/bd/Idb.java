package ry.rudenko.bd;


import java.sql.Connection;
import java.sql.SQLException;
import ry.rudenko.entity.BaseEntity;

public interface Idb {
 void create(BaseEntity baseEntity,Connection connection) throws SQLException;

}
