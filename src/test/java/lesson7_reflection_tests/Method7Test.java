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
    private static boolean isLeapYear(int year) {
        return (year % 100 != 0) && (year % 4 == 0) || (year % 400 == 0);
    }
*/

@RunWith(Parameterized.class)
public class Method7Test {
    @Parameterized.Parameter
    public int year;

    @Parameterized.Parameter(1)
    public boolean expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {2000, true},
                {2055, false},
                {2100, false}
        });
    }

    private Class<?> c;
    private Method m;

    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        //noinspection deprecation
        c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        m = c.getDeclaredMethod("isLeapYear", int.class);
        m.setAccessible(true);
    }

    @Test
    public void test() throws Throwable {
        Assert.assertEquals(expected, m.invoke(c, year));
    }
}
