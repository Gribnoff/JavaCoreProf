package lesson_5_multithreading.morethreads;

abstract class Stage {
    int length;
    String description;

    Stage(int length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}
