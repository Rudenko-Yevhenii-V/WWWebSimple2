package ry.rudenko;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.controller.OperationController;
import ry.rudenko.exception.EmptyArgsException;

public class Main {

  private static String dbName = null;
  private static String dbPass = null;
  private static String phone = null;

  public static String getDbName() {
    return dbName;
  }

  public static String getDbPass() {
    return dbPass;
  }

  public static String getPhone() {
    return phone;
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
    phone = args[2];
    new OperationController().createOperation(phone);
//    new OutputCsvController().createCsv(UUID.fromString("3a536063-5678-33c8-2793-b05b5fe70596"), "2021-10-10 21:32:52",
//        "2021-10-10 21:32:52");
  }
}
