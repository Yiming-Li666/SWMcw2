package state;

import java.io.IOException;

import controller.StartController;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import pacman.BarObstacle;
import pacman.GameManager;

public class RunningState implements State {

	/**
	 * Set the status to running.
	 * Remove the original board, scoreStage, cookieSet and ghosts.
	 * Create new board and add pacman, set life to 3 and set score to 0.
	 * @param gm - GameManager
	 * @throws IOException - IOException
	 */
	@Override
	public void doAction(GameManager gm) throws IOException{
		if(gm.scoreStage.isShowing()) {
			gm.scoreStage.close();
    	}
		gm.root.getChildren().clear();
		gm.cookieSet.clear();
        gm.ghosts.clear();
        StackPane holder = new StackPane();
        holder.getChildren().add(StartController.startModel.getCanvas());
        Background bg = new Background(new BackgroundFill(StartController.startModel.getBgColor(), null, null));
        holder.setBackground(bg);
        gm.root.getChildren().add(holder);
        gm.drawBoard();
        gm.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
        gm.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
        gm.lifes = 3;
        gm.score = 0;
        gm.cookiesEaten = 0;
        gm.gameEnded = false;
	}

}
