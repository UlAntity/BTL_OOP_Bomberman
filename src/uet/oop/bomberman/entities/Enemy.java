package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends Movable{
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }


    public abstract void dead();

    @Override
    public Rectangle getBounds() {
        return new Rectangle(desX + 2, desY +2, Sprite.SCALED_SIZE , Sprite.SCALED_SIZE * 9/10);
    }
}
