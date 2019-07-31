package lesson_4_multithreading;

class Solution {
    public static void main(String[] args) {
        final int MAX_THREADS = 3;
        final int REPEATS = 5;
        char[] charSeq = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, MAX_THREADS).toCharArray();

        Printer printer = new Printer(charSeq);
        new Thread(new MyThread(printer, charSeq, MAX_THREADS, REPEATS)).start();
    }
}

class MyThread implements Runnable {
    private Printer printer;
    private int max;
    private char c;
    private static int threadNumber = 0;


    MyThread(Printer printer, char[] charSeq, int maxCount, int max) {
        this.printer = printer;
        this.max = max;
        c = charSeq[threadNumber];
        threadNumber++;
        if (threadNumber < maxCount)
            new Thread(new MyThread(printer, charSeq, maxCount, max)).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            printer.print(c);
        }
    }
}

class Printer {
    private char[] charSeq;
    private volatile static int num = 0;

    Printer(char[] charSeq) {
        this.charSeq = charSeq;
    }

    synchronized void print(char c) {
        while (c != charSeq[num]) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(c);

        num++;
        if (num == charSeq.length)
            num = 0;

        notifyAll();
    }

}
