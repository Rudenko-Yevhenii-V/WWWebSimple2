package ry.rudenko.controlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import ry.rudenko.entity.Location;
import ry.rudenko.entity.Problem;
import ry.rudenko.entity.Route;
import ry.rudenko.entity.Solution;
import ry.rudenko.util.Generate;
import ry.rudenko.util.LoadProperty;
import ry.rudenko.util.MostProfitableWay;

public class CityController {

  public static int countRoute;

  public void start() {
    List<Route> routes;
    List<Problem> problems;
    List<Solution> solutions;
    List<Location> locations = initNameOfCity();
    Map<String, Integer> wayCost = initWayCosts();
    Map<String, String> wayToFind = initwayToFind();
    Properties props = LoadProperty.loadProperties();
    String url = props.getProperty("url");

    try (Connection connection = DriverManager.getConnection(url, props)) {
      connection.setAutoCommit(false);
//      new LocationServiceImpl().create(locations, connection);

      routes = initRoutes(connection, wayCost);
//      new RouteServiceImpl().create(routes, connection);

      problems = initProblems(connection, wayToFind);
//      new ProblemServiceImpl().create(problems, connection);

      solutions = initSolutions(connection);
//      new SolutionServiceImpl().create(solutions,connection);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Solution> initSolutions(Connection connection) {
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
        Integer start = 0;
        Integer stop = 0;
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
          select_from_id_to_id_from_problems.setInt(1,problemId);
          final ResultSet resultSet = select_from_id_to_id_from_problems.executeQuery();
          while(resultSet.next()){
            final int from_id = resultSet.getInt("from_id");
            final int to_id = resultSet.getInt("to_id");
            start = from_id;
            stop = to_id;
          }
        }

        MostProfitableWay mostProfitableWay = new MostProfitableWay();
        final String outPut = mostProfitableWay.mostProfitableWay(start,stop,inputMatrix);
//        System.out.println("outPut = " + outPut);
      }
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      throw new RuntimeException(e);
    }
    return solutions;
  }

  private Map<String, String> initwayToFind() {
    Map<String, String> wayToFind = new TreeMap<>();
    wayToFind.put("gdansk", "warszawa");
    wayToFind.put("bydgoszcz", "warszawa");
    return wayToFind;
  }

  private List<Problem> initProblems(Connection connection, Map<String, String> wayToFind) {
    List<Problem> problems = new ArrayList<>();
    for (Map.Entry<String, String> entry : wayToFind.entrySet()) {
      String cityFrom = entry.getKey();
      String cityTo = entry.getValue();

      try (PreparedStatement insertContact = connection.prepareStatement(
          "SELECT id FROM locations WHERE name = ?"
      )) {
        int id1 = 0;
        int id2 = 0;
        insertContact.setString(1, cityFrom);
        ResultSet rs1 = insertContact.executeQuery();
        if (rs1.next()) {
          id1 = rs1.getInt("id");
        }
        insertContact.setString(1, cityTo);
        ResultSet rs2 = insertContact.executeQuery();
        if (rs2.next()) {
          id2 = rs2.getInt("id");
        }
        problems.add(new Problem(new Generate().getGenerateId(),
            id1,
            id2));
      } catch (SQLException e) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        throw new RuntimeException(e);
      }


    }
    return problems;
  }

  private List<Route> initRoutes(Connection connection, Map<String, Integer> wayCost) {
    List<Route> routes = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : wayCost.entrySet()) {
      Integer value = entry.getValue();
      String key = entry.getKey();
      final String[] split = key.split("-");
      try (PreparedStatement insertContact = connection.prepareStatement(
          "SELECT id FROM locations WHERE name = ?"
      )) {
        int id1 = 0;
        int id2 = 0;
        insertContact.setString(1, split[0]);
        ResultSet rs1 = insertContact.executeQuery();
        if (rs1.next()) {
          id1 = rs1.getInt("id");
        }
        insertContact.setString(1, split[1]);
        ResultSet rs2 = insertContact.executeQuery();
        if (rs2.next()) {
          id2 = rs2.getInt("id");
        }
        routes.add(new Route(new Generate().getGenerateId(),
            id1,
            id2,
            value));
      } catch (SQLException e) {
        try {
          connection.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        throw new RuntimeException(e);
      }
    }
    return routes;
  }

  private List<Location> initNameOfCity() {
    List<Location> locations = new ArrayList<>();
    locations.add(new Location(new Generate().getGenerateId(), "gdansk"));
    locations.add(new Location(new Generate().getGenerateId(), "bydgoszcz"));
    locations.add(new Location(new Generate().getGenerateId(), "torun"));
    locations.add(new Location(new Generate().getGenerateId(), "warszawa"));
    return locations;
  }

  private Map<String, Integer> initWayCosts() {
    Map<String, Integer> wayCost = new TreeMap<>();
    wayCost.put("gdansk-bydgoszcz", 1);
    wayCost.put("gdansk-torun", 3);
    wayCost.put("bydgoszcz-gdansk", 1);
    wayCost.put("bydgoszcz-torun", 1);
    wayCost.put("bydgoszcz-warszawa", 4);
    wayCost.put("torun-gdansk", 3);
    wayCost.put("torun-bydgoszcz", 1);
    wayCost.put("torun-warszawa", 1);
    wayCost.put("warszawa-bydgoszcz", 4);
    wayCost.put("warszawa-torun", 1);
    return wayCost;
  }
}
