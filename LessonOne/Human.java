package ru.geekbrains;

public class Human implements Movable, PassBarrier {
    private static final int MAX_JUMP_HEIGHT = 2;
    private static final int MAX_RUN_DISTANCE = 5000;

    @Override
    public boolean run(int distance) {
        if (distance > MAX_RUN_DISTANCE) return false;
        System.out.println("Human ran");
        return true;
    }

    @Override
    public boolean jump(int height) {
        if (height > MAX_JUMP_HEIGHT) return false;
        System.out.println("Human jumped");
        return true;
    }
}
