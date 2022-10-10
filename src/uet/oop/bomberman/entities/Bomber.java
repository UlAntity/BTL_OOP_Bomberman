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

public class Bomber extends Entity {
    private GetKey getKey;
    private int speed = 5;
    public Bomber(int x, int y, Image img, Scene scene) {
        super(x, y, img);
        this.getKey = new GetKey(scene);
    }

    @Override
    public void update() {
        if (getKey.isPressed(KeyCode.D) || getKey.isPressed(KeyCode.RIGHT)) {
            System.out.println("Right button pressed");
            if(x < 590) x += speed;
            System.out.println(x);
        }

        if (getKey.isPressed(KeyCode.S) || getKey.isPressed(KeyCode.DOWN)) {
            System.out.println("Down button pressed");
            if(y < 420) y += speed;
            System.out.println(y);
        }

        if (getKey.isPressed(KeyCode.A) || getKey.isPressed(KeyCode.LEFT)) {
            System.out.println("Left button pressed");
            if(x > 22) x -= speed;
            System.out.println(x);
        }

        if (getKey.isPressed(KeyCode.W) ||  getKey.isPressed(KeyCode.UP)) {
            System.out.println("Up button pressed");
            if(y > 22) y -= speed;
            System.out.println(y);
        }
    }
}
