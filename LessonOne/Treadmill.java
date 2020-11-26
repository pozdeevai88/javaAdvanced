package ru.geekbrains;

public class Treadmill implements PassBarrier{
    private int distance;

    public Treadmill (int distance) {
        this.distance = distance;
    }

    @Override
    public boolean doJump(Movable competitor) {
        System.out.println("Can not jump on treadmill");
        return false;
    }

    @Override
    public boolean doRun(Movable competitor) {
        return competitor.run(this.distance);
    }
}
