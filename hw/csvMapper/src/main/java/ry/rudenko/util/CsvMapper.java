package ry.rudenko.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ry.rudenko.annotations.CsvMapping;

public class CsvMapper {

  private static final Logger log = LoggerFactory.getLogger(CsvParser.class);

  public <T> List<Object> mapperCsv(Class<T> type, Map<String, String[]> map, String[] heders) {
    List<Object> objectList = new ArrayList<>();
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

  public <T> Object map(Class<T> type, Properties props) {
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
        } else if (field.getType().equals(Gender.class)) {
          Gender gen = switch (property) {
            case "MALE" -> Gender.MALE;
            case "FEMALE" -> Gender.FEMALE;
            default -> Gender.UNKNOWN;
          };
          field.set(instance, gen);
        } else if (field.getType().equals(long.class)) {
          field.setLong(instance, Long.parseLong(property));
        } else if (field.getType().equals(boolean.class)) {
          field.setBoolean(instance, Boolean.parseBoolean(property));
        } else if (field.getType().equals(double.class)) {
          field.setDouble(instance, Double.parseDouble(property));
        } else if (field.getType().equals(Calendar.class)) {
          final String[] split = property.split("-");
          field.set(instance, new GregorianCalendar(
              Integer.parseInt(split[2]),
              Integer.parseInt(split[1]),
              Integer.parseInt(split[0])
          ));
        }
      } catch (IllegalAccessException e) {
        log.error("Dont add value in fields", e);
        throw new RuntimeException();
      }
    }
    return instance;
  }

  private Object createObject(Class clazz) {
    Object o = null;
    try {
      o = clazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      log.error("Object o dont created", e);
      throw new RuntimeException();
    }
    return o;
  }
}
