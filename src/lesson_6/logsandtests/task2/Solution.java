package lesson_6.logsandtests.task2;

import java.util.Arrays;

public class Solution {
    public static int[] met(int[] arr, final int key) {
        if (arr == null || arr.length == 0)
            throw new NullPointerException("Пустой массив!");

        if (Arrays.stream(arr).noneMatch(i -> i == key))
            throw new RuntimeException(String.format("В массиве нет нужного числа(%d)", key));

        int[] result = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != key)
                continue;

            result = new int[arr.length - i - 1];
            System.arraycopy(arr, i + 1, result, 0, result.length);
            break;
        }

        return result;
    }
}
