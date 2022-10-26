package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Movable extends Entity{

    protected int right = 0;
    protected int left = 0;
    protected int up = 0;
    protected int down = 0;

    protected static int speed;

    int desX = x;
    int desY = y;

    public Movable(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    public static void setSpeed(int speed) {
        Movable.speed = speed;
    }

    public void goRight() {
        desX = x + speed;
    }

    public void goLeft() {
        desX = x - speed;
    }

    public void goUp() {
        desY = y - speed;
    }

    public void goDown() {
        desY = y + speed;
    }

    public void move() {
        x = desX;
        y = desY;
    }

    public void stay() {
        desX = x;
        desY = y;
    }
}
