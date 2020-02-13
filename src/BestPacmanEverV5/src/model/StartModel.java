package model;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class StartModel {
	private Paint bgColor = Color.WHITE;
	private Stage stage;
	private Stage gameStage = new Stage();
	private Group root = new Group();
	private Canvas canvas = new Canvas( 1225, 600 );
	
	public void setBgColor(Color color) {
		bgColor = color;
	}
	
	public Paint getBgColor() {
		return bgColor;
	}
	
	public Group getRoot() {
		return root;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public void setStage(Stage st) {
		stage = st;
	}
	
	public Stage getGameStage() {
		return gameStage;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}
