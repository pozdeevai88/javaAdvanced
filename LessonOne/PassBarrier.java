package ru.geekbrains;

public interface PassBarrier {
    default boolean doRun(Movable competitor) {
        return true;
    }

    default boolean doJump(Movable competitor) {
        return true;
    }
}
