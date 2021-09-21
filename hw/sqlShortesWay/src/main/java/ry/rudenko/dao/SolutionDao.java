package ry.rudenko.dao;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Problem;
import ry.rudenko.entity.Solution;

public interface SolutionDao {
 void create(List<Solution> solutions, Connection connection);
}
