package ry.rudenko.lib.initializer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import ry.rudenko.lib.annotations.PropertyKey;

public class Initializerl {
  public <T> Object map(Class<T> type, Properties props){
    Object instance = createObject(type);
    for (Field field : instance.getClass().getFields()) {
      PropertyKey pk = field.getAnnotation(PropertyKey.class);
      if(pk == null){
        continue;
      }
      final String property = props.getProperty(pk.value());
      try{
        if (field.getType().equals(String.class)){
          field.set(instance, property);
        } else
        if (field.getType().equals(int.class)){
          field.setInt(instance, Integer.parseInt(property));
        }else
        if (field.getType().equals(long.class)){
          field.setLong(instance, Long.parseLong(property));
        }else
        if (field.getType().equals(double.class)){
          field.setDouble(instance, Double.parseDouble(property));
        }
      } catch (IllegalAccessException e) {
        System.err.println("init type of field " + e);
        e.printStackTrace();
      }
    }
return instance;
  }
  private Object createObject(Class clazz) {
    Object o = null;
    try {
      o = clazz.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
    return o;
  }
}
