package lesson7_reflection_tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;

/*
    private static void printIsPositive(int variable) {
        System.out.println("Your number is " + ((variable >= 0) ? "positive" : "negative") + "!");
    }
*/

@RunWith(Parameterized.class)
public class Method4Test {
    @Parameterized.Parameter
    public int number;

    @Parameterized.Parameter(1)
    public String expected;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        String pos = "Your number is positive!\r\n";
        String neg = "Your number is negative!\r\n";
        return Arrays.asList(new Object[][] {
                {10, pos},
                {0, pos},
                {-5, neg}
        });
    }

    private Class<?> c;
    private Method m;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        //noinspection deprecation
        c = URLClassLoader.newInstance(new URL[]{new File("B:/123").toURL()}).loadClass("DZ");
        m = c.getDeclaredMethod("printIsPositive", int.class);
        m.setAccessible(true);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void test() throws Throwable {
        m.invoke(c, number);
        Assert.assertEquals(expected, out.toString());
    }

    @After
    public void end() {
        System.setOut(System.out);
    }
}
