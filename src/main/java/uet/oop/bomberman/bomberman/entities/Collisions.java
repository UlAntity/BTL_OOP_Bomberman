package uet.oop.bomberman.bomberman.entities;

import uet.oop.bomberman.bomberman.BombermanGame;
import uet.oop.bomberman.bomberman.Sound;
import uet.oop.bomberman.bomberman.entities.Enemies.Kondoria;
import uet.oop.bomberman.bomberman.entities.Enemies.Oneal;
import uet.oop.bomberman.bomberman.entities.Items.Portal;

import java.awt.*;

import static uet.oop.bomberman.bomberman.BombermanGame.*;
public class Collisions {
    public static void checkCollisionFlame() {
        for (Flame flame : flameList) {
            Rectangle r1 = flame.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getBounds();
                if (r1.intersects(r2) && (stillObject instanceof Brick))
                    stillObject.setAlive(false);
            }

            Rectangle r2 = bomberman.getBounds();
            if (r1.intersects(r2) && !bomberman.invincible) {
                bomberman.setAlive(false);
            }

            for (Enemy enemy : enemies) {
                Rectangle r3 = enemy.getBounds();

                if (r1.intersects(r3)) {
                    enemy.time++;
                    if (enemy.lives > 1) {
                        enemy.secondChanceTime++;
                    }
                    if (enemy.secondChanceTime == 0 || enemy.secondChanceTime > 40) {
                        //enemy dies!
                        BombermanGame.score += 5;
                        enemy.setAlive(false);
                    }

                }
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
                    BombermanGame.score += 50;
                    ((Item) stillObject).change();;
                    Sound.Eat.play();
                    Sound.powerups.play();
                    stillObjects.remove(stillObject);
                }

                if(stillObject instanceof Portal) {
                    if(enemies.size() == 0) {
                        level++;
                        Sound.Eat.play();
                        BombermanGame.score += 200;
                        BombermanGame.nextLevel = true;
                    }
                }
                break;
            }
        }

    }

    public static void bombsOverlapHandle() {
        Rectangle r1 = BombermanGame.bomberman.getBounds();
        for (Entity bomb : bombs) {
            Rectangle r2 = bomb.getBounds();
            if (bomberman.infibomb && r1.intersects(r2)) {
                bomberman.infibomb = false;
                break;
            }
            else bomberman.infibomb = true;
        }
    }

    public static void enemyHandler() {
        for(Enemy enemy : enemies) {
            Rectangle r1 = enemy.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getBounds();
                if (enemy instanceof Kondoria && stillObject instanceof Brick) {
                    enemy.move();
                } else if (r1.intersects(r2)) {
                    if (enemy.getLayer() >= stillObject.getLayer()) {
                        enemy.move();
                    } else {
                        enemy.stay();
                    }
                    break;
                }
            }
            for (Entity bomb : bombs) {
                Rectangle r3 = bomb.getBounds();
                if (!(enemy instanceof Oneal)) {
                    if (r1.intersects(r3)) {
                        if (enemy.getLayer() < bomb.getLayer()) {
                            enemy.metBomb = true;
                            enemy.stay();
                        }
                    }
                }
            }
            Rectangle r4 = bomberman.getBounds();
            if (r1.intersects(r4) && enemy.isAlive() && !bomberman.invincible) {
                bomberman.setAlive(false);
            }
        }
    }
}
