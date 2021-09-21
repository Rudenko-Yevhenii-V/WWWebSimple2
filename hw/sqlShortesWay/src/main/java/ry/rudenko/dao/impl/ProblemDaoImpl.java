package ry.rudenko.dao.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.bd.DBjdbcSqlproblems;
import ry.rudenko.dao.ProblemDao;
import ry.rudenko.entity.Problem;

public class ProblemDaoImpl implements ProblemDao {

  @Override
  public void create(List<Problem> problems, Connection connection) {
    DBjdbcSqlproblems.getInstance().create(problems, connection);
  }
}
