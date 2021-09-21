package ry.rudenko.bd;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Route;
import ry.rudenko.entity.Solution;

public interface IdbSolutions {
 void create(List<Solution> solutionList,Connection connection);

}
