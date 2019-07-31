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
    private static boolean isNegative(int variable) {
        return (variable < 0);
    }
*/

@RunWith(Parameterized.class)
public class Method5Test {
    @Parameterized.Parameter
    public int number;

    @Parameterized.Parameter(1)
    public boolean expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {10, false},
                {0, false},
                {-5, true}
        });
    }

    private Class<?> c;
    private Method m;

    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        //noinspection deprecation
        c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        m = c.getDeclaredMethod("isNegative", int.class);
        m.setAccessible(true);
    }

    @Test
    public void test() throws Throwable {
        Assert.assertEquals(expected, m.invoke(c, number));
    }
}
