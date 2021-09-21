package ry.rudenko.service.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.dao.SolutionDao;
import ry.rudenko.dao.impl.SolutionDaoImpl;
import ry.rudenko.entity.Solution;
import ry.rudenko.service.SolutionService;

public class SolutionServiceImpl implements SolutionService {
  private final SolutionDao solutionDao = new SolutionDaoImpl();

  @Override
  public void create(List<Solution> solutions, Connection connection) {
    solutionDao.create(solutions, connection);
  }
}
