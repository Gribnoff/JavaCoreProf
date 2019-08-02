package lesson_8_spring;

class SpiralMain {
    public static void main(String[] args) {
        Spiral spiral1 = new Spiral(5, 7);
        Spiral spiral2 = new Spiral(10, 4);
        spiral1.start();
        spiral2.start();
        spiral1.printField();
        spiral2.printField();
    }
}
