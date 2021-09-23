package ry.rudenko.service.impl;


import java.util.List;
import ry.rudenko.dao.SolutionDao;
import ry.rudenko.entity.Solution;
import ry.rudenko.service.SolutionService;

public record SolutionServiceImpl(SolutionDao solutionDao) implements
    SolutionService {

  @Override
  public void create(List<Solution> solutions) {
    solutionDao.create(solutions);
  }
}
