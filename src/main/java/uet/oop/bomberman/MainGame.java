package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.bomberman.Sound;

import java.io.IOException;

public class MainGame extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent menu = FXMLLoader.load((MainGame.class.getResource("menu.fxml")));
        Scene scene = new Scene(menu, 992, 416);
        //String css = this.getClass().getResource("style.css").toExternalForm();
        //scene.getStylesheets().add(css);
        Sound.titleScreen.play();
        stage.setTitle("BOMBERMAN");
        stage.getIcons().add(new Image("C:\\Users\\Dell\\Desktop\\Bomberman\\src\\main\\resources\\img\\icon.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}