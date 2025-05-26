package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class VideoPlayerController {

    @FXML
    private ImageView imageView;

    @FXML
    private Button startButton;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        try {
            InputStream stream = getClass().getResourceAsStream("/Round/frame_0001.png");
            Image image = new Image(stream);
            imageView.setImage(image); // Set image to imageView
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void playVideo() {
        // Hide all elements
        imageView.setVisible(false);
        startButton.setVisible(false);

        // Load video file
        String videoPath = "/Round/output.mp4"; // Change this to your video file path
        URL videoFileUrl = getClass().getResource(videoPath);
        if (videoFileUrl == null) {
            System.err.println("Video file not found: " + videoPath);
            return;
        }

        Media media = new Media(videoFileUrl.toString());

        // Create media player
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // Create media view
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(imageView.getFitWidth());
        mediaView.setFitHeight(imageView.getFitHeight());
        mediaView.setPreserveRatio(true);

        // Add media view to imageView's parent
        ((AnchorPane) imageView.getParent()).getChildren().add(mediaView);

        // Set media view's position
        AnchorPane.setTopAnchor(mediaView, AnchorPane.getTopAnchor(imageView));
        AnchorPane.setLeftAnchor(mediaView, AnchorPane.getLeftAnchor(imageView));

        // Play video
        mediaPlayer.setOnEndOfMedia(() -> {
            // Go to next scene
            goToNextScene();
        });
    }


    private void goToNextScene() {
        // Implement logic to switch to the next scene

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/gui/choosingRoles.fxml"));
            Stage stage = (Stage) imageView.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dice Game");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
