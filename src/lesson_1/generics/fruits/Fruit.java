package lesson_1.generics.fruits;

abstract class Fruit {
    private float weight;

    Fruit(float weight) {
        this.weight = weight;
    }

    float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "фрукт";
    }
}

class Apple extends Fruit {
    Apple() {
        super(1.0f);
    }

    @Override
    public String toString() {
        return "яблоки";
    }
}

class Orange extends Fruit{
    Orange() {
        super(1.5f);
    }

    @Override
    public String toString() {
        return "апельсины";
    }
}