package ry.rudenko.util;

import java.awt.image.ImageProducer;
import java.nio.file.Path;
import java.util.Map;


public class CsvMapper {
  public <T> Object mapperCsv(Class<T> type, Map<String, String[]> map){
    System.out.println("stringMap = " + map.get("lastName")[0] + " " + map.get("lastName")[1]);

    return  null;
  }

}
