package lesson_3.io.task_serialization;

import java.io.Serializable;

class Player implements Serializable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    Player(int age, String name) {
        System.out.print("Создан объект Player: ");
        this.age = age;
        this.name = name;
        info();
    }

    void info() {
        System.out.println(String.format("объект %s: возраст - %d, имя - %s", toString(), age, name));
    }
}
