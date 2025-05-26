import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Example components
        Label topLabel = new Label("Top");
        Label bottomLabel = new Label("Bottom");
        Label centerLabel = new Label("Center");
        Label leftLabel = new Label("Left");
        Label rightLabel = new Label("Right");

        // Add components to BorderPane
        root.setTop(topLabel);
        root.setBottom(bottomLabel);
        root.setCenter(centerLabel);
        root.setLeft(leftLabel);
        root.setRight(rightLabel);

        // Create scene with initial size
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("JavaFX Resizable Scene Example");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Start maximized
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
