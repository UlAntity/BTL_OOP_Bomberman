package uet.oop.bomberman;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import uet.oop.bomberman.bomberman.BombermanGame;

import java.io.IOException;

public class GameController {
    @FXML
    private Label welcomeText;

    @FXML
    public void onHelloButtonClick(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        BombermanGame game = new BombermanGame();
        game.start(window);

    }
}