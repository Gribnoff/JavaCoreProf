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
    private static float calculate(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }
*/

@RunWith(Parameterized.class)
public class Method2Test {
    @Parameterized.Parameter
    public float[] data;

    @Parameterized.Parameter(1)
    public float expected;

    @Parameterized.Parameter(2)
    public Class<? extends Exception> expectedException;

    @Parameterized.Parameter(3)
    public String expectedExceptionMessage;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new float[]{1f,2f,3f,4f}, 2.75f, null, null},
                {new float[]{4f,3f,2f,1f}, 20f, null, null},
                {new float[]{1f,1f,1f,1f}, 2f, null, null},
                {new float[]{1f,8f,4f,0f}, Float.POSITIVE_INFINITY, null, null}
        });
    }

    private Class<?> c;
    private Method m;

    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        //noinspection deprecation
        c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        Class[] params = new Class[4];
        Arrays.fill(params, float.class);
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
