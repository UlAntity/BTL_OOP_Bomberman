package uet.oop.bomberman.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman.BombermanGame;
import uet.oop.bomberman.bomberman.graphics.Sprite;
import java.awt.*;

public class Flame extends Entity {
    private int left;
    private int right;
    private int top;
    private int down;
    public int radius;
    private int size = Sprite.SCALED_SIZE;
    private int direction;
    private int time = 0;

    public Flame(int x, int y, Image image, int direction){
        super(x, y);
        this.img = image;
        this.direction = direction;
    }

    public Flame(int x, int y, Image image){
        super(x, y);
        this.img = image;
        this.radius = 1;
    }

    public Flame(int x, int y){
        super(x,y);
    }

    public void setRadius(int radius) {
        this.radius = radius; // cài bán kính flame
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE * 3/4, Sprite.SCALED_SIZE * 3/4);
    }

    @Override
    public void update(){ // phương thức kết thúc vụ nổ
        if(time < 20){ // thời gian flame kéo dài
            time++;
            setImg();
        } else {
            BombermanGame.flameList.remove(this);
        }
    }

    public void render_explosion(){
        Right();
        Left();
        Top();
        Down();
        create_explosion();
    }

    private void create_explosion(){
        BombermanGame.flameList.add(new Flame(x, y, Sprite.bomb_exploded.getFxImage(),0));
        for(int i = 0; i < right; i++){
            Flame e = new Flame(x + size*(i + 1), y);
            if(i == right - 1) {
                e.img = Sprite.explosion_horizontal_right_last.getFxImage();
                e.direction = 2;
            } else{
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            BombermanGame.flameList.add(e);
        }

        for(int i = 0; i < left; i++){
            Flame e = new Flame(x - size*(i + 1), y);
            if(i == left - 1) {
                e.img = Sprite.explosion_horizontal_left_last.getFxImage();
                e.direction = 3;
            } else{
                e.img = Sprite.explosion_horizontal.getFxImage();
                e.direction = 1;
            }
            BombermanGame.flameList.add(e);
        }

        for(int i = 0; i < top; i++){
            Flame e = new Flame(x , y - size*(i + 1));
            if(i == top - 1) {
                e.img = Sprite.explosion_vertical_top_last.getFxImage();
                e.direction = 5;
            } else{
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            BombermanGame.flameList.add(e);
        }

        for(int i = 0; i < down; i++){
            Flame e = new Flame(x, y + size*(i + 1));
            if(i == right - 1) {
                e.img = Sprite.explosion_vertical_down_last.getFxImage();
                e.direction = 6;
            } else{
                e.img = Sprite.explosion_vertical.getFxImage();
                e.direction = 4;
            }
            BombermanGame.flameList.add(e);
        }
    }
    private void Right() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_right = new Rectangle(x + size*(i + 1), y, size, size); // tạo bound flame
            if (collisionType(ex_right) instanceof Wall) {
                right = i;
                return;
            } else if(collisionType(ex_right) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void Left() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_left = new Rectangle(x - size*(i + 1), y, size, size);
            if (collisionType(ex_left) instanceof Wall) {
                left = i;
                return;
            } else if(collisionType(ex_left) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void Top() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_top = new Rectangle(x, y - size*(i + 1), size, size);
            if (collisionType(ex_top) instanceof Wall) {
                top = i;
                return;
            } else if(collisionType(ex_top) instanceof Brick) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void Down() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_down = new Rectangle(x, y + size*(i + 1), size, size);
            if (collisionType(ex_down) instanceof Wall) {
                down = i;
                return;
            } else if(collisionType(ex_down) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private static Object collisionType(Rectangle r){
        for(Entity e : BombermanGame.stillObjects){
            Rectangle r2 = e.getBounds();
            if(r.intersects(r2)){
                return e;
            }
        }
        return r;
    }



    private void setImg() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, time, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        ,Sprite.explosion_horizontal2,time,20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        ,Sprite.explosion_horizontal_right_last2, time,20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        ,Sprite.explosion_horizontal_left_last2, time,20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        ,Sprite.explosion_vertical2, time,20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        ,Sprite.explosion_vertical_top_last2, time,20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        ,Sprite.explosion_vertical_down_last2, time,20).getFxImage();
                break;
        }
    }

}
