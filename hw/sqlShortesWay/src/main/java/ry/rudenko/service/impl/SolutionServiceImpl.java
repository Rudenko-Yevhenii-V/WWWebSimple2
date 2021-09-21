package ry.rudenko.service.impl;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.dao.LocationDao;
import ry.rudenko.dao.SolutionDao;
import ry.rudenko.dao.impl.LocationDaoImpl;
import ry.rudenko.dao.impl.SolutionDaoImpl;
import ry.rudenko.entity.Solution;
import ry.rudenko.service.SolutionService;

public class SolutionServiceImpl implements SolutionService {
  private final SolutionDao solutionDao = new SolutionDaoImpl();

  @Override
  public void create(List<Solution> solutions, Connection connection) {
    solutionDao.create(solutions, connection);

  }

  @Override
  public void update(Solution solution, Connection connection) {

  }

  @Override
  public void delete(String id, Connection connection) {

  }

  @Override
  public Solution findById(String id, Connection connection) {
    return null;
  }

  @Override
  public List<Solution> findAll(Connection connection) {
    return null;
  }
}
