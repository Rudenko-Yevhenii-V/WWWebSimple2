package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Problem;

public interface ProblemService {

    void create(List<Problem> problems, Connection connection);
    void update(Problem problem, Connection connection);
    void delete(String id, Connection connection);
    Problem findById(String id, Connection connection);
    List<Problem> findAll(Connection connection);
}
