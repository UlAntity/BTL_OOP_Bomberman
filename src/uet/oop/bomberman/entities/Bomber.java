package uet.oop.bomberman.entities;

import Controller.Collision;
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
import uet.oop.bomberman.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends Movable {
    private GetKey getKey;

    boolean isMoving = false;
    String lastMove = "Down";
    private final List<Bomb> bombs = new ArrayList<>();


    public Bomber(int x, int y, Image img, Scene scene) {
        super(x, y, img);
        setSpeed(3);
        this.getKey = new GetKey(scene);
    }

    @Override
    public void update() {
        int mul = Sprite.SCALED_SIZE;
        int centerX = x + mul / 3;
        int centerY = y + mul / 2;
        int diffX = Math.abs(centerX - Math.round(centerX / mul) * mul);
        boolean inlineX = diffX > 3 && diffX < 29;
        int diffY = Math.abs(centerY - Math.round(centerY / mul) * mul);
        boolean inlineY = diffY > 10 && diffY < 22;

        System.out.println(centerX + " " + centerY + " " + inlineX + " " + diffY);
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
            if(Map.getObject(Math.round((centerX + 10) / mul), Math.round(centerY / mul)) instanceof Grass && inlineY) goRight();
            img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
            lastMove = "Right";
        }

        if (getKey.isPressed(KeyCode.S) || getKey.isPressed(KeyCode.DOWN)) {
            if(Map.getObject(Math.round((centerX) / mul), Math.round((centerY + 16) / mul)) instanceof Grass && inlineX) goDown();
            img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
            lastMove = "Down";
        }

        if (getKey.isPressed(KeyCode.A) || getKey.isPressed(KeyCode.LEFT)) {
            if(Map.getObject(Math.round((centerX - 10) / mul), Math.round(centerY / mul)) instanceof Grass && inlineY) goLeft();
            img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
            lastMove = "Left";
        }

        if (getKey.isPressed(KeyCode.W) ||  getKey.isPressed(KeyCode.UP)) {
            if(Map.getObject(Math.round((centerX) / mul), Math.round((centerY - 16) / mul)) instanceof Grass && inlineX) goUp();
            img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
            lastMove = "Up";
        }

        if(getKey.isPressed(KeyCode.SPACE)) {
            placeBomb();
            System.out.println("place");
        }
    }

    public void placeBomb() {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage()));
    }

    public List<Bomb> getBombs() { // trả về list bomb
        return bombs;
    }
}
