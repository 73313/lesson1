package lesson4;
import java.util.Random;
import java.util.Scanner;

public class lesson4 {
   // public static int n = 5;
    public static int DOTS_TO_WIN=3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] feald;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();
    public static void main(String[] args) {
        initMap(3);
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin()==DOT_X ){
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin()==DOT_O) {
                System.out.println("Победил Компьютер");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void initMap(int n) {
        feald = new char[n][n];

        for (int i = 0; i < feald.length; i++) {
            for (int j = 0; j < feald.length; j++) {
                feald[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i <= feald.length ; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < feald.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < feald.length; j++) {
                System.out.print(feald[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static char checkWin(){

        char symbol = DOT_EMPTY;
        for (int i = 0; i < feald.length; i++) { // проход по оси Y
            for (int j = 0; j < feald.length; j++) { //проход по оси X
                symbol = feald[j][i];
                if (checkLine(j, i, 1, 0, symbol) ||
                        checkLine(j, i, 0, 1, symbol) ||
                        checkLine(j, i, 1, 1, symbol) ||
                        checkLine(j, i, 1, -1, symbol)) {
                    return symbol;
                }
            }
        }
        return DOT_EMPTY;
    }
    // x, y - позиция на поле, deltaX, deltaY - смещение при проверке линии
    private static boolean checkLine(int x, int y, int deltaX, int deltaY, char symbol) {
        // 1) проверка - поместится линия на поле или нет
        int lastX = x + (DOTS_TO_WIN - 1) * deltaX; // координата X последней точки в ряду
        int lastY = y + (DOTS_TO_WIN - 1) * deltaY; // координата Y последней точки в ряду
        if (lastX < 0 || lastX >= feald.length || lastY < 0 || lastY >= feald.length) return false; // выходит за пределы массива

        // 2) проверка - все ли символы в линии одинаковые
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            x += deltaX;
            y += deltaY;
            if (feald[x][y] != symbol) return false;
        }
        return true;
    }


    /* if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
     if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
     if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
     if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
     if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
     if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
     if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
     if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
     return false;
 }*/
    public static boolean isMapFull() {
        for (int i = 0; i < feald.length; i++) {
            for (int j = 0; j < feald.length; j++) {
                if (feald[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(feald.length);
            y = rand.nextInt(feald.length);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        feald[y][x] = DOT_O;
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        feald[y][x] = DOT_X;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= feald.length || y < 0 || y >= feald.length) return false;
        if (feald[y][x] == DOT_EMPTY) return true;
        return false;
    }

}




