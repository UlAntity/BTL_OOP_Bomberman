package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import Controller.GetKey;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Bomber extends Movable {
    private GetKey getKey;

    private KeyCode direction = null;
    private boolean placeBombCommand = false;
    private final List<Bomb> bombs = new ArrayList<>();
    protected int radius;
    boolean bombable;
    boolean infibomb;
    public static boolean revive;
    protected int dieHandler = 0;


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
        setSpeed(3);
        setRadius(1);
        bombable = true;
        infibomb = false;
        revive = false;
        setAlive(true);
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void update() {
        this.move();
        if (direction == KeyCode.LEFT) {
            goLeft();
        }
        if (direction == KeyCode.RIGHT) {
            goRight();
        }
        if (direction == KeyCode.UP) {
            goUp();
        }
        if (direction == KeyCode.DOWN) {
            goDown();
        }
        if (placeBombCommand) {
            if(bombable || infibomb) {
                placeBomb();
            }
        }
        if(!isAlive()) {
            dieHandler++;
            bomberDie();
        }
    }

    public void bomberDie() {
        this.stay();
        if(dieHandler <= 60) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, dieHandler, 20).getFxImage();
        } else revive = true;

    }
    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = true;
        }
    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (direction == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (direction == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (direction == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
            direction = null;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = false;
        }
    }

    public void placeBomb() {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius));
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
    }


    public Rectangle getBounds() { // tạo bao cho bomber
        return new Rectangle(desX + 2, desY + 5, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE *3/4);
    }

    public List<Bomb> getBombs() { // trả về list bomb
        return bombs;
    }
}
