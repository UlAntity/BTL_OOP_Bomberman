package uet.oop.bomberman.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.bomberman.entities.*;
import uet.oop.bomberman.bomberman.graphics.Sprite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class BombermanGame extends Application {

    public static int WIDTH;
    public static int HEIGHT;
    public static Bomber bomberman;


    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Bomb> bombs;
    public static List<Flame> flameList = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<Enemy>();
    public static int level = 1;
    public static boolean nextLevel = false;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        Map.createMap();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(nextLevel) {
                    resetLevel();
                }

                if(Bomber.revive) {
                    entities.clear();
                    bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                    entities.add(bomberman);
                    bombs = bomberman.getBombs();
                }
                try {
                    render();
                    update();
                } catch (ConcurrentModificationException e) {
                    // inevitable.
                }
            }

        };


        timer.start();
        scene.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode()));
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));

        bombs = bomberman.getBombs();
    }

    public void resetLevel() {
        stillObjects.clear();
        entities.clear();
        Map.createMap();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        bombs = bomberman.getBombs();
        nextLevel = false;
    }

    public void update() throws ConcurrentModificationException {
            entities.forEach(Entity::update);
            enemies.forEach(Enemy::update);
            stillObjects.forEach(Entity::update);
            bombs.forEach(Bomb::update);
            for (Flame flame : flameList) flame.update();
            Collisions.checkCollisionFlame();
            Collisions.collisionsHandler();
            Collisions.enemyHandler();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        bombs.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        flameList.forEach(g -> g.render(gc));
    }
}