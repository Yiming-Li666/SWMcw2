package pacman;



import controller.StartController;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score {

    public Text score;
    public Text lifes;

    /**
     * Show the current score in the button of the game page.
     * @param root - root for gameManager
     */
    public Score(Group root) {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 28, "Score: 0");
        this.lifes = new Text(BarObstacle.THICKNESS * 20, BarObstacle.THICKNESS * 28,"Lifes:");
        ShowLife();
        ShowScoreMulti();
        score.setFill(Color.MAGENTA);
        score.setFont(Font.font("Arial", 30));

        lifes.setFill(Color.MAROON);
        lifes.setFont(Font.font("Arial", 30));

        root.getChildren().add(score);
        root.getChildren().add(lifes);
    }

    /**
     * Use pacman image to represents the number of lives left.
     */
	private void ShowLife() {
	    Image lifeImage = new Image("./image/pacman.png");
	    StartController.startModel.getRoot().getChildren().add(GameManager.view3);
		GameManager.view3.setImage(lifeImage);
		GameManager.view3.setFitWidth(25);
		GameManager.view3.setFitHeight(25);
		GameManager.view3.setLayoutX(660);
		GameManager.view3.setLayoutY(675);
		StartController.startModel.getRoot().getChildren().add(GameManager.view2);
        GameManager.view2.setImage(lifeImage);
        GameManager.view2.setFitWidth(25);
        GameManager.view2.setFitHeight(25);
        GameManager.view2.setLayoutX(630);
        GameManager.view2.setLayoutY(675);
        StartController.startModel.getRoot().getChildren().add(GameManager.view);
        GameManager.view.setImage(lifeImage);
        GameManager.view.setFitWidth(25);
        GameManager.view.setFitHeight(25);
        GameManager.view.setLayoutX(600);
        GameManager.view.setLayoutY(675);
	}
	
	/**
	 * Show score rate corresponding to different difficulty.
	 */
	private void ShowScoreMulti() {
		switch(GameManager.difficulty) {
			case 1:
				this.score.setText(this.score.getText() + " X1.0");
				break;
			case 2:
				this.score.setText(this.score.getText() + " X1.2");
				break;
			case 3:
				this.score.setText(this.score.getText() + " X1.5");
				break;
		}
	}
}
