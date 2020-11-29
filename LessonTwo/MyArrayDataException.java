package ru.geekbrains;

public class MyArrayDataException extends RuntimeException{

    MyArrayDataException(int i, int j, String val) {
        System.out.println("Can not parse symbol '" + val + "' at position [" + i + "," + j + "]");
    }

}
