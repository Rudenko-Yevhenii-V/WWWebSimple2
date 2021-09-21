package ry.rudenko.service.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.dao.ProblemDao;
import ry.rudenko.dao.impl.ProblemDaoImpl;
import ry.rudenko.entity.Problem;
import ry.rudenko.service.ProblemService;

public class ProblemServiceImpl implements ProblemService {
  private final ProblemDao problemDao = new ProblemDaoImpl();

  @Override
  public void create(List<Problem> problems, Connection connection) {
    problemDao.create(problems, connection);

  }

  @Override
  public void update(Problem problem, Connection connection) {

  }

  @Override
  public void delete(String id, Connection connection) {

  }

  @Override
  public Problem findById(String id, Connection connection) {
    return null;
  }

  @Override
  public List<Problem> findAll(Connection connection) {
    return null;
  }
}
