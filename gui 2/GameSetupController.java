package game.gui;

import game.engine.CLIGameController;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;




import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GameSetupController {
 private CLIGameController x;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private TextField inputField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label player1Label;
    @FXML
    private TextField player1Name;
    @FXML
    private Label player2Label;
    @FXML
    private TextField player2Name;
    @FXML
    private MediaView imageView1;
    @FXML
    private MediaView imageView2;
    @FXML
    private Button  nextButton;
    

    @FXML
    public void initialize() {
        // Show the logo image after 1 second
    	  // Show the logo image after 1 second
//   	 // Load the first media file
//       String mediaPath1 = new File("/GamePlay/src/application/dragonvod.mp4").toURI().toString();
//       Media media1 = new Media(mediaPath1);
//       MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
//       imageView1.setMediaPlayer(mediaPlayer1);
//
//      
//
//       // Optionally, auto-play the videos
//       mediaPlayer1.play();
//      // mediaPlayer2.play()

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> showLabels());
        delay.play();
    }
    


    private void showLabels() {
        // Show labels one by one with a delay
        showLabel(label1, 1);
        showLabel(label2, 2);
        showLabel(label3, 3);
        showLabel(label4, 4, () -> {
            // After all labels are shown, show input field
            inputField.setOpacity(1.0);
            inputField.setOnAction(e -> handleInput());
            Platform.runLater(() -> inputField.requestFocus());
        });
    }
    
    private void showLabel(Label label, int delayInSeconds) {
        showLabel(label, delayInSeconds, null);
    }

    private void showLabel(Label label, int delayInSeconds, Runnable onFinished) {
        PauseTransition delay = new PauseTransition(Duration.seconds(delayInSeconds));
        delay.setOnFinished(event -> {
            label.setVisible(true);
            if (onFinished != null) {
                onFinished.run();
            }
        });
        delay.play();
    }

    private void handleInput() {
        String input = inputField.getText();
        if (!input.equals("1") && !input.equals("2")) {
            errorLabel.setVisible(true);
            inputField.clear();
            Platform.runLater(() -> inputField.requestFocus());
            return;
        }

        errorLabel.setVisible(false);
        inputField.setVisible(false);

        if (input.equals("1")) {
            player1Label.setOpacity(1.0);
            player1Name.setOpacity(1.0);
            player1Name.setOnAction(e -> showNextButton());
            Platform.runLater(() -> player1Name.requestFocus());
            
            player2Label.setOpacity(1.0);
            player2Name.setOpacity(1.0);
            player2Name.setOnAction(e -> showNextButton());
        } else {
            player1Label.setOpacity(1.0);
            player1Name.setOpacity(1.0);
            player1Name.setOnAction(e -> showNextButton());
            Platform.runLater(() -> player1Name.requestFocus());
        }
    }

    private void showNextButton() {
        nextButton.setVisible(true);
        nextButton.setOnAction(e -> switchToDiceView(e));
    }

    private void switchToDiceView(ActionEvent event) {
        try {
        	x.startGame();
            Parent diceView = FXMLLoader.load(getClass().getResource("DiceView.fxml"));
            Scene diceScene = new Scene(diceView);
            
			Stage window = (Stage) nextButton.getScene().getWindow();
            window.setScene(diceScene);
            
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }	
    }
    public String getPlayerOne(){
		String Name1= player1Name.getText();
		return Name1;
    }
    public String getPlayerTwo(){
		String Name1= player2Name.getText();
		return Name1;
    }
}
