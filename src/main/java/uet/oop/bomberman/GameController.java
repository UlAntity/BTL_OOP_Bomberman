package uet.oop.bomberman;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import uet.oop.bomberman.bomberman.BombermanGame;
import uet.oop.bomberman.bomberman.Sound;

import java.io.IOException;

import static uet.oop.bomberman.bomberman.BombermanGame.*;

public class GameController {

    @FXML
    BombermanGame game = new BombermanGame();

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public static void finalSteps(Stage window) {
        window.setTitle("BOMBERMAN");
        window.getIcons().add(new Image("C:\\Users\\Dell\\Desktop\\Bomberman\\src\\main\\resources\\img\\icon.jpg"));
    }

    public void playButtonClick (ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        Sound.titleScreen.stop();
        if (!gameInnit) {
            finalSteps(window);
            game.start(window);
        }

    }


    public void htpButtonClick(ActionEvent actionEvent) throws IOException {
        Parent htp = FXMLLoader.load(getClass().getResource("howToPlay.fxml"));
        stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(htp, 992, 416);
        finalSteps(stage);
        stage.setScene(scene);
        stage.show();
    }

    public void menuButtonClick(ActionEvent actionEvent) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        Scene scene = new Scene(menu, 992, 416);
        finalSteps(stage);
        stage.setScene(scene);
        stage.show();
    }


    public void quitButtonClick() {
        System.exit(0);
    }

    public void retryButtonClick(KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            game.resetLevel();
            //working
        }
    }

    public static void pauseButtonClick(KeyEvent keyCode) throws IOException {
        Stage stage = (Stage) (((Node) keyCode.getSource()).getScene().getWindow());
        FXMLLoader pause = new FXMLLoader(GameController.class.getResource("pauseScreen.fxml"));
        Parent root = pause.load();
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
        //working
    }

    public void mouseEntered(MouseEvent mouseEvent)  {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(0);
    }

    public void mouseExited(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.setOpacity(1);
    }


}

