package element;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pacman.BarObstacle;
import pacman.GameManager;
import pacman.Maze;

import java.util.Random;


public class Ghost extends Rectangle implements Runnable{
    String direction;
    GameManager gameManager;
    Maze maze;
    public AnimationTimer animation;
    int timesWalked;
    double step = 5;

    /**
     * Constructor
     * @param x - x-axis of ghost
     * @param y - y-axis of ghost
     * @param color - Color for the ghost
     * @param maze - the maze of gameManager
     * @param gameManager - gameManager
     */
    public Ghost(double x, double y, Color color, Maze maze, GameManager gameManager) {
        this.setX(x);
        this.setY(y);
        this.maze = maze;
        this.gameManager = gameManager;
        this.setHeight(50);
        this.setWidth(50);
        this.setFill(color);
        this.timesWalked = 0;
        this.direction = "down";
        this.createAnimation();
    }

    /**
     * Get random direction for the ghost to move.
     * @param exclude1 - the direction should not go to
     * @param exclude2 - the direction should not go to
     * @return directions[rnd] - the direction generated randomly
     */
    private String getRandomDirection(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }

    /**
     * Gets the animation for the ghost.
     * @return animation
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     * Check the if the pacman can move to that direction.
     * @param direction - new direction
     */
    public void checkIftheresPathToGo(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down":
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "up":
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
                    this.direction = direction;
                }
                break;
            case "left":
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "right":
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
        }
    }

    /**
     * Move until you cannot to left, try the 'down' direction.
     * If go through the left door, set pacman's coordinate to the right door.
     * @param leftEdge - leftEdge of ghost
     * @param topEdge - topEdge of ghost
     * @param padding - padding of ghost
     */
    public void moveUntilYouCantLeft(double leftEdge, double topEdge, double padding) {
    	if (!maze.isTouching(leftEdge, topEdge, padding)) {
        	if(getX() <= 0  && getY()>= 11* BarObstacle.THICKNESS && getY()<= 15* BarObstacle.THICKNESS) {
        		setX(1224.5);
        	}
            else {
            	setX(leftEdge - step);
            }
        } else {
            while (maze.isTouching(getX(), getY(), padding)) {
                	setX(getX() + 1);
            }
            direction = "down";
        }
    }
    
    /**
     * Move until you cannot to right, try the 'up' direction.
     * If go through the right door, set pacman's coordinate to the left door.
     * @param leftEdge - leftEdge of ghost
     * @param topEdge - topEdge of ghost
     * @param rightEdge - rightEdge of ghost
     * @param padding - padding of ghost
     */
    public void moveUntilYouCantRight(double rightEdge, double leftEdge, double topEdge, double padding) {
    	if (!maze.isTouching(rightEdge, topEdge, padding)) {
        	if(getX() >= 1225 && getY()>= 11* BarObstacle.THICKNESS && getY()<= 15* BarObstacle.THICKNESS) {
        		setX(0.5);
        	}
        	else {
        		setX(leftEdge + step);
        	}
        } else {
            while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
                    setX(getX() - 1);
            }
            direction = "up";
        }
    }
    
    /**
     * Move until you cannot to up, try the 'left' direction.
     * @param leftEdge - leftEdge of ghost
     * @param topEdge - topEdge of ghost
     * @param padding - padding of ghost
     */
    public void moveUntilYouCantUp(double leftEdge, double topEdge, double padding) {
    	if (!maze.isTouching(leftEdge, topEdge, padding)) {
            setY(topEdge - step);
        } else {
            while (maze.isTouching(getX(), getY(), padding)) {
                setY(getY() + 1);
            }
            direction = "left";
        }
    }
    
    /**
     * Move until you cannot to down, try the 'right' direction.
     * @param leftEdge - leftEdge of ghost
     * @param topEdge - topEdge of ghost
     * @param bottomEdge - bottomEdge of ghost
     * @param padding - padding of ghost
     */
    public void moveUntilYouCantDown(double leftEdge, double topEdge, double bottomEdge, double padding) {
    	if (!maze.isTouching(leftEdge, bottomEdge, padding)) {
            setY(topEdge + step);
        } else {
            while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
                setY(getY() - 1);
            }
            direction = "right";
        }
    }
    
    /**
     * Creates an animation of the ghost.
     * If the ghost is inside the start box, lead them to get out.
     * If there is no obstacle between ghost and pacman, ghost will randomly choose from two direction to approach pacman.
     */
    public void createAnimation() {

        this.animation = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gameManager.checkGhostCoalition();
                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 10;
                timesWalked++;
                int walkAtLeast = 20;
                ChangeStep();
                switch (direction) {
                    case "left":
                    	moveUntilYouCantLeft(leftEdge,topEdge, padding);
                        if (timesWalked > walkAtLeast) {
                        	Follow();
                            checkIftheresPathToGo(getRandomDirection("left", "right"));
                            GetOut(direction);
                            timesWalked = 0;
                        }
                        break;
                    case "right":
                    	moveUntilYouCantRight(rightEdge, leftEdge, topEdge, padding);
                        if (timesWalked > walkAtLeast) {
                        	Follow();
                            checkIftheresPathToGo(getRandomDirection("left", "right"));
                            direction = GetOut(direction);
                             timesWalked = 0;
                        }
                        break;
                    case "up":
                    	moveUntilYouCantUp(leftEdge, topEdge, padding);
                        if (timesWalked > walkAtLeast) {
                        	Follow();
                            checkIftheresPathToGo(getRandomDirection("up", "down"));
                            direction =GetOut(direction);
                            timesWalked = 0;
                        }
                        break;
                    case "down":
                    	moveUntilYouCantDown(leftEdge, topEdge, bottomEdge, padding);
                        if (timesWalked > walkAtLeast) {
                        	Follow();
                            checkIftheresPathToGo(getRandomDirection("up", "down"));
                            direction = GetOut(direction);
                            timesWalked = 0;
                        }
                        break;
                }
            }

            /**
             * Make the ghost have a great chance to move toward the pacman.
             * Check if there is obstacle between pacman and ghost.
             * Ghost will choose one of the direction to approach the pacman.
             */
            private void Follow() {
        		if(!maze.hasObstacle(GameManager.pacman.getCenterX(), getX(), GameManager.pacman.getCenterY(), getY())) {
        			// left-up
        			if(GameManager.pacman.getCenterX() < getX() && GameManager.pacman.getCenterY() < getY()) {
        				checkIftheresPathToGo(getRandomDirection("right", "down"));
        			}
        			// left-down
        			else if(GameManager.pacman.getCenterX() < getX() && GameManager.pacman.getCenterY() > getY()) {
        				checkIftheresPathToGo(getRandomDirection("right", "up"));
        			}
        			// right-up
        			else if(GameManager.pacman.getCenterX() > getX() && GameManager.pacman.getCenterY() < getY()) {
        				checkIftheresPathToGo(getRandomDirection("left", "down"));
        			}
        			// right-down
        			else if(GameManager.pacman.getCenterX() > getX() && GameManager.pacman.getCenterY() > getY()) {
        				checkIftheresPathToGo(getRandomDirection("left", "up"));
        			}
        		}
            }
            
            /**
             * Lead the ghost to get out of the start box.
             * Guide the ghost to the center of the start box and set the direction to 'up'.
             * @param direction - the original direction
             * @return direction - new direction
             */
            private String GetOut(String direction) {
            	if(getY() > 8 * BarObstacle.THICKNESS && getY() < 600 - 8 * BarObstacle.THICKNESS) {
            		if(getX() > 17 * BarObstacle.THICKNESS && getX() < 22 * BarObstacle.THICKNESS) {
            			direction = "right";
            		}
            		else if(getX() > 27 * BarObstacle.THICKNESS && getX() < 32 * BarObstacle.THICKNESS) {
            			direction = "left";
            		}
            		else if(getX() > 22 * BarObstacle.THICKNESS && getX() < 27 * BarObstacle.THICKNESS) {
            			direction = "up";
            		}
            	}
            	return direction;
            }
            
            /**
             * Change the step of ghost in unit time.
             * Set different speed for ghosts corresponding to difficulty.
             */
			private void ChangeStep() {
				switch(GameManager.difficulty) {
					case 1:
						step = 5;
						break;
					case 2:
						step = 6;
						break;
					case 3:
						step = 7;
						break;
				}
			}
        };
    }

    @Override
    public void run() {
        this.animation.start();
    }
}
