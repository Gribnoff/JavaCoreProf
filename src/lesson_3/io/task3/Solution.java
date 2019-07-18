package lesson_3.io.task3;

import java.io.*;
import java.util.Scanner;

class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "src/lesson_3/io/task3/book.txt";
        final long bookSize = 10 * 1024 * 1024;
        final int pageLength = 1800;

        try (RandomAccessFile file = new RandomAccessFile(path, "r")) {
            createBook(path, bookSize);

            while (true) {
                System.out.println("Введите номер страницы(0 - для выхода)");
                int page = sc.nextInt();
                if (page == 0)
                    break;
                else {
                    long start = System.currentTimeMillis();
                    file.seek(pageLength * page - pageLength);

                    for (int i = 0; i < pageLength; i++)
                        System.out.print((char) file.read());

                    System.out.println("\nВремя чтения " + (System.currentTimeMillis() - start));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createBook(String path, long size) {
        File file = new File(path);

        long start = System.currentTimeMillis();
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            if (!file.exists())
                file.createNewFile();

            int i = 0;
            while (file.length() < size) {
                i++;
                out.write(String.format("this is test string #%6d for book filling(made it soooo looooong to reduce write time)\n", i));
                if (i % 17 == 0) {
                    for (int j = 0; j < 89; j++) out.write("-");
                    out.write("\n");
                    for (int j = 0; j < 40; j++) out.write("-");
                    out.write(String.format("Page#%4d", i / 17));
                    for (int j = 0; j < 40; j++) out.write("-");
                    out.write("\n");
                    for (int j = 0; j < 89; j++) out.write("-");
                    out.write("\n");
                    out.flush();
                }
            }

            System.out.println("Всего страниц в книге: " + i / 17);

        } catch (IOException e) {e.printStackTrace();}

        System.out.println("Время создания книги: " + (System.currentTimeMillis() - start));
    }
}
