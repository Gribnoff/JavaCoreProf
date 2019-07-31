package lesson_1_generics;

import java.util.ArrayList;
import java.util.Arrays;

class Solution2 {
    public static void main(String[] args) {
        String[] array = {"String #1", "Строка №2", "str #3", "s #4", "this is string, too"};
        ArrayList list = castToArrayList(array);
        printArrayList(list);
    }

    private static ArrayList castToArrayList(Object[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    private static void printArrayList(ArrayList list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
}
