package ry.rudenko;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.dao.impl.SolutionDaoImpl;
import ry.rudenko.db.DBjdbcSql;
import ry.rudenko.entity.Solution;
import ry.rudenko.service.impl.SolutionServiceImpl;
import ry.rudenko.util.LoadProperty;
import ry.rudenko.util.MostProfitableWay;

public class Main {

  private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");


  public static void main(String[] args) {
    Properties props = LoadProperty.loadProperties();
    String url = props.getProperty("url");
    List<Solution> solutions;


    try (Connection connection = DriverManager.getConnection(url, props)) {
          LOGGER_INFO.info("Connecting to {}", url);
      connection.setAutoCommit(false);
      Supplier<Connection> conFactory = () -> connection;
      solutions = initSolutions(conFactory);
      new SolutionServiceImpl(new SolutionDaoImpl(() -> connection)).create(solutions);
      System.out.println(
          "_________________________________________________________________________________________________________");
      System.out.println(
          "SELECT  cost AS \"price\",(SELECT name from locations\n"
              + "where locations.id = (SELECT from_id FROM problems where solutions.problem_id = problems.id\n"
              + "))AS \"from\",(SELECT name from locations where locations.id = (SELECT to_id FROM problems\n"
              + "where solutions.problem_id = problems.id)) AS \"to\" FROM solutions;  = \n "
              + DBjdbcSql.getInstance().findAll(connection));

    } catch (SQLException e) {
          LOGGER_ERROR.error(" connection : " + e);
      throw new RuntimeException(e);
    }
  }

  private static List<Solution> initSolutions(Supplier<Connection> conFactory) {
    Connection connection = conFactory.get();
    List<Solution> solutions = new ArrayList<>();
    List<Integer> solutionsId = new ArrayList<>();
    try (PreparedStatement insertContact = connection.prepareStatement(
        "SELECT problem_id FROM solutions"
    )) {
      ResultSet rs1 = insertContact.executeQuery();
      while (rs1.next()) {
        final int problem_id = rs1.getInt("problem_id");
        solutionsId.add(problem_id);
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
        solutions.add(new Solution(problemId, Integer.parseInt(outPut.replaceAll("[^0-9]", ""))));
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
