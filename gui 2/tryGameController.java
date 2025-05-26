package game.gui;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class tryGameController {

    @FXML
    private SplitPane splitPane;

    @FXML
    private ImageView leftImageView;

    @FXML
    private ImageView rightImageView;

    public void initialize() {
        // Load images
        Image image = new Image("file:/Users/eyad/Documents/Game/dice-realms-game-le3ba/src/main/resources/Round/frame_0001.png");

        // Set images to ImageViews
        leftImageView.setImage(image);
        rightImageView.setImage(image);
    }
}
