package lesson_5_multithreading.the_bay.bay;

import lesson_5_multithreading.the_bay.Ship;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LoadPort extends MapPoint {
    private String type;
    private ConcurrentHashMap<String, Integer> goodsStored = new ConcurrentHashMap<>();
    private boolean empty;
    private ReentrantLock lock = new ReentrantLock();

    private static ArrayList<LoadPort> loadPorts = new ArrayList<>();

    public Map<String, Integer> getGoodsStored() {
        return goodsStored;
    }

    static ArrayList<LoadPort> getLoadPorts() {
        return loadPorts;
    }

    boolean isEmpty() {
        return empty;
    }

    public LoadPort(String type, int quantity) {
        this.type = type;
        goodsStored.put(type, quantity);
        empty = quantity == 0;
        loadPorts.add(this);
        description = String.format("порт(%s)", type);
    }

    public void pass(Ship ship) throws InterruptedException {
        if (ship.isLoaded() || empty || lock.isLocked())
            return;

        lock.lock();

        int goodsToLoad = (goodsStored.get(type) > ship.getLoadCapacity()) ? ship.getLoadCapacity() : goodsStored.get(type);
        Thread.sleep(1000 * goodsToLoad / ship.getLoadSpeed());
        ship.loadWith(type, goodsToLoad);
        goodsStored.put(type, goodsStored.get(type) - goodsToLoad);
        System.out.println(String.format("%s загрузил на борт %s(%d ед.)", ship.getName(), type, goodsToLoad));

        info();
        empty = goodsStored.get(type) == 0;
        lock.unlock();
    }

    void info() {
        System.out.println(description + goodsStored);
    }
}
