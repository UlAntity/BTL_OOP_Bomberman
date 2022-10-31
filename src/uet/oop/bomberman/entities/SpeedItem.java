package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class SpeedItem extends Item {
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
    }

    @Override
    public void change() {
        int i = bomberman.getSpeed();
        bomberman.setSpeed(i + 1);
    }


}