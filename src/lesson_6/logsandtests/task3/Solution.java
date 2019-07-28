package lesson_6.logsandtests.task3;

import java.util.Arrays;

public class Solution {
    public static boolean met(int[] arr, int... keys) {
        if (arr == null || arr.length == 0)
            throw new NullPointerException("Пустой массив!");

        boolean result = true;

        for (int key : keys) {
            if (Arrays.stream(arr).noneMatch(i -> i == key)) {
                result = false;
                System.err.println((String.format("В массиве нет нужного числа(%d)", key)));
            }
        }

        return result;
    }
}
