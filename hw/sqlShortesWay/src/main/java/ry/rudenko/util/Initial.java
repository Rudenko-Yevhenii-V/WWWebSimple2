package ry.rudenko.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.entity.Solution;

public class Initial {
  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

  public List<Solution> initSolutions(Supplier<Connection> conFactory) {
    Connection connection = conFactory.get();
    List<Solution> solutions = new ArrayList<>();
    List<Integer> solutionsId = new ArrayList<>();
    try (PreparedStatement insertContact = connection.prepareStatement(
        "SELECT problem_id FROM solutions WHERE cost IS NULL"
    )) {
      ResultSet rs1 = insertContact.executeQuery();
      boolean emp = true;
      while (rs1.next()) {
        emp = false;
        final int problem_id = rs1.getInt("problem_id");
        System.out.println("need solve  where  problem_id = " + problem_id);
        solutionsId.add(problem_id);
      }
      if(emp){
        System.out.println("All problem have solution!");
        System.exit(0);
      }
      for (Integer problemId : solutionsId) {
        int[][] inputMatrix = new int[0][0];
        int start = 0;
        int stop = 0;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(id)\n"
            + "FROM locations AS count");
        while (rs.next()) {
          final int countCity = rs.getInt("count");
          inputMatrix = new int[countCity][countCity];
        }
        for (int i = 0; i < inputMatrix.length; i++) {
          for (int i1 = 0; i1 < inputMatrix[i].length; i1++) {
            if (i == i1) {
              inputMatrix[i][i1] = 0;
              continue;
            }
            inputMatrix[i][i1] = -1;
          }
        }
        try (PreparedStatement select_from_id_to_id_from_problems = connection.prepareStatement(
            "SELECT from_id, to_id FROM problems WHERE id = ?"
        )) {
          select_from_id_to_id_from_problems.setInt(1, problemId);
          final ResultSet resultSet = select_from_id_to_id_from_problems.executeQuery();
          while (resultSet.next()) {
            final int from_id = resultSet.getInt("from_id");
            final int to_id = resultSet.getInt("to_id");
            start = from_id;
            stop = to_id;
          }
        }
        try (PreparedStatement select = connection.prepareStatement(
            "SELECT from_id, to_id, cost FROM routes"
        )) {
          final ResultSet resultSet = select.executeQuery();
          while (resultSet.next()) {
            final int from_id = resultSet.getInt("from_id");
            final int to_id = resultSet.getInt("to_id");
            final int cost = resultSet.getInt("cost");
            inputMatrix[from_id - 1][to_id - 1] = cost;
          }
        }
        MostProfitableWay mostProfitableWay = new MostProfitableWay();
        final String outPut = mostProfitableWay.mostProfitableWay(start, stop, inputMatrix);
        int cost = Integer.parseInt(outPut.replaceAll("[^0-9]", ""));
        if (cost < 1){
          System.out.println("City don't have way!");
        }
        solutions.add(new Solution(problemId, cost));
      }
    } catch (SQLException e) {
      LOGGER_ERROR.error(" connection : " + e);
      try {
        connection.rollback();
      } catch (SQLException ex) {
        LOGGER_ERROR.error(" rollback : " + e);
        ex.printStackTrace();
      }
      throw new RuntimeException(e);
    }
    return solutions;
  }

}
