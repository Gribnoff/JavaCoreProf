import lesson_6.logsandtests.task2.Solution;
import org.junit.Assert;
import org.junit.Test;

public class L6T2 {
    private static final int key = 4;

    @Test
    public void normalTest1() {
        int[] array = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] expected = {1, 7};
        Assert.assertArrayEquals(expected, Solution.met(array, key));
    }

    @Test
    public void normalTest2() {
        int[] array = {4, 4, 4};
        int[] expected = {};
        Assert.assertArrayEquals(expected, Solution.met(array, key));
    }

    @Test(expected = NullPointerException.class)
    public void nullTest() {
        int[] array = null;
        int[] expected = {};
        Assert.assertArrayEquals(expected, Solution.met(array, key));
    }

    @Test(expected = RuntimeException.class)
    public void noKeyTest() {
        int[] array = {1, 2, 2, 3, 1, 7};
        int[] expected = {};
        Assert.assertArrayEquals(expected, Solution.met(array, key));
    }
}
