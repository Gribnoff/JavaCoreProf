package lesson_3_io.task2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


class Solution {
    public static void main(String[] args) {
        BufferedWriter br = null;

        InputStream in = null;
        OutputStream out = null;

        try {
            File file;

            ArrayList<File> fal = new ArrayList<>();
            int numOfFiles = 5;

            for (int i = 1; i <= numOfFiles; i++) {
                file = new File(String.format("src/main/java/lesson_3_io/task2/file%d.txt", i));
                fal.add(file);
                if (!file.exists())
                    file.createNewFile();

                br = new BufferedWriter(new FileWriter(file));
                int j = 0;
                while (file.length() < 100) {
                    j++;
                    br.write(String.format("test string #%d of data file #%d\n", j, i));
                    br.flush();
                }
            }


            file = new File("src/main/java/lesson_3_io/task2/result.txt");
            if (!file.exists())
                file.createNewFile();

            ArrayList<InputStream> isal = new ArrayList<>();
            for (File f : fal) {
                isal.add(new FileInputStream(f));
            }

            in = new SequenceInputStream(Collections.enumeration(isal));
            out = new FileOutputStream(file);
            int x;
            while ((x = in.read()) != -1)
                out.write(x);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
