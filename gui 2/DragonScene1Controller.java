package game.gui;
import java.awt.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import game.creatures.DragonNumber;
import application.DiceController;

public class DragonScene1Controller {
DiceController x;
int index;
    @FXML
    private Button back;
    private Stage stage;
    private Scene scene;
    private Parent root;
//public void setWhatToAttack(){
//boolean f = x.isDrag1Attackable();
//	if(f){
//	index =x.AttackIndex(DragonNumber.Dragon1);
//	int y= x.getDiceNumber();
//	// loop on dragon 1 
//	
//	}
//		
//		
//}
       public void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DiceView.fxml"));	
		root = loader.load();	
		//DiceController DiceController = loader.getController();
		root = FXMLLoader.load(getClass().getResource("DiceView.fxml"));	
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
	    stage.setFullScreen(false);
	    stage.setResizable(false);
		stage.show();
    }
//       public int attack (ActionEvent event) throws IOException {
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("DiceView.fxml"));	
//   		root = loader.load();	
//   		//DiceController DiceController = loader.getController();
//   		root = FXMLLoader.load(getClass().getResource("DiceView.fxml"));	
//   		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//   		scene = new Scene(root);
//   		stage.setScene(scene);
//   	    
//   		stage.show();
//   		return index;
//       }
       
}