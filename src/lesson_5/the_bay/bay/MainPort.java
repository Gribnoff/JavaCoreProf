package lesson_5.the_bay.bay;

import lesson_5.the_bay.Ship;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainPort extends MapPoint {
    private ConcurrentHashMap<String, Integer> goodsStored = new ConcurrentHashMap<>();

    public MainPort() {
        goodsStored.put("топливо", 0);
        goodsStored.put("еда", 0);
        goodsStored.put("одежда", 0);
        description = "главный порт";
    }

    public void pass(Ship ship) throws InterruptedException {
        for (Map.Entry<String, Integer> entry : ship.getGoodsOnBoard().entrySet()) {
            if (entry.getValue() == 0)
                continue;

            goodsStored.put(entry.getKey(), goodsStored.get(entry.getKey()) + entry.getValue());
            Thread.sleep(1000 * entry.getValue() / ship.getLoadSpeed());
            System.out.println(String.format("%s выгрузил в главный порт %s(%d ед.)", ship.getName(), entry.getKey(), entry.getValue()));
            ship.loadWith(entry.getKey(), 0);
            info();
        }
    }

    public void info() {
        System.out.println(description + goodsStored);
    }
}
