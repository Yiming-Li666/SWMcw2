package controller;

import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SettingModel;
import pacman.BarObstacle;
import pacman.GameManager;

public class SettingController {
	@FXML ImageView HeaderImg;
	@FXML ImageView MazeView;
	@FXML ImageView FstLeft;
	@FXML ImageView FstRight;
	@FXML ImageView SndLeft;
	@FXML ImageView SndRight;
	@FXML ImageView TrdLeft;
	@FXML ImageView TrdRight;
	@FXML ImageView FthLeft;
	@FXML ImageView FthRight;
	@FXML Pane Header;
	@FXML Text Setting;
	@FXML Text Difficulty;
	@FXML Text GhostNum;
	@FXML Rectangle BgColor;
	@FXML Rectangle BdColor;
	@FXML private Button ConfirmBtn;
	
	SettingModel setModel = new SettingModel();
	
	/**
	 * initialize the setting page.
	 */
	@FXML
	public void initialize() {
		Image SetImage = new Image("./image/setting.jpeg");
		HeaderImg.setImage(SetImage);
		Image triImage = new Image("./image/tri.png");
		FstLeft.setImage(triImage);
		FstLeft.setRotate(180);
		FstRight.setImage(triImage);
		SndLeft.setImage(triImage);
		SndLeft.setRotate(180);
		SndRight.setImage(triImage);
		TrdLeft.setImage(triImage);
		TrdLeft.setRotate(180);
		TrdRight.setImage(triImage);
		FthLeft.setImage(triImage);
		FthLeft.setRotate(180);
		FthRight.setImage(triImage);
		MazeView.setImage(new Image("./preview/WHITE-CADETBLUE.png"));
	}
	
	/**
	 * Switch the color for the background.
	 * Eight color can be chosen.
	 */
	public void BgColorSwitch() {
		switch(setModel.getBgCounter()%8) {
			case 1:
				StartController.startModel.setBgColor(Color.WHITE);
				//StartController.bgColor = Color.WHITE;
				SetImage();
				 break;
			case 2:
				StartController.startModel.setBgColor(Color.BLACK);
				//StartController.bgColor = Color.BLACK;
				SetImage();
				 break;
			case 3:
				StartController.startModel.setBgColor(Color.DARKRED);
				//StartController.bgColor = Color.DARKRED;
				SetImage();
				 break;
			case 4:
				StartController.startModel.setBgColor(Color.BURLYWOOD);
				//StartController.bgColor = Color.BURLYWOOD;
				SetImage();
				 break;
			case 5:
				StartController.startModel.setBgColor(Color.LIGHTGOLDENRODYELLOW);
				//StartController.bgColor = Color.LIGHTGOLDENRODYELLOW;
				SetImage();
				 break;
			case 6:
				StartController.startModel.setBgColor(Color.FORESTGREEN);
				//StartController.bgColor = Color.FORESTGREEN;
				SetImage();
				 break;
			case 7:
				StartController.startModel.setBgColor(Color.CADETBLUE);
				//StartController.bgColor = Color.CADETBLUE;
				SetImage();
				 break;
			case 0:
				StartController.startModel.setBgColor(Color.MEDIUMPURPLE);
				//StartController.bgColor = Color.MEDIUMPURPLE;
				SetImage();
				 break;
		}
	}
	
	/**
	 * Switch the color for the obstacle.
	 * Eight color can be chosen.
	 */
	public void ObColorSwitch() {
		switch(setModel.getObCounter()%8) {
			case 1:
				BarObstacle.color = Color.CADETBLUE;
				SetImage();
				 break;
			case 2:
				BarObstacle.color = Color.BLACK;
				SetImage();
				 break;
			case 3:
				BarObstacle.color = Color.DARKRED;
				SetImage();
				 break;
			case 4:
				BarObstacle.color = Color.BURLYWOOD;
				SetImage();
				 break;
			case 5:
				BarObstacle.color = Color.LIGHTGOLDENRODYELLOW;
				SetImage();
				 break;
			case 6:
				BarObstacle.color = Color.FORESTGREEN;
				SetImage();
				 break;
			case 7:
				BarObstacle.color = Color.WHITE;
				SetImage();
				 break;
			case 0:
				BarObstacle.color = Color.MEDIUMPURPLE;
				SetImage();
				 break;
		}
	}
	
