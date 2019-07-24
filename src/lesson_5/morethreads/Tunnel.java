package lesson_5.morethreads;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore sem;

    Tunnel(int length) {
        super(length);
        this.description = "Тоннель " + length + " метров";
        sem = new Semaphore(2);
    }

    @Override
    public void go(Car c) {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                sem.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
                System.out.println(c.getName() + " закончил этап: " + description);
                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
