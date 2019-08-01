package lesson_7_reflection_annotations.annotations;

import java.lang.reflect.InvocationTargetException;

public class MainClass {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        System.out.println("Тест для объекта");
        TestClass.start(new ExampleClass());
        System.out.println("\n\nТест для класса");
        TestClass.start(ExampleClass.class);
    }
}
