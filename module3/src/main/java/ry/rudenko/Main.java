package ry.rudenko;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.controller.OperationController;
import ry.rudenko.exception.EmptyArgsException;

public class Main {

  private static String dbName = null;
  private static String dbPass = null;

  public static String getDbName() {
    return dbName;
  }

  public static String getDbPass() {
    return dbPass;
  }

  private static final Logger log = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    if (args.length < 3) {
      try {
        throw new EmptyArgsException("Please pass the data!");
      } catch (EmptyArgsException e) {
        log.error("Did not receive username and password for DB or user's phone number ! ", e);
        throw new RuntimeException(e);
      }
    }
    dbName = args[0];
    dbPass = args[1];
    String phone = args[2];
    new OperationController().createOperation(phone);
  }
}
