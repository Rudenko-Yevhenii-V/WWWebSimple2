package ry.rudenko.service.impl;


import java.util.List;
import ry.rudenko.dao.ProblemDao;
import ry.rudenko.entity.Problem;
import ry.rudenko.service.ProblemService;

public record ProblemServiceImpl(ProblemDao problemDao) implements ProblemService {

  @Override
  public void create(List<Problem> problems) {
    problemDao.create(problems);
  }
}





