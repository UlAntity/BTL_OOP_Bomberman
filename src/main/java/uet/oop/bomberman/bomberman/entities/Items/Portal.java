package uet.oop.bomberman.bomberman.entities.Items;

import javafx.scene.image.Image;
import uet.oop.bomberman.bomberman.entities.StillEntity;

public class Portal extends StillEntity {
    public Portal(int x, int y, Image image) {
        super(x, y, image);
        setLayer(2);
    }

    @Override
    public void update() {

    }
}
