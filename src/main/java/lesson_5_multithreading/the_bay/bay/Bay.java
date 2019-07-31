package lesson_5_multithreading.the_bay.bay;

import java.util.ArrayList;
import java.util.Arrays;

public class Bay {
    private ArrayList<MapPoint> points;

    public ArrayList<MapPoint> getPoints() {
        return points;
    }

    public Bay(MapPoint... points) {
        this.points = new ArrayList<>(Arrays.asList(points));
    }

    public boolean isJobDone() {
        boolean result = true;
        for (LoadPort port: LoadPort.getLoadPorts()) {
            if (!port.isEmpty())
                result = false;
        }
        return result;
    }
}
