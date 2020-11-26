package ru.geekbrains;

public class Robot implements Movable {
    private static final int MAX_JUMP_HEIGHT = 1;
    private static final int MAX_RUN_DISTANCE = 500;

    @Override
    public boolean run(int distance) {
        if (distance > MAX_RUN_DISTANCE) return false;
        System.out.println("Robot ran");
        return true;
    }

    @Override
    public boolean jump(int height) {
        if (height > MAX_JUMP_HEIGHT) return false;
        System.out.println("Robot jumped");
        return true;
    }
}
