package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.StartModel;
import pacman.BarObstacle;
import pacman.GameManager;

public class StartController {
	@FXML FlowPane Flow;
	@FXML public static Pane Outter;
	@FXML ImageView Header;
	@FXML Pane HeadPane;
	@FXML ImageView Left;
	@FXML Pane LeftPane;
	@FXML ImageView Right;
	@FXML Pane RightPane;
	@FXML public Button StartBtn;
	@FXML private Button SettingBtn;
//	public static Paint bgColor = Color.WHITE;
//	public static Stage stage;
//	public static Stage gameStage = new Stage();
//	public static Group root = new Group();
//	public static Canvas canvas;
	
	public static StartModel startModel = new StartModel();
	/**
	 * initialize the start page.
	 */
	@FXML
	public void initialize() {
		Image image1 = new Image("./image/image1.png");
		Header.setImage(image1);
		Header.setLayoutX(HeadPane.getPrefWidth()-image1.getWidth()*1.08);
		Image image2 = new Image("./image/image2.gif");
		Left.setImage(image2);
		Left.setLayoutX(100);
		Image image3 = new Image("./image/image3.gif");
		Right.setImage(image3);
		Right.setLayoutX(70);
	}
	
	/**
	 *  OnAction for StartBtn
	 *  When click on Start button, create the game interface and hide the start interface.
	 */
	public void ClickOnStart(){
        Scene theScene = new Scene( startModel.getRoot() );
        startModel.getGameStage().setResizable(false);
        startModel.getGameStage().setScene( theScene );
        //canvas = new Canvas( 1225, 600 );
        // set background color of the maze
        StackPane holder = new StackPane();
        holder.getChildren().add(startModel.getCanvas());
        Background bg = new Background(new BackgroundFill(startModel.getBgColor(), null, null));
        holder.setBackground(bg);
        startModel.getRoot().getChildren().add(holder);
        GameManager gameManager = new GameManager(startModel.getRoot());
        
        gameManager.drawBoard();
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
        theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
        theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			try {
				gameManager.restartGame(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
        startModel.setStage((Stage)StartBtn.getScene().getWindow());
        startModel.getStage().hide();
        
        startModel.getGameStage().show();
        
	}
	
	/**
	 *  OnAction for SettingBtn
	 *  When click on Setting button, create the setting interface and hide the start interface.
	 *  @throws IOException - IOException
	 */
	public void ClickOnSetting() throws IOException{
		Stage settingStage = new Stage();
		Parent root2 = FXMLLoader.load(getClass().getResource("../view/Setting.fxml"));
        Scene theScene = new Scene( root2, 1225, 600 );
        theScene.getStylesheets().add("./cssStyle/SettingStyle.css");
        settingStage.setResizable(false);
        settingStage.setScene( theScene );
        startModel.setStage((Stage)StartBtn.getScene().getWindow());
        startModel.getStage().hide();
        startModel.setBgColor(Color.WHITE);
        BarObstacle.color = Color.CADETBLUE;
        settingStage.show();
	}
	
	/**
	 *  Mouse MoveOn and MoveExit Action for StartBtn and SettingBtn
	 *  When move on button, change the color of text to yellow and add shadow to the button.
	 */
	public void StartOnEnter(){
        StartBtn.setTextFill(Color.YELLOW);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.GREY);
        StartBtn.setEffect(ds);
    }
	
	public void StartOnExit(){
        StartBtn.setTextFill(Color.WHITE);
        StartBtn.setEffect(null);
    }
	
	public void SettingOnEnter(){
        SettingBtn.setTextFill(Color.YELLOW);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.GREY);
        SettingBtn.setEffect(ds);
    }
	
	public void SettingOnExit(){
        SettingBtn.setTextFill(Color.WHITE);
        SettingBtn.setEffect(null);
    }
}
