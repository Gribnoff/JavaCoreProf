package lesson_5_multithreading.the_bay.bay;

import lesson_5_multithreading.the_bay.Ship;

import java.util.concurrent.Semaphore;

public class Strait extends MapPoint {
    private final Semaphore semaphore = new Semaphore(2);

    public void pass(Ship ship) {
        try {
            System.out.println(ship.getName() + " подплывает к узкому проливу");
            semaphore.acquire();
            System.out.println(ship.getName() + " заходит в пролив");
            Thread.sleep(2000);
            System.out.println(ship.getName() + " вышёл из пролива");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
