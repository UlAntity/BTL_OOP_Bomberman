package uet.oop.bomberman.bomberman;

import uet.oop.bomberman.bomberman.entities.*;
import uet.oop.bomberman.bomberman.entities.Enemies.*;
import uet.oop.bomberman.bomberman.entities.Items.BombItem;
import uet.oop.bomberman.bomberman.entities.Items.FlameItem;
import uet.oop.bomberman.bomberman.entities.Items.Portal;
import uet.oop.bomberman.bomberman.entities.Items.SpeedItem;
import uet.oop.bomberman.bomberman.graphics.Sprite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static uet.oop.bomberman.bomberman.BombermanGame.enemies;
import static uet.oop.bomberman.bomberman.BombermanGame.stillObjects;

public class Map {

    public static void createMap() {
        try {
            File file = new File("src/main/java/uet/oop/bomberman/Levels/level" + BombermanGame.level + ".txt");
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

                        if (r.charAt(j) == '2') {
                            enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        }

                        if (r.charAt(j) == '3') {
                            enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                        }

                        if (r.charAt(j) == '4') {
                            enemies.add(new Minvo(j, i, Sprite.minvo_left1.getFxImage()));
                        }

                        if (r.charAt(j) == '5') {
                            enemies.add(new Kondoria(j, i, Sprite.kondoria_left1.getFxImage()));
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
