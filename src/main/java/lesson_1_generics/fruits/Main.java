package lesson_1_generics.fruits;

class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>();
        for (int i = 0; i < 9; i++) {
            appleBox1.put(new Apple());
        }
//        appleBox1.put(new Orange());
        System.out.println(appleBox1.toString());

        Box<Orange> orangeBox1 = new Box<>();
        for (int i = 0; i < 9; i++) {
            orangeBox1.put(new Orange());
        }
        System.out.println(orangeBox1.toString());

        System.out.println("Эти две коробки равны по весу? - " + appleBox1.compare(orangeBox1) + "\n");

        Box<Apple> appleBox2 = new Box<>();
        for (int i = 0; i < 10; i++) {
            appleBox2.put(new Apple());
        }
        System.out.println(appleBox2.toString());

        System.out.println("Пересыпаем из первой яблочной коробки во вторую:");
//        appleBox1.shiftContent(orangeBox1);
        appleBox1.shiftContent(appleBox2);
        System.out.println(appleBox1.toString());
        System.out.println(appleBox2.toString());
    }
}
