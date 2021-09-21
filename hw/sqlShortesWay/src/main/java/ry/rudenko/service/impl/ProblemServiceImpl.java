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
}
