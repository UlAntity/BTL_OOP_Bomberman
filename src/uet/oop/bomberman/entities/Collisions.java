package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;

public class Collisions {
    public static void checkCollisionFlame() {
        for (int i = 0; i < flameList.size(); i++) {
            Rectangle r1 = flameList.get(i).getBounds();
            for (int j = 0; j < stillObjects.size(); j++) {
                Rectangle r2 = stillObjects.get(j).getBounds();
                if (r1.intersects(r2) && (stillObjects.get(j) instanceof Brick))
                    stillObjects.get(j).setAlive(false);
            }
        }
    }

    public static void collisionsHandler() {
        Rectangle r1 = BombermanGame.bomberman.getBounds();
        for (Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getBounds();
            if (r1.intersects(r2)) {
                if(bomberman.getLayer() >= stillObject.getLayer()) {
                    bomberman.move();
                } else {
                    bomberman.stay();
                }

                if(stillObject instanceof Item) {
                    if(stillObject instanceof SpeedItem) {
                        bomberman.setSpeed(5);
                    }
                    if(stillObject instanceof FlameItem) {
                        bomberman.setRadius(2);
                    }
                    if(stillObject instanceof BombItem) {
                        bomberman.infibomb = true;
                    }
                    stillObjects.remove(stillObject);
                }

                if(stillObject instanceof Portal) {
                    level++;
                    BombermanGame.nextLevel = true;
                }
                break;
            }
        }
    }
}
