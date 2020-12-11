package ru.geekbrains;

public class ThreadsForArray extends Thread{
    float[] arr;

    public ThreadsForArray (float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length/2; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i/5) * Math.cos(0.2f + i/5) *
                    Math.cos(0.4f + i/2));

        }
    }
}
