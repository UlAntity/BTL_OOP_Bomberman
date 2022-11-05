package uet.oop.bomberman.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.bomberman.entities.*;
import uet.oop.bomberman.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class BombermanGame extends Application {

    public static int WIDTH;
    public static int HEIGHT;
    public static Bomber bomberman;
    public static boolean gameRunning = false; // test
    //GAME TEST
    public static int gameState;
    public static final int playState = 1;
    public static final int pauseState = 2;


    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Bomb> bombs;
    public static List<Flame> flameList = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<Enemy>();
    public static int level = 1;
    public static boolean nextLevel = false;
    public static int score = 0;
    public static int time = 0;
    public static AnchorPane BarControl = new AnchorPane();
    public static Bar bar = new Bar();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        gameRunning = true;
        gameState = playState;

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        Map.createMap();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 30);
        gc = canvas.getGraphicsContext2D();
        BarControl.getChildren().addAll(new Rectangle(2,3));
        bar.setPanel();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(BarControl);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        Sound.BG.loop();


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
        if (gameState == playState)
        {
            score = Math.max(score, 0);
            bar.setPoint(score);
            bar.setLevel(level);
            bar.setRemain(enemies.size());
            bar.setTimes(time / 60);
            time++;
            entities.forEach(Entity::update);
            enemies.forEach(Enemy::update);
            stillObjects.forEach(Entity::update);
            bombs.forEach(Bomb::update);
            for (Flame flame : flameList) flame.update();
            Collisions.collisionsHandler();
            Collisions.checkCollisionFlame();
            Collisions.enemyHandler();
        }
        else {
            //nothing
        }
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
