package lesson7_reflection_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;

/*
private static int calculate(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }
*/

@RunWith(Parameterized.class)
public class Method1Test {
    @Parameterized.Parameter
    public int[] data;

    @Parameterized.Parameter(1)
    public int expected;

    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Parameterized.Parameter(3)
    public String expectedExceptionMessage;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new int[]{1,2,3,4}, 2, null, null},
                {new int[]{4,3,2,1}, 20, null, null},
                {new int[]{1,1,1,1}, 2, null, null},
                {new int[]{1,8,4,0}, 0, ArithmeticException.class, "/ by zero"}
        });
    }

    private Class<?> c;
    private Method m;

    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        //noinspection deprecation
        c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        Class[] params = new Class[4];
        Arrays.fill(params, int.class);
        m = c.getDeclaredMethod("calculate", params);
        m.setAccessible(true);
    }

    @Test
    public void test() throws Throwable {
        if (expectedException != null) {
            thrown.expect(expectedException);
            thrown.expectMessage(expectedExceptionMessage);
        }

        try {
            Assert.assertEquals(expected, m.invoke(c, data[0], data[1], data[2], data[3]));
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }

    }
}
