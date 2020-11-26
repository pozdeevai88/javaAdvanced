package ru.geekbrains;

public class Wall implements PassBarrier{
    private int height;

    public Wall (int height) {
        this.height = height;
    }

    @Override
    public boolean doJump(Movable competitor) {
        return competitor.jump(this.height);
    }

    @Override
    public boolean doRun(Movable competitor) {
        System.out.println("Can not run through the wall");
        return false;
    }
}
