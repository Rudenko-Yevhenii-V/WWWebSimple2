package ry.rudenko.db.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.dao.impl.LocationDaoImpl;
import ry.rudenko.dao.impl.ProblemDaoImpl;
import ry.rudenko.dao.impl.RouteDaoImpl;
import ry.rudenko.entity.Location;
import ry.rudenko.entity.Problem;
import ry.rudenko.entity.Route;
import ry.rudenko.service.impl.LocationServiceImpl;
import ry.rudenko.service.impl.ProblemServiceImpl;
import ry.rudenko.service.impl.RouteServiceImpl;
import ry.rudenko.util.Generate;
import ry.rudenko.util.LoadProperty;

public class InitializeData {

  private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

  public void initialize() {
    List<Route> routes;
    List<Problem> problems;
    List<Location> locations = initNameOfCity();
    Map<String, Integer> wayCost = initWayCosts();
    Map<String, String> wayToFind = initwayToFind();
    Properties props = LoadProperty.loadProperties();
    String url = props.getProperty("url");
    try (Connection connection = DriverManager.getConnection(url, props)) {
      connection.setAutoCommit(false);
      new LocationServiceImpl(new LocationDaoImpl(() -> connection)).create(locations);
      routes = initRoutes(connection, wayCost);
      new RouteServiceImpl(new RouteDaoImpl(() -> connection)).create(routes);
      problems = initProblems(connection, wayToFind);
      new ProblemServiceImpl(new ProblemDaoImpl(() -> connection)).create(problems);
    } catch (SQLException e) {
      LOGGER_ERROR.error(" connection : " + e);
      throw new RuntimeException(e);
    }
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
        LOGGER_ERROR.error("connection  : " + e);
        try {
          connection.rollback();
        } catch (SQLException ex) {
          LOGGER_ERROR.error("connection rollback : " + e);
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
        LOGGER_ERROR.error("connection  : " + e);
        try {
          connection.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        LOGGER_ERROR.error("connection rollback : " + e);
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
