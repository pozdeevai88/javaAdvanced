package ru.geekbrains;

import java.util.Arrays;

public class MainApp {
    static final int SIZE = 10000000;

    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        long n1 = method1(arr);
        long n2 = method2(arr);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println("С разделением на потоки получается быстрее в " + n1 / n2 + " раза");
    }

    private static long method1(float[] arr) {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        long timeStart = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        return (System.nanoTime() - timeStart);
    }

    public static long method2(float[] arr) {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        float[] a1 = new float[SIZE / 2];
        float[] a2 = new float[SIZE / 2];

        long timeStart = System.nanoTime();

        System.arraycopy(arr, 0, a1, 0, SIZE / 2);
        System.arraycopy(arr, SIZE / 2, a2, 0, SIZE / 2);

        ThreadsForArray tfa1 = new ThreadsForArray(a1);
        ThreadsForArray tfa2 = new ThreadsForArray(a2);

        tfa1.start();
        tfa2.start();

        try {
            tfa1.join();
            tfa2.join();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        System.arraycopy(a1, 0, arr, 0, SIZE / 2);
        System.arraycopy(a2, 0, arr, SIZE / 2, SIZE / 2);

        return (System.nanoTime() - timeStart);
    }
}
