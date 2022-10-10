package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Movable extends Entity{

    protected int right = 0;
    protected int left = 0;
    protected int up = 0;
    protected int down = 0;

    protected int speed;

    public Movable(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goRight() {
        this.x += speed;
    }

    public void goLeft() {
        this.x -= speed;
    }

    public void goUp() {
        this.y -= speed;
    }

    public void goDown() {
        this.y += speed;
    }
}
