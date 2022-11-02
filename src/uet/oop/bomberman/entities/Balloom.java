package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.enemies;

public class Balloom extends Enemy {
    int direction = randomDirection();

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(1);
        setAlive(true);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,Sprite.balloom_left3, left++, 60).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,Sprite.balloom_right3, right++, 60).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,Sprite.balloom_left3, up++, 60).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,Sprite.balloom_right3, down++, 60).getFxImage();
    }

    @Override
    public void dead() {
        setAlive(false);
        if (time < 50) {
            img = Sprite.balloom_dead.getFxImage();
            time++;
        } else if (time < 120){
            super.dead();
            time++;
        } else enemies.remove(this);
    }

    @Override
    public void stay() {
        super.stay();
        direction = randomDirection();
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (direction == 0) {
                this.goLeft();
            }
            if (direction == 1) {
                this.goRight();
            }
            if (direction == 2) {
                this.goUp();
            }
            if (direction == 3) {
                this.goDown();
            }
        } else dead();
    }
}
