package game.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class tryGameApplication extends Application{

        @Override
        public void start(Stage primaryStage) throws Exception {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResizablePane.fxml"));
            Parent root = loader.load();

            // Set the controller for the FXML file
            tryGameController controller = loader.getController();

            // Set up the stage
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Resizable Pane Example");
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

