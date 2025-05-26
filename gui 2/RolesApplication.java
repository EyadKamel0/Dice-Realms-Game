package game.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RolesApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/game/gui/choosingRoles.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Dice Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}