	/**
	 * Switch the difficulty of the game
	 * Three difficulty can be chosen.
	 */
	public void DifSwitch() {
		switch(setModel.getDif()%3) {
			case 1:
				Difficulty.setText("Easy");
				GameManager.difficulty = 1;
				break;
			case 2:
				Difficulty.setText("Medium");
				GameManager.difficulty = 2;
				break;
			case 0:
				Difficulty.setText("Difficult");
				GameManager.difficulty = 3;
				break;
		}
	}
	
	/**
	 * Switch the ghost number of the game.
	 * Ghost number can be chosen from three to five.
	 */
	public void GhostSwitch() {
		switch(setModel.getGhostNum()%3) {
			case 1:
				GhostNum.setText("Three");
				GameManager.ghostNum = 3;
				 break;
			case 2:
				GhostNum.setText("Four");
				GameManager.ghostNum = 4;
				 break;
			case 0:
				GhostNum.setText("Five");
				GameManager.ghostNum = 5;
				 break;
		}
	}
	
	/**
	 * Set the image of preview
	 */
	public void SetImage() {
		if(StartController.startModel.getBgColor() == Color.WHITE) {
			if(BarObstacle.color == Color.CADETBLUE) {
				MazeView.setImage(new Image("./preview/WHITE-CADETBLUE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/WHITE-BLACK.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/WHITE-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/WHITE-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/WHITE-LIGHTGOLDENRODYELLOW.png"));
			}
			else if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/WHITE-FORESTGREEN.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/WHITE-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.BLACK) {
			if(BarObstacle.color == Color.CADETBLUE) {
				MazeView.setImage(new Image("./preview/BLACK-CADETBLUE.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/BLACK-WHITE.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/BLACK-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/BLACK-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/BLACK-LIGHTGOLDENRODYELLOW.png"));
			}
			else if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/BLACK-FORESTGREEN.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/BLACK-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.DARKRED) {
			if(BarObstacle.color == Color.CADETBLUE) {
				MazeView.setImage(new Image("./preview/DARKRED-CADETBLUE.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/DARKRED-WHITE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/DARKRED-BLACK.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/DARKRED-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/DARKRED-LIGHTGOLDENRODYELLOW.png"));
			}
			else if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/DARKRED-FORESTGREEN.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/DARKRED-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.BURLYWOOD) {
			if(BarObstacle.color == Color.CADETBLUE) {
				MazeView.setImage(new Image("./preview/BURLYWOOD-CADETBLUE.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/BURLYWOOD-WHITE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/BURLYWOOD-BLACK.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/BURLYWOOD-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/BURLYWOOD-LIGHTGOLDENRODYELLOW.png"));
			}
			else if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/BURLYWOOD-FORESTGREEN.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/BURLYWOOD-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.LIGHTGOLDENRODYELLOW) {
			if(BarObstacle.color == Color.CADETBLUE) {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-CADETBLUE.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-WHITE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-BLACK.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-FORESTGREEN.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/LIGHTGOLDENRODYELLOW-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.FORESTGREEN) {
			if(BarObstacle.color == Color.CADETBLUE) {
				MazeView.setImage(new Image("./preview/FORESTGREEN-CADETBLUE.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/FORESTGREEN-WHITE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/FORESTGREEN-BLACK.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/FORESTGREEN-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/FORESTGREEN-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/FORESTGREEN-LIGHTGOLDENRODYELLOW.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/FORESTGREEN-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.CADETBLUE) {
			if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/CADETBLUE-FORESTGREEN.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/CADETBLUE-WHITE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/CADETBLUE-BLACK.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/CADETBLUE-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/CADETBLUE-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/CADETBLUE-LIGHTGOLDENRODYELLOW.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/CADETBLUE-MEDIUMPURPLE.png"));
			}
		}
		if(StartController.startModel.getBgColor() == Color.MEDIUMPURPLE) {
			if(BarObstacle.color == Color.FORESTGREEN) {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-FORESTGREEN.png"));
			}
			else if(BarObstacle.color == Color.WHITE) {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-WHITE.png"));
			}
			else if(BarObstacle.color == Color.BLACK) {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-BLACK.png"));
			}
			else if(BarObstacle.color == Color.DARKRED) {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-DARKRED.png"));
			}
			else if(BarObstacle.color == Color.BURLYWOOD) {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-BURLYWOOD.png"));
			}
			else if(BarObstacle.color == Color.LIGHTGOLDENRODYELLOW) {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-LIGHTGOLDENRODYELLOW.png"));
			}
			else {
				MazeView.setImage(new Image("./preview/MEDIUMPURPLE-CADETBLUE.png"));
			}
		}
	}
	
	/**
	 * the switch controller for the selection of color and difficulty.
	 * the left button to click is to select previous color/difficulty.
	 * the right button to click is to select next color/difficulty.
	 */
	public void BgAddValue() {
		setModel.setBgCounter(setModel.getBgCounter() + 1);
		BgColorSwitch();
		if(StartController.startModel.getBgColor() == BarObstacle.color) {
			setModel.setBgCounter(setModel.getBgCounter() + 1);
			BgColorSwitch();
		}
		BgColor.setFill(StartController.startModel.getBgColor());
	}
	
	public void BgminusValue() {
		setModel.setBgCounter(setModel.getBgCounter() - 1);
		BgColorSwitch();
		if(StartController.startModel.getBgColor() == BarObstacle.color) {
			setModel.setBgCounter(setModel.getBgCounter() - 1);
			BgColorSwitch();
		}
		BgColor.setFill(StartController.startModel.getBgColor());
	}
	
	public void ObAddValue() {
		setModel.setObCounter(setModel.getObCounter() + 1);
		ObColorSwitch();
		if(StartController.startModel.getBgColor() == BarObstacle.color) {
			setModel.setObCounter(setModel.getObCounter() + 1);
			ObColorSwitch();
		}
		BdColor.setFill(BarObstacle.color);
	}
	
	public void ObminusValue() {
		setModel.setObCounter(setModel.getObCounter() - 1);
		ObColorSwitch();
		if(StartController.startModel.getBgColor() == BarObstacle.color) {
			setModel.setObCounter(setModel.getObCounter() + 1);
			ObColorSwitch();
		}
		BdColor.setFill(BarObstacle.color);
	}
	
	public void DifMinusValue() {
		setModel.setDif(setModel.getDif() - 1);
		DifSwitch();
	}
	
	public void DifAddValue() {
		setModel.setDif(setModel.getDif() + 1);
		DifSwitch();
	}
	
	public void GhostMinusValue() {
		setModel.setGhostNum(setModel.getGhostNum() - 1);
		GhostSwitch();
	}
	
	public void GhostAddValue() {
		setModel.setGhostNum(setModel.getGhostNum() + 1);
		GhostSwitch();
	}
	
	/**
	 * OnAction for OK button
	 * When clicked, close the setting interface and show the start interface.
	 */
	public void confirm() {
		Stage settingStage = (Stage)ConfirmBtn.getScene().getWindow();
		settingStage.close();
		StartController.startModel.getStage().show();
	}
	
	/**
	 *  Mouse MoveOn and MoveExit Action for switch controller and OK button
	 *  When move on button, change the color of text to yellow and add shadow to the button.
	 */
	public void OKOnEnter(){
		ConfirmBtn.setTextFill(Color.YELLOW);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.GREY);
        ConfirmBtn.setEffect(ds);
    }
	public void OKOnExit(){
		ConfirmBtn.setTextFill(Color.WHITE);
		ConfirmBtn.setEffect(null);
    }
	
	public void onFstLeft() {
		DropShadow ds = new DropShadow();
        ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
		FstLeft.setEffect(ds);
	}
	public void leaveFstLeft() {
		FstLeft.setEffect(null);
	}
	
	public void onFstRight() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        FstRight.setEffect(ds);
	}
	public void leaveFstRight() {
		FstRight.setEffect(null);
	}
	
	public void onSndLeft() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        SndLeft.setEffect(ds);
	}
	public void leaveSndLeft() {
		SndLeft.setEffect(null);
	}
	
	public void onSndRight() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        SndRight.setEffect(ds);
	}
	public void leaveSndRight() {
		SndRight.setEffect(null);
	}
	
	public void onTrdLeft() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        TrdLeft.setEffect(ds);
	}
	public void leaveTrdLeft() {
		TrdLeft.setEffect(null);
	}
	
	public void onTrdRight() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        TrdRight.setEffect(ds);
	}
	public void leaveTrdRight() {
		TrdRight.setEffect(null);
	}
	
	public void onFthLeft() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        FthLeft.setEffect(ds);
	}
	public void leaveFthLeft() {
		FthLeft.setEffect(null);
	}
	
	public void onFthRight() {
		DropShadow ds = new DropShadow();
		ds.setOffsetY(5.0f);
        ds.setColor(Color.WHITE);
        FthRight.setEffect(ds);
	}
	public void leaveFthRight() {
		FthRight.setEffect(null);
	}
}
