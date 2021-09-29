package ry.rudenko;

import java.nio.file.Path;
import java.util.List;
import ry.rudenko.data.UserDetails;
import ry.rudenko.util.CSVTable;
import ry.rudenko.util.CsvMapper;
import ry.rudenko.util.CsvParser;

public class Main {

  public static void main(String[] args) {


    String pathToCsv = "file.csv";
    final CSVTable csvTable = new CsvParser().parserCsv(Path.of(pathToCsv));
    final List<UserDetails> objectList =  new CsvMapper().mapperCsv(UserDetails.class, csvTable);
    for (UserDetails userDetails : objectList) {
      System.out.println("objectList.get(i) = " + userDetails);
    }
  }
}
