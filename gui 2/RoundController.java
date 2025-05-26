package game.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class RoundController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private MediaView mediaView;

    public void initialize() {
        String videoFile = "/Users/eyad/Documents/Game/dice-realms-game-le3ba/src/main/resources/Round/RoundNum.mp4"; // Replace with your video file path
        File file = new File(videoFile);
        String videoPath = file.toURI().toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(videoPath));
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        // Create a PauseTransition for 5 seconds
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            try {
                returnToGameBoard();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    private void returnToGameBoard() throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));
        stage = (Stage) mediaView.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

