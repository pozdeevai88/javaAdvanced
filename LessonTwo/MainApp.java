package ru.geekbrains;

import java.util.Arrays;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {

        /*
         * Задание 1
         * Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
         * При подаче массива другого размера необходимо бросить исключение MyArraySizeException.
         *
         */

        String[][] myArray = new String[4][4];
        try {
            checkArraySize(myArray);
        } catch (MyArraySizeException e) {
            System.out.println("Incorrect array size");
        }

        /*
         * Задание 2
         * Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
         * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
         * или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
         * в какой именно ячейке лежат неверные данные.
         */

        /*
         * Задание 3
         * В методе main() вызвать полученный метод, обработать возможные исключения
         * MyArraySizeException и MyArrayDataException и вывести результат расчета.
         */

        fill2DArrayWithRandom(myArray);
        int sum = 0;
        try {
            sum = sumArrayData(myArray);
        } catch (MyArrayDataException e) {
            System.out.println("Incorrect data");
        }
        System.out.println("Calculated sum = " + sum);

    }

    public static void checkArraySize(String[][] arr) {
        if ((arr.length != 4) || (arr[0].length != 4)) {
            throw new MyArraySizeException();
        }
    }

    public static int sumArrayData(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (!isInteger(arr[i][j])) {
                    throw new MyArrayDataException(i, j, arr[i][j]);
                }
                sum += Integer.parseInt(arr[i][j]);
            }
        }
        return sum;
    }

    public static void fill2DArrayWithRandom(String[][] arr) {
        Random rnd = new Random();
        String symbols = "0123456789abc";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = String.valueOf(symbols.charAt(rnd.nextInt(symbols.length())));
            }
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
