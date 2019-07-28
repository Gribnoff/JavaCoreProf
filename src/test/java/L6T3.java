import lesson_6.logsandtests.task3.Solution;
import org.junit.Assert;
import org.junit.Test;

public class L6T3 {
    private static final int[] keys = {1, 4};

    @Test
    public void normalTest() {
        int[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Assert.assertTrue(Solution.met(array, keys));
    }

    @Test
    public void noKeyTest1() {
        int[] array = {4, 4, 4};
        Assert.assertFalse(Solution.met(array, keys));
    }

    @Test
    public void noKeyTest2() {
        int[] array = {1, 2, 2, 3, 1, 7};
        Assert.assertFalse(Solution.met(array, keys));
    }

    @Test(expected = NullPointerException.class)
    public void nullTest() {
        int[] array = null;
        Assert.assertFalse(Solution.met(array, keys));
    }

    @Test(expected = NullPointerException.class)
    public void emptyTest() {
        int[] array = {};
        Assert.assertFalse(Solution.met(array, keys));
    }
}
