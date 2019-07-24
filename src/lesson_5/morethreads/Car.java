package lesson_5.morethreads;

class Car implements Runnable {
    private static int carsCount = 0;

    private Race race;
    private int speed;
    private String name;

    String getName() {
        return name;
    }

    int getSpeed() {
        return speed;
    }

    Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        carsCount++;
        this.name = "Участник #" + carsCount;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            race.start.countDown();
            race.start.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            race.finish.countDown();

            if (race.getWinner() == null)
                race.setWinner(this);
            race.finish.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
