package ry.rudenko.bd;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Problem;

public interface Idbproblems {

  void create(List<Problem> problemList, Connection connection);

}
