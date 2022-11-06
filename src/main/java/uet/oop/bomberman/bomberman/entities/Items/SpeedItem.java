package uet.oop.bomberman.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman.entities.Item;

import static uet.oop.bomberman.bomberman.BombermanGame.bomberman;

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