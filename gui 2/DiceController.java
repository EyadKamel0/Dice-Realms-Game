package game.gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.*;
import game.creatures.*;

import java.awt.Button;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
 

	
public class DiceController {
//	CLIGameController x;
	private Label nameLabel;
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 CLIGameController x=new CLIGameController();
	 
	 @FXML
	 private  Button dragon1Button;
	 @FXML
	 private  Button dragon2Button;
	 @FXML
	 private  Button dragon3Button;
	 @FXML
	 private  Button dragon4Button;
	 @FXML
	 private  ImageView dragon1image;
	 @FXML
	 private  ImageView dragon2image;
	 @FXML
	 private  ImageView dragon3image;
	 @FXML
	 private  ImageView dragon4image;
	 Player player;
	 Dice dice;

    
    private MediaView mediaView;
//
//   public DiceController(){
//	   player =
//	   Dice = ()		   
//   }

    
//    public  int  getDiceNumber(){
//    	return dice.getValue();
//    }
//	   public  void whattoattack(){
//		//   DragonNumber [] DN = new DragonNumber[2];
//		 Move[] temp =  x.getPossibleMovesForADie(player, dice);
//	     if(temp.length==2){         //Red function
//	         switch ( ((RedDice) temp[0].getDice()).getDragon()){
//	         case Dragon1: dragon1Button.setVisible(true);
//	                       dragon1image.setVisible(false);
//	         case Dragon2: dragon2Button.setVisible(true);
//	         			   dragon2image.setVisible(false);
//	         case Dragon3: dragon3Button.setVisible(true);
//	         			   dragon3image.setVisible(false);
//	         case Dragon4: dragon4Button.setVisible(true);
//	                       dragon4image.setVisible(false);
//			default:
//				break;
//	         }
//	      
//	        	 
//	         switch ( ((RedDice) temp[1].getDice()).getDragon()){
//	         case Dragon1: dragon1Button.setVisible(true);
//	                       dragon1image.setVisible(false);
//	         case Dragon2: dragon2Button.setVisible(true);
//	         			   dragon2image.setVisible(false);
//	         case Dragon3: dragon3Button.setVisible(true);
//	         			   dragon3image.setVisible(false);
//	         case Dragon4: dragon4Button.setVisible(true);
//	                       dragon4image.setVisible(false);
//			default:
//				break;
//	         }
//	         
//	         
//	        
////	         DN[0]=( ((RedDice) temp[0].getDice()).getDragon());
////	         DN[1]=( ((RedDice) temp[1].getDice()).getDragon());
////	        return DN;
//	              }
//	     else if(temp.length==1){
//	         switch ( ((RedDice) temp[0].getDice()).getDragon()){
//	         case Dragon1: dragon1Button.setVisible(true);
//	                       dragon1image.setVisible(false);
//	         case Dragon2: dragon2Button.setVisible(true);
//	         			   dragon2image.setVisible(false);
//	         case Dragon3: dragon3Button.setVisible(true);
//	         			   dragon3image.setVisible(false);
//	         case Dragon4: dragon4Button.setVisible(true);
//	                       dragon4image.setVisible(false);
//			default:
//				break;
//	         
//	     }
////	         DN[0]=( ((RedDice) temp[0].getDice()).getDragon());
////	         return DN;
//	     }
//	     else{
//	    	 
//	     }
//    	 
//   
// 
//}
//	   public boolean isDrag4Attackable() {
//		   whattoattack();
//		   
//		return dragon4Button.isVisible();
//	}
//	   public boolean isDrag3Attackable() {
//		   whattoattack();
//		   
//		return dragon3Button.isVisible();
//	}
//	   public boolean isDrag2Attackable() {
//		   whattoattack();
//		   
//		return dragon2Button.isVisible();
//	}
//	   public boolean isDrag1Attackable() {
//		   whattoattack();
//		   
//		return dragon1Button.isVisible();
//	}
//	public int AttackIndex( DragonNumber d){
//		   Move[] temp =  x.getPossibleMovesForADie(player, dice);
//		   if(temp[0]!=null && temp[1]!=null){
//		   if((((RedDice) temp[0].getDice()).getDragon())== d)
//			  return 0; 
//		   else if ((((RedDice) temp[1].getDice()).getDragon())== d)
//			   return 1;
//		   else
//			   return -1;
//	   }
//		   else if(temp[0]!=null && temp[1]==null){
//			   if((((RedDice) temp[0].getDice()).getDragon())== d){
//				   return 0;
//			   }
//			   else
//				   return -1;
//	   }
//		   else
//			   return -1;
//	   }
//	   
//

	@FXML
    private void switchToDragon1Scene(ActionEvent event) throws IOException {
   	 Parent root = FXMLLoader.load(getClass().getResource("Dragon1Scene.fxml"));
  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  	  scene = new Scene(root);
  	  stage.setScene(scene);
  	  stage.setFullScreen(true);
  	  stage.setResizable(false);
  	  stage.show();
   }
    

    @FXML
    private void switchToDragon2Scene(ActionEvent event) throws IOException {
    	 Parent root = FXMLLoader.load(getClass().getResource("Dragon2Scene.fxml"));
   	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   	  scene = new Scene(root);
   	  stage.setScene(scene);
   	  stage.setFullScreen(true);
   	  stage.setResizable(false);
   	  stage.show();
    }

    @FXML
    private void switchToDragon3Scene(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Dragon3Scene.fxml"));
     	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     	  scene = new Scene(root);
     	  stage.setScene(scene);
     	  stage.setFullScreen(true);
     	  stage.setResizable(false);
     	  stage.show();
    }

    @FXML
    private void switchToDragon4Scene(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Dragon4Scene.fxml"));
     	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
     	  scene = new Scene(root);
     	  stage.setScene(scene);
     	  stage.setFullScreen(true);
     	  stage.setResizable(false);
     	  stage.show();
    }
    
    // Method to simulate rolling the dice
    
}

