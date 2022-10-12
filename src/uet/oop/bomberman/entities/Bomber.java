package uet.oop.bomberman.entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import Controller.GetKey;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Movable {
    private GetKey getKey;

    boolean isMoving = false;
    String lastMove = "Down";


    public Bomber(int x, int y, Image img, Scene scene) {
        super(x, y, img);
        setSpeed(3);
        this.getKey = new GetKey(scene);
    }

    @Override
    public void update() {
        switch (lastMove) {
            case "Up":
                img = Sprite.player_up.getFxImage();
                break;
            case "Right":
                img = Sprite.player_right.getFxImage();
                break;
            case "Left":
                img = Sprite.player_left.getFxImage();
                break;
            default:
                img = Sprite.player_down.getFxImage();
                break;
        }
         if (getKey.isPressed(KeyCode.D) || getKey.isPressed(KeyCode.RIGHT)) {
            System.out.println("Right button pressed");
            if(x < (BombermanGame.WIDTH - 1.5) * Sprite.SCALED_SIZE) goRight();
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
            lastMove = "Right";
        }

        if (getKey.isPressed(KeyCode.S) || getKey.isPressed(KeyCode.DOWN)) {
            if(y < (BombermanGame.HEIGHT - 2) * Sprite.SCALED_SIZE) goDown();
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
            lastMove = "Down";
        }

        if (getKey.isPressed(KeyCode.A) || getKey.isPressed(KeyCode.LEFT)) {
            if(x > Sprite.SCALED_SIZE) goLeft();
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
            lastMove = "Left";
        }

        if (getKey.isPressed(KeyCode.W) ||  getKey.isPressed(KeyCode.UP)) {
            if(y > Sprite.SCALED_SIZE) goUp();
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
            lastMove = "Up";
        }
    }
}
