package uet.oop.bomberman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.bomberman.BombermanGame;

import java.io.IOException;

import static uet.oop.bomberman.bomberman.BombermanGame.*;

public class GameController {

    @FXML

    public static void finalSteps(Stage window) {
        window.setTitle("BOMBERMAN");
        window.getIcons().add(new Image("C:\\Users\\Dell\\Desktop\\Bomberman\\src\\main\\resources\\img\\icon.jpg"));
    }

    public void playButtonClick (ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
            BombermanGame game = new BombermanGame();
            finalSteps(window);
            game.start(window);
    }


    public void htpButtonClick(ActionEvent actionEvent) throws IOException {
        Parent htp = FXMLLoader.load(getClass().getResource("howToPlay.fxml"));
        Stage root = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(htp, 992, 416);
        finalSteps(root);
        root.setScene(scene);
        root.show();
    }

    public void menuButtonClick(ActionEvent actionEvent) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Stage root = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(menu, 992, 416);
        finalSteps(root);
        root.setScene(scene);
        root.show();
    }


    public void quitButtonClick() {
        System.exit(0);
    }

    public void retryButtonClick(ActionEvent actionEvent) {
    }

    public void pauseButtonClick(KeyCode keyCode) {

    }
}

