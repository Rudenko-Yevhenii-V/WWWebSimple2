package ry.rudenko.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.annotations.CsvMapping;

public class CsvMapper {

  private static final Logger log = LoggerFactory.getLogger(CsvParser.class);

  public <T> List<T> mapperCsv(Class<T> type, CSVTable table) {
    String[] heders = table.getHeader();
    Map<String, String[]> map = table.getRows();
    List<T> objectList = new ArrayList<>();
    int countOfObj = map.get(heders[1]).length;
    for (int i = 0; i < countOfObj; i++) {
      Properties props = new Properties();
      for (String heder : heders) {
        props.put(heder, map.get(heder)[i]);
      }
      objectList.add(map(type, props));
    }
    return objectList;
  }

  public <T> T map(Class<T> type, Properties props) {
    Object instance = createObject(type);
    for (Field field : instance.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      CsvMapping pk = field.getAnnotation(CsvMapping.class);
      if (pk == null) {
        continue;
      }
      final String property = props.getProperty(pk.value());
      try {
        if (field.getType().equals(String.class)) {
          field.set(instance, property);
        } else if (field.getType().equals(int.class)) {
          field.setInt(instance, Integer.parseInt(property));
        } else if (field.getType().isEnum()) {
          final Object[] enumConstants = field.getType().getEnumConstants();
          for (Object enumConstant : enumConstants) {
            if (String.valueOf(enumConstant).equals(property)) {
              field.set(instance, enumConstant);
            }
          }
        } else if (field.getType().equals(long.class)) {
          field.setLong(instance, Long.parseLong(property));
        } else if (field.getType().equals(boolean.class)) {
          field.setBoolean(instance, Boolean.parseBoolean(property));
        } else if (field.getType().equals(double.class)) {
          field.setDouble(instance, Double.parseDouble(property));
        } else if (field.getType().equals(LocalDate.class)) {
          final String[] split = property.split("-");
          final LocalDate localDate = LocalDate.of(
              Integer.parseInt(split[2]),
              Integer.parseInt(split[1]),
              Integer.parseInt(split[0])
          );
          field.set(instance, localDate);
        }
      } catch (IllegalAccessException e) {
        log.error("Dont add value in fields", e);
        throw new RuntimeException(e);
      }
    }
    return (T) instance;
  }

  private Object createObject(Class clazz) {
    Object o;
    try {
      o = clazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      log.error("Failed to instantiate an object of class {}", clazz, e);
      throw new RuntimeException(e);
    }
    return o;
  }
}
