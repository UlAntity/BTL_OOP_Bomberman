package uet.oop.bomberman;


import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import static javafx.scene.paint.Color.BLACK;

public class Bar extends AnchorPane{
    public Label labelLevel;

    public Label labelTime;

    public Label labelPoint;

    public Label labelRemain;

    public Bar() {
        labelTime = new Label("TIME: ");
        labelTime.setLayoutX(32);
        labelTime.setLayoutY(420);
        labelTime.setFont(Font.font(18));
        labelTime.setTextFill(BLACK);

        labelLevel = new Label("Level: ");
        labelLevel.setLayoutX(200);
        labelLevel.setLayoutY(420);
        labelLevel.setFont(Font.font(18));
        labelLevel.setTextFill(BLACK);

        labelPoint = new Label("POINT: ");
        labelPoint.setLayoutX(400);
        labelPoint.setLayoutY(420);
        labelPoint.setFont(Font.font(18));
        labelPoint.setTextFill(BLACK);

        labelRemain = new Label("ENEMIES REMAIN: ");
        labelRemain.setLayoutX(600);
        labelRemain.setLayoutY(420);
        labelRemain.setFont(Font.font(18));
        labelRemain.setTextFill(BLACK);


    }
    public void setPanel() {
        BombermanGame.BarControl.getChildren().addAll(labelTime,labelLevel, labelPoint,labelRemain);
    }
    public void setLevel(int n) {
        labelLevel.setText("LEVEL : " + n);
    }

    public void setTimes(int n) {
        labelTime.setText("TIMES : " + n);
    }

    public void setPoint(int n) {
        labelPoint.setText("POINT : " + n);
    }

    public void setRemain(int n) {
        labelRemain.setText("ENEMIES REMAIN : " + n);
    }
}