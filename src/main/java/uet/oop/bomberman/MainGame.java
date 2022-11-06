package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uet.oop.bomberman.bomberman.Sound;

import java.io.IOException;

public class MainGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent menu = FXMLLoader.load((MainGame.class.getResource("menu.fxml")));
        Scene scene = new Scene(menu, 992, 416);
        Sound.titleScreen.play();
        GameController.finalSteps(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}