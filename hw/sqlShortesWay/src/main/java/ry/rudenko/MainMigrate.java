package ry.rudenko;


import ry.rudenko.db.migration.CleanDB;
import ry.rudenko.db.migration.InitializeData;
import ry.rudenko.db.migration.MigrateDB;

public class MainMigrate {

  public static void main(String[] args) {

    new CleanDB().clean();
    new MigrateDB().createDB();
    new InitializeData().initialize();

  }
}
