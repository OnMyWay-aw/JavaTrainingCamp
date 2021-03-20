package pers.fffjia.javacamp.week1.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoader4Hello extends ClassLoader {

  public static void main(String[] args) {
    try {
      Object object = new ClassLoader4Hello().findClass("Hello").newInstance();
      Method method = object.getClass().getMethod("hello");
      method.invoke(object);

    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {

    byte[] bytes = readFile("/Users/jiaruifeng/github/javacamp/src/pers/fffjia/javacamp/week1/demo2/Hello.xlass");
    return defineClass(name, bytes, 0, bytes.length);
  }

  private byte[] readFile(String filename) {

    File file = new File(filename);
    FileInputStream in = null;
    byte[] bytes = new byte[(int) file.length()];

    try {
      in = new FileInputStream(filename);
      int c;
      int i = 0;
      while (true) {
        if (!((c = in.read()) != -1)) {
          break;
        }
        bytes[i] = (byte) (255 - c);
        i++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return bytes;
  }
}
