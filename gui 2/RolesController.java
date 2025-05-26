package game.gui;

import game.engine.CLIGameController;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class RolesController implements Initializable {
    private static final int NUMBER_OF_DICE = 2;
    private static final String IMAGE_PATH = "/Users/eyad/Documents/Myprojects/GameGUI/src/main/resources/Images/dice";
    private static final String IMAGE_EXTENSION = ".png";
    private final Random random = new Random();
    CLIGameController x = new CLIGameController();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView diceImage;
    @FXML
    private ImageView diceImage1;
    @FXML
    private Button rollButton;
    @FXML
    private Button nextRound;
    @FXML
    private Label player1Label;
    @FXML
    private Label player2Label;

    // Predefined dice values
    private final int[] predefinedDiceValues = {3, 5}; // Example values, change as needed

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set initial dice images to 1
        File[] initialImageFiles = new File[NUMBER_OF_DICE];
        for (int j = 0; j < NUMBER_OF_DICE; j++) {
            initialImageFiles[j] = new File(IMAGE_PATH + "1" + IMAGE_EXTENSION);
        }

        // Update dice images on the JavaFX Application Thread
        diceImage.setImage(new Image(initialImageFiles[0].toURI().toString()));
        diceImage1.setImage(new Image(initialImageFiles[1].toURI().toString()));
    }

    @FXML
    private void roll(ActionEvent event) {
        rollButton.setDisable(true);
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 14; i++) {
                    // Generate random dice values for the rolling effect
                    int[] diceValues = random.ints(NUMBER_OF_DICE, 1, 7).toArray();

                    // Create image file paths
                    File[] imageFiles = new File[NUMBER_OF_DICE];
                    for (int j = 0; j < NUMBER_OF_DICE; j++) {
                        imageFiles[j] = new File(IMAGE_PATH + diceValues[j] + IMAGE_EXTENSION);
                    }

                    // Update dice images on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        diceImage.setImage(new Image(imageFiles[0].toURI().toString()));
                        diceImage1.setImage(new Image(imageFiles[1].toURI().toString()));
                    });

                    Thread.sleep(50);
                }

                // Set the predefined dice values at the end
                File[] finalImageFiles = new File[NUMBER_OF_DICE];
                for (int j = 0; j < NUMBER_OF_DICE; j++) {
                    finalImageFiles[j] = new File(IMAGE_PATH + predefinedDiceValues[j] + IMAGE_EXTENSION);
                }

                // Update dice images on the JavaFX Application Thread
                Platform.runLater(() -> {
                    diceImage.setImage(new Image(finalImageFiles[0].toURI().toString()));
                    diceImage1.setImage(new Image(finalImageFiles[1].toURI().toString()));
                });

                // Delay for 1 second before showing the popup message
                Thread.sleep(1000);

                // Determine the higher dice value and show the popup message
                Platform.runLater(() -> {
                    String activePlayer;
                    String passivePlayer;

                    if (predefinedDiceValues[0] > predefinedDiceValues[1]) {
                        activePlayer = player1Label.getText();
                        passivePlayer = player2Label.getText();
                    } else {
                        activePlayer = player2Label.getText();
                        passivePlayer = player1Label.getText();
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Player Roles");
                    alert.setHeaderText(null);
                    alert.setContentText("Active Player: " + activePlayer + "\nPassive Player: " + passivePlayer);
                    alert.show();

                    // Automatically close the alert after 4 seconds
                    PauseTransition delay = new PauseTransition(Duration.seconds(4));
                    delay.setOnFinished(e -> alert.close());
                    delay.play();
                });

                // Wait for 5 seconds before switching the scene
                Thread.sleep(5000);

                // Switch the scene on the JavaFX Application Thread
                Platform.runLater(this::switchScene);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Re-enable the roll button on the JavaFX Application Thread
                Platform.runLater(() -> rollButton.setDisable(false));
            }
        });
        thread.start();
    }

    @FXML
    private void switchScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/game/gui/GameBoard.fxml"));
            stage = (Stage) rollButton.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
