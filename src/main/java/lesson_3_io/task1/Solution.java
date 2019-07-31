package lesson_3_io.task1;

import java.io.*;

class Solution {
    public static void main(String[] args) {
        File file = new File("src/main/java/lesson_3_io/task1/file.txt");

        try (BufferedWriter out = new BufferedWriter(new FileWriter(file));
             FileInputStream in = new FileInputStream(file)) {

            if (!file.exists())
                file.createNewFile();

            int i = 0;
            while (file.length() < 50) {
                i++;
                out.write(String.format("test string #%d\n", i));
                out.flush();
            }


            byte[] buf = new byte[4096];
            int data;
            while ((data = in.read(buf)) > 0)
                System.out.print(new String(buf, 0, data));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
