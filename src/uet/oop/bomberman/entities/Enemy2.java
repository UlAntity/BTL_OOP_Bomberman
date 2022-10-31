package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.enemies;

public class Enemy2 extends Enemy{
    int count = 0;
    int dir = direction();
    int tempX;
    public Enemy2(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(2);
        setAlive(true);
    }

    @Override
    public void update() {
        if(isAlive()) {
            count++;
            if (count == 30) {
                count = 0;
                if(tempX == x) {
                    dir = 1;
                } else {
                    tempX = x;
                }
            }
            if (dir == 0) goLeft();
            if (dir == 1) goRight();
            if (dir == 2) goUp();
            if (dir == 3) goDown();
            System.out.println(dir);
        } else {
            if(count < 40) {
                img = Sprite.oneal_dead.getFxImage();
                count ++;
            } else {
                enemies.remove(this);
            }
        }
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 20).getFxImage();
    }


    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 20).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 15).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        dir = direction();
    }

    private int Xdirection() {
        if(this.getX() < bomberman.getX()) {
            return 1;
        } else if(this.getX() > bomberman.getX()){
            return 0;
        }
        return -1;
    }

    private int Ydirection() {
        if(this.getY() < bomberman.getY()) {
            return 3;
        } else if(this.getY() > bomberman.getY()){
            return 2;
        }
        return -1;
    }

    public int direction() {
        int vertical = (int) (Math.random() * 2);

        if (vertical == 1) {
            if (Xdirection() != -1)
                return Xdirection();
            else
                return Ydirection();

        } else {
            if (Ydirection() != -1)
                return Ydirection();
            else
                return Xdirection();
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(desX + 5, desY + 5, Sprite.SCALED_SIZE * 3/4, Sprite.SCALED_SIZE * 3/4);
    }
}
