package uet.oop.bomberman.bomberman.entities.Enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman.entities.Enemy;
import uet.oop.bomberman.bomberman.graphics.Sprite;
import static java.lang.Math.abs;
import static uet.oop.bomberman.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.bomberman.BombermanGame.enemies;


public class Oneal extends Enemy {
    int aimingDir = direction();
    int direction = randomDirection();
    int count = 0;
    int bombEva = 0;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(2);
        setAlive(true);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 60).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 60).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left3, up++, 40).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left3, down++, 40).getFxImage();
    }


    @Override
    public void move() {
        super.move();
        count++;
        if (metBomb) bombEva++;
        if (count == 3 && !this.metBomb) {
            aimingDir = direction();
            count = 0;
        }
        if(bombEva == 60) {
            metBomb = false;
            count = 0;
            bombEva = 0;
        }

    }

    @Override
    public void stay() {
        super.stay();
        aimingDir = direction();
        direction = randomDirection();
    }

    @Override
    public void dead() {
        setAlive(false);
        if (time < 50) {
            img = Sprite.oneal_dead.getFxImage();
            time++;
        } else if (time < 120){
            super.dead();
            time++;
        } else enemies.remove(this);
    }

    private int xDirection() {
        if(this.getX() < bomberman.getX()) {
            return 1;
        } else if(this.getX() > bomberman.getX()){
            return 0;
        }
        return -1;
    }

    private int yDirection() {
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
            if (xDirection() != -1)
                return xDirection();
            else
                return yDirection();

        } else {
            if (yDirection() != -1)
                return yDirection();
            else
                return xDirection();
        }
    }



    @Override
    public void update() {
        if(isAlive()) {
            if (abs(this.getY() - bomberman.getY()) < 100 && abs(this.getX() - bomberman.getX()) < 100 && !metBomb) {
                if (aimingDir == 0) goLeft();
                if (aimingDir == 1) goRight();
                if (aimingDir == 2) goUp();
                if (aimingDir == 3) goDown();
            } else {
                if (direction == 0) goLeft();
                if (direction == 1) goRight();
                if (direction == 2) goUp();
                if (direction == 3) goDown();
            }
        } else dead();
    }
}
