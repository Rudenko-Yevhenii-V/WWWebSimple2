package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Solution;

public interface SolutionService {

    void create(List<Solution> solutions, Connection connection);
    void update(Solution solution, Connection connection);
    void delete(String id, Connection connection);
    Solution findById(String id, Connection connection);
    List<Solution> findAll(Connection connection);
}
