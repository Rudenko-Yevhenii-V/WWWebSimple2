package ry.rudenko.service;

import java.sql.Connection;
import java.util.List;
import ry.rudenko.entity.Problem;

public interface ProblemService {

    void create(List<Problem> problems, Connection connection);

}
