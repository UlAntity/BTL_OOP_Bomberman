package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Map {

    public static void createMap() {
        try {
            File file = new File("src/Levels/level" + BombermanGame.level + ".txt");
            Scanner scanner = new Scanner(file);
            int height = scanner.nextInt();
            height = scanner.nextInt();
            int width = scanner.nextInt();
            scanner.nextLine();
            Entity object;

            BombermanGame.HEIGHT = height;
            BombermanGame.WIDTH = width;

            for (int i = 0; i < height; i++) { // quét các dòng tiếp rồi nhập hết vào string r để duyệt sau đó xuất ảnh đêt load map
                String r = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    if (r.charAt(j) == '#') {
                        stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                    } else {
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        if (r.charAt(j) == '*') {
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        }
                        if (r.charAt(j) == 'x') {
                            stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        }
                        if (r.charAt(j) == 'b') {
                            stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        }
                        if (r.charAt(j) == 'f') {
                            stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        }
                        if (r.charAt(j) == 's') {
                            stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        }

                        if (r.charAt(j) == '1') {
                            enemies.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                        }
                    }
                }
            }
            stillObjects.sort(new Layer());
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
