package lesson_5.the_bay;

import lesson_5.the_bay.bay.Bay;
import lesson_5.the_bay.bay.MapPoint;

import java.util.HashMap;
import java.util.Map;

public class Ship implements Runnable{
    private static int shipsCount = 0;

    private final int loadSpeed = 100;
    private final int loadCapacity;
    private String name;
    private boolean loaded = false;

    private Bay bay;
    private Map<String, Integer> goodsOnBoard = new HashMap<>();

    public int getLoadSpeed() {
        return loadSpeed;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getGoodsOnBoard() {
        return goodsOnBoard;
    }

    public void loadWith(String goodsOnBoard, int weightOfGoods) {
        this.goodsOnBoard.put(goodsOnBoard, weightOfGoods);
        loaded = weightOfGoods != 0;
    }

    Ship(Bay bay, int loadCapacity) {
        this.bay = bay;
        this.loadCapacity = loadCapacity;
        shipsCount++;
        name = "Корабль №" + shipsCount;
        goodsOnBoard.put("топливо", 0);
        goodsOnBoard.put("еда", 0);
        goodsOnBoard.put("одежда", 0);
    }

    @Override
    public void run() {
        while (!bay.isJobDone()) {
            System.out.println(name + " отплывает из главного порта");
            for (MapPoint point : bay.getPoints()) {
                try {
                    point.pass(this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
