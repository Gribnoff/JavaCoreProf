package lesson7_reflection_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;

/*
    private static boolean checkTwoNumbers(int first, int second) {
        int sum = first + second;
        return sum <= 20 && sum >= 10;
    }
*/

@RunWith(Parameterized.class)
public class Method3Test {
    @Parameterized.Parameter
    public int first;

    @Parameterized.Parameter(1)
    public int second;

    @Parameterized.Parameter(2)
    public boolean expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {10, 0, true},
                {0, 20, true},
                {10, 20, false},
                {20, -5, true}
        });
    }

    private Class<?> c;
    private Method m;

    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        //noinspection deprecation
        c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        Class[] params = new Class[2];
        Arrays.fill(params, int.class);
        m = c.getDeclaredMethod("checkTwoNumbers", params);
        m.setAccessible(true);
    }

    @Test
    public void test() throws Throwable {
        Assert.assertEquals(expected, m.invoke(c, first, second));
    }
}
