package ry.rudenko;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import ry.rudenko.data.UserDetails;
import ry.rudenko.util.CsvParser;

public class Main {

  public static void main(String[] args) throws IOException {
    String pathToCsv = "file.csv";
    final List<Object> objectList = new CsvParser().parserCsv(UserDetails.class,
        Path.of(pathToCsv));
    for (int i = 0; i < objectList.size(); i++) {
      System.out.println("objectList.get(i) = " + objectList.get(i));
    }
  }
}
