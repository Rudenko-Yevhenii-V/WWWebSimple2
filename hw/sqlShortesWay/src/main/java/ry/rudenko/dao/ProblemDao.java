package ry.rudenko.dao;


import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Problem;

public interface ProblemDao {
 void create(List<Problem> problems, Connection connection);
}
