package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.enemies;

public class Enemy1 extends Enemy{
    int dir = direction();
    int count = 0;
    public Enemy1(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(1);
        setAlive(true);
    }

    @Override
    public void update() {
        if(isAlive()) {
            if (dir < 1) {
                goLeft();
            } else if (dir < 2) {
                goRight();
            } else if (dir < 3) {
                goUp();
            } else {
                goDown();
            }
        } else {
            if(count < 40) {
                img = Sprite.balloom_dead.getFxImage();
                count ++;
            } else {
                enemies.remove(this);
            }
        }
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 15).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 15).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        dir = direction();
    }

    public int direction() {
        return (int) (Math.random() * 4);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(desX + 2, desY +5, Sprite.SCALED_SIZE , Sprite.SCALED_SIZE * 3/4);
    }
}
