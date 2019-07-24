package lesson_5.morethreads;

public abstract class Stage {
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
