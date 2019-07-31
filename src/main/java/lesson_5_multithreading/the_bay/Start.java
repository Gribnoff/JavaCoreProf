package lesson_5_multithreading.the_bay;

import lesson_5_multithreading.the_bay.bay.Bay;
import lesson_5_multithreading.the_bay.bay.LoadPort;
import lesson_5_multithreading.the_bay.bay.MainPort;
import lesson_5_multithreading.the_bay.bay.Strait;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Start {
    private static final int SHIPS_COUNT = 5;

    public static void main(String[] args) {
        Strait strait = new Strait();
        Bay bay = new Bay(strait, new LoadPort("топливо", 8500),
                new LoadPort("еда", 5900), new LoadPort("одежда", 2700),
                strait, new MainPort());

        ExecutorService ships = Executors.newFixedThreadPool(SHIPS_COUNT);
        for (int i = 0; i < SHIPS_COUNT; i++) {
            ships.execute(new Ship(bay, 100 + (int)(Math.random() * 100)));
        }
        ships.shutdown();
    }
}
