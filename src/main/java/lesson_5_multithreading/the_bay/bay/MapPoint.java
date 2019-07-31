package lesson_5_multithreading.the_bay.bay;

import lesson_5_multithreading.the_bay.Ship;

public abstract class MapPoint {
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void pass(Ship ship) throws InterruptedException;
}
