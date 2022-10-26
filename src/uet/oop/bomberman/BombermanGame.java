package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import uet.oop.bomberman.Map;

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
    public static int level = 1;
    public static boolean nextLevel = false;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
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
                    stillObjects.clear();
                    entities.clear();
                    Map.createMap();
                    bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                    entities.add(bomberman);
                    nextLevel = false;
                }
                render();
                update();
            }
        };


        timer.start();
        scene.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode()));
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        System.out.println(Map.getObject(1,1));
        bombs = bomberman.getBombs();
    }


    public void update() {
        System.out.println(level);
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        bombs.forEach(Bomb::update);
        for (int i = 0; i < flameList.size(); i++)
            flameList.get(i).update();
        Collisions.checkCollisionFlame();
        Collisions.collisionsHandler();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        entities.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        flameList.forEach(g -> g.render(gc));
    }
}
