package uet.oop.bomberman.bomberman.entities;

import javafx.scene.image.Image;

public class Portal extends StillEntity{
    public Portal(int x, int y, Image image) {
        super(x, y, image);
        setLayer(2);
    }

    @Override
    public void update() {

    }
}
