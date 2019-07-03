package lesson_1.generics;

public class Solution1 {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 4, 5};

        printArray(array);
        swap(array, 0, 4);
    }

    private static void printArray(Object[] array) {
        for (Object o : array) {
            System.out.print(o + " | ");
        }
        System.out.println();
    }

    @SuppressWarnings("SameParameterValue")
    private static void swap(Object[] array, int first, int second) {
        try {
            Object buf = array[first];
            array[first] = array[second];
            array[second] = buf;
            printArray(array);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("Индекс за пределами массива в строке ");
            System.err.println(e.getStackTrace()[1]);
        }
    }
}
