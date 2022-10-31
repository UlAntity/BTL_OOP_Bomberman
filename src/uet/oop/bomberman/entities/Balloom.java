package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

import static uet.oop.bomberman.BombermanGame.enemies;

public class Balloom extends Enemy {
    String direction = randomDirection();

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(1);
        setAlive(true);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2,Sprite.balloom_left3, left++, 50).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2,Sprite.balloom_right3, right++, 50).getFxImage();
    }

    @Override
    public void dead() {
        img = Sprite.balloom_dead.getFxImage();
    }

    public String randomDirection() {
        Random randMove = new Random();
        int rand = randMove.nextInt(4) + 1;
        if (rand <= 1) {
            return "Left";
        } else if (rand <= 2) {
            return "Right";
        } else if (rand <= 3) {
            return "Up";
        } else if (rand >= 4) {
            return "Down";
        }
        return null;
    }



    @Override
    public void stay() {
        super.stay();
        direction = randomDirection();
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (direction == "Left") {
                this.goLeft();
            }
            if (direction == "Right") {
                this.goRight();
            }
            if (direction == "Up") {
                this.goUp();
            }
            if (direction == "Down") {
                this.goDown();
            }
        } else {
            if (time < 40) {
                dead();
                time++;
            } else enemies.remove(this);
        }
    }
}
