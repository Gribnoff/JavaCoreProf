package lesson_8_spring;

class Spiral {
    private int width;
    private int height;
    private int cells;
    private int printNumber; //текущее число
    private int turn; //количество сделанных обходов периметра(полный обход = все четыре стенки массива)
    private int lengthReduction; //уменьшение длины стенки, каждые два хода массив "сокращается" на 1 столбец и 1 строку
    private int[][] field;

    Spiral(int width, int height) {
        this.width = width;
        this.height = height;
        cells = width * height;
        printNumber = 0;
        turn = 0;
        lengthReduction = 0;
        field = new int[height][width];
    }

    void start() {
        goRight();
    }

    private void goRight() {
        for (int i = turn; i < (width - lengthReduction + turn); i++) {
            field[turn][i] = ++printNumber;
        }

        if (!isLastNumber())
            goDown();
    }

    private void goDown() {
        lengthReduction++;
        for (int i = turn + 1; i < (height - lengthReduction + turn + 1); i++) {
            field[i][width - turn - 1] = ++printNumber;
        }

        if (!isLastNumber())
            goLeft();
    }

    private void goLeft() {
        for (int i = width - 2 - turn; i >= turn; i--) {
            field[height - 1 - turn][i] = ++printNumber;
        }

        if (!isLastNumber())
            goUp();
    }

    private void goUp() {
        lengthReduction++;
        for (int i = height - turn - 2; i > turn; i--) {
            field[i][turn] = ++printNumber;
        }

        if (!isLastNumber()) {
            turn++;
            goRight();
        }
    }

    private boolean isLastNumber(){
        return printNumber == cells;
    }

    void printField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf("%3d ", field[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
