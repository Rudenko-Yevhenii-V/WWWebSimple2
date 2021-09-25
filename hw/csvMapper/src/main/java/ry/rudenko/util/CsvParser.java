package ry.rudenko.util;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {

  public <T> List<Object> parserCsv(Class<T> type, Path path) {
    Map<String, String[]> csvValues = new HashMap<>();
    String[] splitToColumNames = new String[0];
    try {
      final List<String> csvLines = Files.readAllLines(path);
      if (csvLines.isEmpty()) {
        return null;
      }
      splitToColumNames = csvLines.get(0).split(",");
      String[][] splitColumVals = new String[splitToColumNames.length][csvLines.size() - 1];
      int count = 0;
      for (int i = 0; i < csvLines.size(); i++) {
        if (i == 0) {
          continue;
        }
        String[] splitColumVal = csvLines.get(i).split(",");
        for (int i1 = 0; i1 < splitColumVal.length; i1++) {
          splitColumVals[i1][count] = splitColumVal[i1];
        }
        count++;
      }
      for (int i = 0; i < splitToColumNames.length; i++) {
        csvValues.put(splitToColumNames[i], splitColumVals[i]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new CsvMapper().mapperCsv(type, csvValues, splitToColumNames);
  }
}
