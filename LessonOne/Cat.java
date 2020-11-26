package ru.geekbrains;

public class Cat implements Movable {
    private static final int MAX_JUMP_HEIGHT = 3;
    private static final int MAX_RUN_DISTANCE = 300;

    @Override
    public boolean run(int distance) {
        if (distance > MAX_RUN_DISTANCE) return false;
        System.out.println("Cat ran");
        return true;
    }

    @Override
    public boolean jump(int height) {
        if (height > MAX_JUMP_HEIGHT) return false;
        System.out.println("Cat jumped");
        return true;
    }
}
