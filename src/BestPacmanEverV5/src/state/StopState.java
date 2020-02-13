package state;

import java.io.IOException;

import element.Ghost;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pacman.GameManager;

public class StopState implements State {

	/**
	 * Set the status to stop.
	 * Stop all the animation for pacman and ghosts.
	 * Remove pacman, ghosts, scoreStage.
	 * Open the score page.
	 * Create new board and add pacman, set life to 3 and set score to 0.
	 * @param gm - GameManager
	 * @throws IOException - IOException
	 */
	@Override
	public void doAction(GameManager gm) throws IOException {
		gm.gameEnded = true;
        gm.rightPacmanAnimation.stop();
        gm.leftPacmanAnimation.stop();
        gm.upPacmanAnimation.stop();
        gm.downPacmanAnimation.stop();
        gm.root.getChildren().remove(gm.pacman);
        
        for (Ghost ghost : gm.ghosts) {
        	ghost.animation.stop();
            gm.root.getChildren().remove(ghost);
        }
        gm.root.getChildren().remove(gm.scoreBoard.score);
        gm.root.getChildren().remove(gm.scoreBoard.lifes);
        gm.scoreStage = new Stage();
		Parent root3 = FXMLLoader.load(getClass().getResource("../view/Score.fxml"));
        Scene theScene = new Scene( root3, 600, 400 );
        theScene.getStylesheets().add("./cssStyle/ScoreStyle.css");
        gm.scoreStage.setResizable(false);
        gm.scoreStage.setScene( theScene );
        gm.scoreStage.show();

	}

}
