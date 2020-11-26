package ru.geekbrains;

public class MainApp {
    public static void main(String[] args) {

        PassBarrier[] barriers = new PassBarrier[5];
        barriers[0] = new Wall(1);
        barriers[1] = new Treadmill(200);
        barriers[2] = new Wall(3);
        barriers[3] = new Treadmill(1000);
        barriers[4] = new Wall(2);

        Movable[] competitors = new Movable[3];
        competitors[0] = new Human();
        competitors[1] = new Cat();
        competitors[2] = new Robot();

        for (Movable competitor : competitors) {
            for (PassBarrier pb : barriers) {
                if (pb.getClass().toString().equals("class ru.geekbrains.Wall")) {
                    if (!pb.doJump(competitor)) break;
                } else {
                    if (!pb.doRun(competitor)) break;
                }
            }
        }
    }
}
