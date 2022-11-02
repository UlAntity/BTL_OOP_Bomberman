package uet.oop.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent menu = FXMLLoader.load((GameMenu.class.getResource("menu.fxml")));
        Scene scene = new Scene(menu, 992, 416);
        //String css = this.getClass().getResource("style.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setTitle("BOMBERMAN");
        stage.getIcons().add(new Image("C:\\Users\\Dell\\Desktop\\Bomberman\\src\\main\\resources\\img\\icon.jpg"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}