package game.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GaiaController {

    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Button button10;
    @FXML
    private Button button11;
    @FXML
    private Button button12;

    public void initialize() {
        button12.setOnAction(this::switchScene);
        button2.setOnAction(this::switchScene);
        button3.setOnAction(this::switchScene);
        button4.setOnAction(this::switchScene);
        button5.setOnAction(this::switchScene);
        button6.setOnAction(this::switchScene);
        button7.setOnAction(this::switchScene);
        button8.setOnAction(this::switchScene);
        button9.setOnAction(this::switchScene);
        button10.setOnAction(this::switchScene);
        button11.setOnAction(this::switchScene);
    }

    @FXML
    private void switchScene(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/game/gui/GameBoard.fxml"));
            stage.setScene(new Scene(root, 600, 400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
