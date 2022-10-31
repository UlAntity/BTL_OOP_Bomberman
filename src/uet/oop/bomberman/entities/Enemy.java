package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Enemy extends Movable{
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }

    public void update() {

    }
}
