package element;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import pacman.BarObstacle;
import pacman.GameManager;

public class GhostFactory {
	
	/**
	 * To initialize ghost in factory pattern.
	 * By initializing a GhostFactory, can invoke createGhost("name") with the name of ghost to initialize a ghost.
	 * @param name - the name of ghost
	 * @param gm - GameManager
	 * @return ghost - initialized ghost
	 */
	public Ghost createGhost(String name, GameManager gm) {
		if("ghost1".equalsIgnoreCase(name)) {
			Ghost ghost1 = new Ghost(18.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.DEEPPINK, GameManager.maze, gm);
	    	Image im1 = new Image("./image/ghost1.png");
	    	ghost1.setFill(new ImagePattern(im1));
	    	return ghost1;
		}
    	else if("ghost2".equalsIgnoreCase(name)) {
	    	Ghost ghost2 = new Ghost(22.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.GREENYELLOW, GameManager.maze, gm);
	        Image im2 = new Image("./image/ghost2.png");
	        ghost2.setFill(new ImagePattern(im2));
	    	return ghost2;
		}
    	else if("ghost3".equalsIgnoreCase(name)) {
    		Ghost ghost3 = new Ghost(22.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.GREENYELLOW, GameManager.maze, gm);
        	Image im3 = new Image("./image/ghost3.png");
        	ghost3.setFill(new ImagePattern(im3));
	    	return ghost3;
		}
    	else if("ghost4".equalsIgnoreCase(name)) {
    		Ghost ghost4 = new Ghost(28.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, Color.BLACK, GameManager.maze, gm);
            Image im4 = new Image("./image/ghost4.png");
            ghost4.setFill(new ImagePattern(im4));
        	return ghost4;
    	}
    	else if("ghost5".equalsIgnoreCase(name)) {
    		Ghost ghost5 = new Ghost(28.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, Color.SPRINGGREEN, GameManager.maze, gm);
            Image im5 = new Image("./image/ghost5.png");
            ghost5.setFill(new ImagePattern(im5));
        	return ghost5;
    	}
    	else {
    		return null;
    	}
	}
}
