package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Enemy1 extends Movable{
    public Enemy1(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(3);
        setLayer(1);
    }

    public void update() {
        this.move();
        this.goLeft();
    }
}
