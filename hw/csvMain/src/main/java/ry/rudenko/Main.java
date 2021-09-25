package ry.rudenko;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import ry.rudenko.data.UserDetails;
import ry.rudenko.util.CsvMapper;
import ry.rudenko.util.CsvParser;

/**
 * @author Rudenko Yevhenii
 * @created 25/09/2021 - 11:55 AM
 * @project hw
 */
public class Main {

  public static void main(String[] args) throws IOException {
    String pathToCsv = "file.csv";
    new CsvParser().parserCsv(UserDetails.class,Path.of(pathToCsv));





//File file = new File(pathToCsv);
//file.createNewFile();
//    System.out.println("file.exists() = " + file.exists());
////    BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
////    String row;
////    while ((row = csvReader.readLine()) != null) {
////      String[] data = row.split(",");
////      System.out.println("data = " + data);
////    }
////    csvReader.close();



//    try {
//      final List<String> strings = Files.readAllLines(Path.of(filePath)));
//      for (String string : strings) {
//        System.out.println("string = " + string);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }






//    try (InputStream input = ry.rudenko.Main.class.getResourceAsStream("/app.properties")) {
////      props.load(input);
////      final AppProperties appProperties = (AppProperties) initializer.map(AppProperties.class,
////          props);
//      System.out.println("appProperties = " + appProperties);
//    } catch (IOException e) {
//      System.err.println("InputStream" + e);
//      throw new UncheckedIOException(e);
//    }
  }

}
