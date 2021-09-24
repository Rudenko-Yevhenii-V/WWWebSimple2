package ry.rudenko.initializer;


import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.Properties;

public class Initializer {
  public <T> void map(Class<T> type, Properties props){
    System.out.println("obj = " + type);
    System.out.println("props = " + props);
    Class<?> classOfInstance = type;
//  classOfInstance.getField
    Object instance = createObject(classOfInstance);

    for (Field field : classOfInstance.getFields()) {
      System.out.println("field = " + field);
      field.getAnnotation(classOfInstance.class)
//      annotations annotations
//          = field.getAnnotation(
//          annotations.class);
    }

  }
  private Object createObject(Class clazz) {
    Object o = null;
    try {
      o = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return o;
  }
}
