package uet.oop.bomberman;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {

    public static void createMap() {
        try {
            File file = new File("src/uet/oop/bomberman/Map.txt");
            Scanner scanner = new Scanner(file);
            int height = scanner.nextInt();
            height = scanner.nextInt();
            int width = scanner.nextInt();
            scanner.nextLine();
            Entity object;

            BombermanGame.HEIGHT = height;
            BombermanGame.WIDTH = width;

            for (int i = 0; i < height; i++) {
                String tempString = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    switch (tempString.charAt(j)) {
                        case '#':
                            object = new Wall(j, i, Sprite.wall.getFxImage());
                            break;
                        case '*':
                            object = new Brick(j, i, Sprite.brick.getFxImage());
                            break;
                        // case 'x':
                        //     tempList.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        default:
                            object = new Grass(j, i, Sprite.grass.getFxImage());
                            break;
                    }
                    BombermanGame.stillObjects.add(object);
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        /*
        for (int i = 0; i < BombermanGame.WIDTH; i++) {
            for (int j = 0; j < BombermanGame.HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == BombermanGame.HEIGHT - 1 || i == 0 || i == BombermanGame.WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                BombermanGame.stillObjects.add(object);
            }
        }

         */
    }

    public static Entity getObject(int i, int j) {
        Entity object = null;
        for(Entity entity : BombermanGame.stillObjects) {
            if(entity.x / Sprite.SCALED_SIZE == i && entity.y / Sprite.SCALED_SIZE == j) {
               object = entity;
            }
        }
        return object;
    }
}
