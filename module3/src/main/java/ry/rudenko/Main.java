package ry.rudenko;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger log = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) {
    log.info("Main info");
    log.warn("Main warn");
    log.error("Main error");
    System.out.println("Main.main");
  }

}
