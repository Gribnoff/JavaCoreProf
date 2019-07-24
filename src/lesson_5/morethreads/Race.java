package lesson_5.morethreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

class Race {
    private ArrayList<Stage> stages;
    CountDownLatch start;
    CountDownLatch finish;
    private Car winner;

    ArrayList<Stage> getStages() {
        return stages;
    }

    Car getWinner() {
        return winner;
    }

    void setWinner(Car winner) {
        this.winner = winner;
    }

    Race(int carsCount, Stage... stages) {
        start = new CountDownLatch(carsCount);
        finish = new CountDownLatch(carsCount);
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
