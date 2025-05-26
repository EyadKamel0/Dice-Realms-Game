package game.gui;

import game.engine.CLIGameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameBoardController {
    private static final int NUMBER_OF_DICE = 6;
    private static final String IMAGE_PATH = "/Users/eyad/Documents/Myprojects/GameGUI/src/main/resources/Images/dice";
    private static final String IMAGE_EXTENSION = ".png";
    private final Random random = new Random();
    private CLIGameController x = new CLIGameController();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView diceImage;
    @FXML
    private ImageView diceImage1;
    @FXML
    private ImageView diceImage2;
    @FXML
    private ImageView diceImage3;
    @FXML
    private ImageView diceImage4;
    @FXML
    private ImageView diceImage5;
    @FXML
    private Button rollButton;
    @FXML
    private Button nextRound;
    @FXML
    private HBox forgottenRealmBox;
    private Set<Integer> forgottenRealmDice = new HashSet<>();
    private int rollCount = 0;
    private static final int MAX_ROLLS = 3;
    private boolean firstRoll = true;




    private int[] currentDiceValues = new int[NUMBER_OF_DICE];
    private boolean[] diceChosen = new boolean[NUMBER_OF_DICE];
    private int selectedForgottenRealmDiceValue = -1;

    @FXML
    public void initialize() {
        Arrays.fill(diceChosen, false);
    }

    @FXML
    private void roll(ActionEvent event) {
        // Check if it's the first roll or if any dice have been selected
        if (!firstRoll && !anyDiceSelected()) {
            System.out.println("Please select a dice before rolling.");
            return; // Exit the method if no dice selected after the first roll
        }

        // Reset the first roll flag after the first roll
        if (firstRoll) {
            firstRoll = false;
        }

        rollCount++;

        // Reset roll count if all dice become invisible
        if (!anyDiceVisible()) {
            rollCount = 0;
        }

        // Disable roll button if maximum rolls reached or all dice invisible
        if (rollCount >= MAX_ROLLS || !anyDiceVisible()) {
            rollButton.setDisable(true);
            enableSelectionFromForgottenRealm();
            selectDiceFromForgottenRealm(); // Select a dice from the forgotten realm
        }

        // Reset the diceChosen array before rolling
        forgottenRealmDice.clear();
        Arrays.fill(diceChosen, false);

        rollButton.setDisable(true);
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    // Generate random dice values
                    int[] diceValues = random.ints(NUMBER_OF_DICE, 1, 7).toArray();

                    // Store the current dice values
                    System.arraycopy(diceValues, 0, currentDiceValues, 0, NUMBER_OF_DICE);

                    // Create image file paths
                    File[] imageFiles = new File[NUMBER_OF_DICE];
                    for (int j = 0; j < NUMBER_OF_DICE; j++) {
                        imageFiles[j] = new File(IMAGE_PATH + diceValues[j] + IMAGE_EXTENSION);
                    }

                    // Update dice images on the JavaFX Application Thread
                    Platform.runLater(() -> {
                        diceImage.setImage(new Image(imageFiles[0].toURI().toString()));
                        diceImage1.setImage(new Image(imageFiles[1].toURI().toString()));
                        diceImage2.setImage(new Image(imageFiles[2].toURI().toString()));
                        diceImage3.setImage(new Image(imageFiles[3].toURI().toString()));
                        diceImage4.setImage(new Image(imageFiles[4].toURI().toString()));
                        diceImage5.setImage(new Image(imageFiles[5].toURI().toString()));
                    });

                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Re-enable the roll button on the JavaFX Application Thread
                Platform.runLater(() -> {
                    rollButton.setDisable(false);
                    enableAllDiceMouseEvents();
                });
            }
        });
        thread.start();
    }

    private void enableAllDiceMouseEvents() {
        diceImage.setDisable(false);
        diceImage1.setDisable(false);
        diceImage2.setDisable(false);
        diceImage3.setDisable(false);
        diceImage4.setDisable(false);
        diceImage5.setDisable(false);
    }


    @FXML
    private void handleDiceClick(MouseEvent event) {
        ImageView clickedDice = (ImageView) event.getSource();
        int clickedIndex = -1;

        // Determine which dice was clicked and if it has already been chosen
        if (clickedDice == diceImage && !diceChosen[0]) {
            clickedIndex = 0;
        } else if (clickedDice == diceImage1 && !diceChosen[1]) {
            clickedIndex = 1;
        } else if (clickedDice == diceImage2 && !diceChosen[2]) {
            clickedIndex = 2;
        } else if (clickedDice == diceImage3 && !diceChosen[3]) {
            clickedIndex = 3;
        } else if (clickedDice == diceImage4 && !diceChosen[4]) {
            clickedIndex = 4;
        } else if (clickedDice == diceImage5 && !diceChosen[5]) {
            clickedIndex = 5;
        }

        // Handle the clicked dice index
        if (clickedIndex != -1) {
            int clickedValue = currentDiceValues[clickedIndex];

            // Move lower value visible remaining dice to the Forgotten Realm
            for (int i = 0; i < NUMBER_OF_DICE; i++) {
                if (!diceChosen[i] && currentDiceValues[i] != 0 && currentDiceValues[i] < clickedValue && diceIsVisible(i)) {
                    moveToForgottenRealm(currentDiceValues[i]);
                    currentDiceValues[i] = 0; // Mark this dice as moved
                }
            }



            // Hide the clicked dice
            clickedDice.setVisible(false);
            currentDiceValues[clickedIndex] = 0; // Mark this dice as moved
            diceChosen[clickedIndex] = true; // Mark this dice as chosen

            // Update the remaining visible dice
            updateRemainingDice();

            // Disable mouse events for remaining dice
            disableRemainingDiceMouseEvents();
        }
    }

    private void disableRemainingDiceMouseEvents() {
        diceImage.setDisable(true);
        diceImage1.setDisable(true);
        diceImage2.setDisable(true);
        diceImage3.setDisable(true);
        diceImage4.setDisable(true);
        diceImage5.setDisable(true);
    }


    private void moveToForgottenRealm(int value) {
        if (!forgottenRealmDice.contains(value)) { // Check if the value is not already in forgotten realm
            Platform.runLater(() -> {
                ImageView diceImage = new ImageView(new Image(new File(IMAGE_PATH + value + IMAGE_EXTENSION).toURI().toString()));
                diceImage.setFitHeight(33);
                diceImage.setFitWidth(33);
                forgottenRealmBox.getChildren().add(diceImage);
            });
        }
    }

    private void updateRemainingDice() {
        Platform.runLater(() -> {
            ImageView[] diceImages = { diceImage, diceImage1, diceImage2, diceImage3, diceImage4, diceImage5 };
            for (int i = 0; i < NUMBER_OF_DICE; i++) {
                if (currentDiceValues[i] ==0) {
                    diceImages[i].setVisible(false);
                }
            }
        });
    }

    @FXML
    private void handleDiceHoverEnter(MouseEvent event) {
        ImageView hoveredDice = (ImageView) event.getSource();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5); // Dim the image
        hoveredDice.setEffect(colorAdjust);
    }

    @FXML
    private void handleDiceHoverExit(MouseEvent event) {
        ImageView hoveredDice = (ImageView) event.getSource();
        hoveredDice.setEffect(null); // Remove the dim effect
    }

    @FXML
    private void switchScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RoundNumber.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private boolean diceIsVisible(int index) {
        switch (index) {
            case 0: return diceImage.isVisible();
            case 1: return diceImage1.isVisible();
            case 2: return diceImage2.isVisible();
            case 3: return diceImage3.isVisible();
            case 4: return diceImage4.isVisible();
            case 5: return diceImage5.isVisible();
            default: return false;
        }
    }

    private boolean anyDiceVisible() {
        return diceImage.isVisible() || diceImage1.isVisible() || diceImage2.isVisible() ||
                diceImage3.isVisible() || diceImage4.isVisible() || diceImage5.isVisible();
    }
    private void enableSelectionFromForgottenRealm() {
        // Enable selection of a dice from the forgotten realm
        // You can implement the logic to enable selection here
        // For now, let's just print a message to indicate the selection
        System.out.println("Select a dice from the forgotten realm.");
    }
    private boolean anyDiceSelected() {
        for (boolean chosen : diceChosen) {
            if (chosen) {
                return true;
            }
        }
        return false;
    }
    @FXML
    private void selectDiceFromForgottenRealm() {
        System.out.println("Selecting a dice from the forgotten realm.");

        // Clear any existing event handlers
        forgottenRealmBox.getChildren().forEach(node -> {
            if (node instanceof ImageView) {
                ImageView diceImage = (ImageView) node;
                diceImage.setOnMouseClicked(null);
            }
        });

        // Set event handler for each dice image in the forgotten realm
        forgottenRealmBox.getChildren().forEach(node -> {
            if (node instanceof ImageView) {
                ImageView diceImage = (ImageView) node;
                diceImage.setOnMouseClicked(event -> {
                    // Extract the value of the selected dice from the image URL
                    String imageUrl = diceImage.getImage().getUrl();
                    int index = imageUrl.lastIndexOf("dice") + 4;
                    int endIndex = imageUrl.lastIndexOf(".");
                    if (index != -1 && endIndex != -1) {
                        try {
                            // Extract the value of the selected dice from the image URL
                            selectedForgottenRealmDiceValue = Integer.parseInt(imageUrl.substring(index, endIndex));
                            System.out.println("Selected dice value: " + selectedForgottenRealmDiceValue);
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing the value of the selected dice: " + e.getMessage());
                        }
                    }

                    // Reset the game board after selecting a dice
                    resetGameBoard();
                });
            }
        });
    }

    private void resetGameBoard() {
        // Reset the dice images to their initial state
        String initialImagePath = "/Users/eyad/Documents/Myprojects/GameGUI/src/main/resources/Images/dice1.png";
        Image initialImage = new Image(new File(initialImagePath).toURI().toString());

        Platform.runLater(() -> {
            diceImage.setImage(initialImage);
            diceImage1.setImage(initialImage);
            diceImage2.setImage(initialImage);
            diceImage3.setImage(initialImage);
            diceImage4.setImage(initialImage);
            diceImage5.setImage(initialImage);
        });

        // Clear any selections made by the player
        Arrays.fill(diceChosen, false);
        selectedForgottenRealmDiceValue = -1;

        // Enable the roll button
        rollButton.setDisable(false);

        // Reset roll count
        rollCount = 0;
    }



}