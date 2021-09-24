package ry.rudenko;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.dao.impl.SolutionDaoImpl;
import ry.rudenko.db.DBjdbcSql;
import ry.rudenko.entity.Solution;
import ry.rudenko.service.impl.SolutionServiceImpl;
import ry.rudenko.util.Initial;
import ry.rudenko.util.LoadProperty;

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
      solutions = new Initial().initSolutions(conFactory);
      new SolutionServiceImpl(new SolutionDaoImpl(() -> connection)).create(solutions);
     printSolution(conFactory);

    } catch (SQLException e) {
          LOGGER_ERROR.error(" connection : " + e);
      throw new RuntimeException(e);
    }
  }

  private static void printSolution(Supplier<Connection> conFactory) {
    System.out.println(
        "_________________________________________________________________________________________________________");
    System.out.println(
        "solutions : \n "
            + DBjdbcSql.getInstance().findAll(conFactory.get()));
  }
}
