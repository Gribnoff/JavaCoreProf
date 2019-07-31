package lesson_7_reflection_annotations.reflection;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Reflect {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        @SuppressWarnings("deprecation")
        Class c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        Method[] m = c.getDeclaredMethods();
        for (Method o: m) {
            System.out.println(o);
        }
    }
}
