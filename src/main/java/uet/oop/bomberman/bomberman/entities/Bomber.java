package uet.oop.bomberman.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.bomberman.BombermanGame;
import uet.oop.bomberman.bomberman.Sound;
import uet.oop.bomberman.Controller.GetKey;
import uet.oop.bomberman.bomberman.graphics.Sprite;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static uet.oop.bomberman.bomberman.BombermanGame.pauseState;
import static uet.oop.bomberman.bomberman.BombermanGame.playState;


public class Bomber extends Movable {

    private KeyCode direction = null;
    private boolean placeBombCommand = false;
    private final List<Bomb> bombs = new ArrayList<>();
    protected int radius;
    boolean bombable;
    public boolean isInfibomb = false;
    public boolean infibomb;
    public boolean invincible = false;
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
            if(!isInfibomb) {
                if(bombable) {
                    placeBomb();
                }
            } else if (infibomb) {
                placeBomb();
            }
        }
        if(!isAlive()) {
            dieHandler++;
            bomberDie();
        }
    }

    public void bomberDie() {
        Sound.Die.play();
        BombermanGame.score -= 1;
        this.stay();
        if(dieHandler <= 60) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, dieHandler, 20).getFxImage();
        } else revive = true;

    }
    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (BombermanGame.gameState == playState) {
            if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                    || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
                this.direction = keyCode;
            }
            if (keyCode == KeyCode.SPACE) {
                placeBombCommand = true;
            }
        }

    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode && BombermanGame.gameState == playState) {
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

        if (keyCode == KeyCode.I) {
            invincible = !invincible;
        }

        if (keyCode == KeyCode.P) {
            if (BombermanGame.gameState == playState) {
                BombermanGame.gameState = pauseState;
                Sound.BG.pauseMusic();
                //BombermanGame.pauseGame();
            } else if (BombermanGame.gameState == pauseState) {
                BombermanGame.gameState = playState;
                Sound.BG.continueMusic();
            }
        }

    }

    public void placeBomb() {
        Sound.plant.play();
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
