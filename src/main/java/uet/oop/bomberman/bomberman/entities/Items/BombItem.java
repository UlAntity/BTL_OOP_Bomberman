package uet.oop.bomberman.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman.entities.Item;

import static uet.oop.bomberman.bomberman.BombermanGame.bomberman;

public class BombItem extends Item {
    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }

    @Override
    public void change() {
        bomberman.isInfibomb = true;
        bomberman.infibomb = true;
    }

    @Override
    public void update() {

    }
}
