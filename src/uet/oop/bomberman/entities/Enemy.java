package uet.oop.bomberman.entities;


import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;


public abstract class Enemy extends Movable{

    public boolean metBomb = false;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }


    public void dead() {
        img = Sprite.movingSprite(Sprite.mob_dead1,Sprite.mob_dead2, Sprite.mob_dead3, deadTime++, 90).getFxImage();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(desX + 4, desY + 4, Sprite.SCALED_SIZE * 9/10, Sprite.SCALED_SIZE * 9/10);
    }

    //random int from 1 to 4
    public int randomDirection() {
        Random randMove = new Random();
        return randMove.nextInt(4) ;
    }
}
