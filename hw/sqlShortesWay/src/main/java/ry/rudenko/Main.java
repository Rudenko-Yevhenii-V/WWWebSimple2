package ry.rudenko;


import ry.rudenko.bd.migration.CleanDB;
import ry.rudenko.bd.migration.MigrateDB;
import ry.rudenko.controlers.CityController;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello world)");
    new CleanDB().clean();
    new MigrateDB().createDB();
    new CityController().start();
  }
}
