package lesson_7_reflection_annotations.annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class TestClass {
    @SuppressWarnings("SameParameterValue")
    static void start(Class<?> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = c.getConstructor();
        Object o = constructor.newInstance();
        start(o);
    }

    static void start(Object o) throws InvocationTargetException, IllegalAccessException {
        List<List<Method>> methods = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            methods.add(new ArrayList<>());
        }

        for (Method m : o.getClass().getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methods.get(0).size() != 0)
                    throw new RuntimeException("повтор @BeforeSuite");
                methods.get(0).add(m);
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methods.get(11).size() != 0)
                    throw new RuntimeException("повтор @AfterSuite");
                methods.get(11).add(m);
            } else if (m.isAnnotationPresent(Test.class)) {
                int p = m.getAnnotation(Test.class).priority();
                methods.get(11 - p).add(m);
            }
        }

        for (List<Method> list : methods) {
            for (Method method : list) {
                if (method.isAnnotationPresent(Test.class))
                    System.out.print("Execution priority = " + method.getAnnotation(Test.class).priority() + ". ");

                method.invoke(o);
            }
        }
    }
}
