package lesson_1_generics.fruits;

import java.util.ArrayList;

class Box<T extends Fruit> {
    private ArrayList<T> content = new ArrayList<>();

    @SuppressWarnings("WeakerAccess")
    ArrayList<T> getContent() {
        return content;
    }

    void put(T fruit){
        content.add(fruit);
    }

    @SuppressWarnings("WeakerAccess")
    float getWeight() {
        float weight = 0;
        for (Fruit fruit : content) {
            weight += fruit.getWeight();
        }

        return weight;
    }

    boolean compare(Box box) {
        return getWeight() == box.getWeight();
    }

    void shiftContent(Box<T> target) {
        target.getContent().addAll(getContent());
        getContent().clear();
    }

    @Override
    public String toString() {
        if (getContent().isEmpty())
            return "Коробка пуста!";
        else
            return String.format("В коробке %s - %dшт. Вес: %.1f", getContent().get(0).toString(), getContent().size(), getWeight());
    }
}
