package pacman;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import layout.CookieLayout;
import layout.PacmanLayout;
import state.RunningState;
import state.StopState;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import element.Cookie;
import element.Ghost;
import element.GhostFactory;
import element.Pacman;

public class GameManager {
    public static Pacman pacman;
    public Group root;
    public Set<Cookie> cookieSet;
    public Set<Ghost> ghosts;
    public AnimationTimer leftPacmanAnimation;
    public AnimationTimer rightPacmanAnimation;
    public AnimationTimer upPacmanAnimation;
    public AnimationTimer downPacmanAnimation;
    public static Maze maze;
    public int lifes;
    public static double score;
    public Score scoreBoard;
    public boolean gameEnded;
    public int cookiesEaten;
    public Stage scoreStage;
    public static int difficulty = 1;
    public static int ghostNum = 3;
    Image lifeImage = new Image("./image/pacman.png");
    public static ImageView view3 = new ImageView();
    public static ImageView view2 = new ImageView();
    public static ImageView view = new ImageView();
    
    /**
     * Constructor
     * @param root - root for gameManager
     */
    public GameManager(Group root) {
        this.root = root;
        this.maze = new Maze();
        this.pacman = Pacman.getInstance(2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
        this.cookieSet = new HashSet<>();
        this.ghosts = new HashSet<>();
        this.leftPacmanAnimation = this.createAnimation("left");
        this.rightPacmanAnimation = this.createAnimation("right");
        this.upPacmanAnimation = this.createAnimation("up");
        this.downPacmanAnimation = this.createAnimation("down");
        this.lifes = 3;
        this.score = 0;
        this.cookiesEaten = 0;
    }

    /**
     * Invoke when pacman died each time.
     * Reset pacman to the original start point and if there is a ghost near this point, reset it to the start box.
     * Minus a life and minus 10 to the score.
     * If do not have more lives, open the score page.
     */
    private void lifeLost() {
        this.leftPacmanAnimation.stop();
        this.rightPacmanAnimation.stop();
        this.upPacmanAnimation.stop();
        this.downPacmanAnimation.stop();
        for (Ghost ghost : ghosts) {
            ghost.getAnimation().stop();
            if(ghost.getX() < 5 * BarObstacle.THICKNESS && ghost.getY() < 5 * BarObstacle.THICKNESS) {
            	ghost.setX(22.5 * BarObstacle.THICKNESS);
            	ghost.setY(12.5 * BarObstacle.THICKNESS);
            }
        }
        this.pacman.setCenterX(2.5 * BarObstacle.THICKNESS);
        this.pacman.setCenterY(2.5 * BarObstacle.THICKNESS);
        lifes--;
        score -= 10;
        this.scoreBoard.lifes.setText("Lifes: ");
        ShowLife();
        this.scoreBoard.score.setText("Score: " + this.score);
        if (lifes == 0) {
        	switch(GameManager.difficulty) {
        		case 1:
        			this.addFile(String.valueOf(this.score * 1.0), "./src/pacman/Score.csv");
        			break;
        		case 2:
        			this.addFile(String.valueOf(this.score * 1.2), "./src/pacman/Score.csv");
        			break;
        		case 3:
        			this.addFile(String.valueOf(this.score * 1.5), "./src/pacman/Score.csv");
        			break;
        	}
            try {
				this.endGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
            javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
            endGame.setX(BarObstacle.THICKNESS * 3);
            endGame.setY(BarObstacle.THICKNESS * 28);
            endGame.setFont(Font.font("Arial", 40));
            endGame.setFill(Color.ROYALBLUE);
            root.getChildren().add(endGame);
        }
    }

    /**
     * Calculate the lives left.
     * Use image of pacman to represent the lives left.
     * When lose one life, remove one pacman image.
     */
    private void ShowLife() {
    	if(lifes == 2) {
    		view3.setImage(null);
    		root.getChildren().remove(view3);
    	}
    	if(lifes == 1) {
    		view2.setImage(null);
    		root.getChildren().remove(view2);
    	}
    	if(lifes == 0) {
    		view.setImage(null);
    		root.getChildren().remove(view);
    	}
	}

    /**
     * Write current score to the end of the file.
     * @param string - the score in String format
     * @param path - the path of the file
     */
	public void addFile(String string, String path) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(path,true);
            fw.write(string);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    /**
     * Ends the game.
     * When the game ends, change the state to StopState.
     * @throws IOException - IOException
     */
    private void endGame() throws IOException {
    	if(this.gameEnded){
    		return;
    	}
    	StopState end = new StopState();
    	end.doAction(this);
    }

    /**
     * Restart the game
     * When the game restarts, change the state to RunningState.
     * @param event - keyboard input
     * @throws IOException - IOException
     */
    public void restartGame(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
        	RunningState start = new RunningState();
        	start.doAction(this);
        }
    }
    
    /**
     * Generate cookies for each line
     * @param skip[] - Integer array contain all the coordinates which should not have a cookie on.
     * @param yaxis - y-axis of cookie
     */
    private void CreateCookie(Integer skip[], double yaxis) {
    	for (int i = 0; i < 23; i++) {
            if (!Arrays.asList(skip).contains(i)) {
                Cookie cookie = new Cookie(((2*i) + 2.5) * BarObstacle.THICKNESS, yaxis * BarObstacle.THICKNESS);
                this.cookieSet.add(cookie);
                root.getChildren().add(cookie);
            }
        }
    }
    
    /**
     * Draw the board of pacman.
     * Receive the difficulty from setting and invoke corresponding method.
     * Three kings of mazes are preloaded in the program.
     */
    public void drawBoard() {
    	switch(GameManager.difficulty) {
    		case 1:
    			drawEasyBoard();
    			break;
    		case 2:
    			drawMediumBoard();
    			break;
    		case 3:
    			drawDifficultBoard();
    			break;
    	}
    }
    
    /**
     * Draws the easy board of the game with the cookies and the Pacman
     */
    public void drawEasyBoard() {
    	this.maze.CreateEasyMaze(root);
    	CreateCookie(new Integer[]{}, 2.5);
    	CreateCookie(new Integer[]{1, 2, 3, 7, 8, 9, 10, 11, 12, 13, 14, 15, 19, 20, 21}, 4.5);
    	CreateCookie(new Integer[]{1, 21}, 6.5);
    	CreateCookie(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 8.5);
    	CreateCookie(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 10.5);
    	CreateCookie(new Integer[]{ 7, 8, 9, 10, 11, 12, 13, 14, 15}, 12.5);
    	CreateCookie(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 14.5);
    	CreateCookie(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 16.5);
    	CreateCookie(new Integer[]{1, 21}, 18.5);
    	CreateCookie(new Integer[]{1, 2, 3, 7, 8, 9, 10, 11, 12, 13, 14, 15, 19, 20, 21}, 20.5);
    	CreateCookie(new Integer[]{}, 22.5);
    	root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }
    
    /**
     * Draws the medium board of the game with the cookies and the Pacman
     */
    public void drawMediumBoard() {
        this.maze.CreateMediumMaze(root);
        CreateCookie(new Integer[]{5, 17}, 2.5);
        CreateCookie(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21}, 4.5);
        CreateCookie(new Integer[]{1, 21}, 6.5);
        CreateCookie(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 8.5);
        CreateCookie(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 10.5);
        CreateCookie(new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19}, 12.5);
        CreateCookie(new Integer[]{1, 7, 8, 9, 10, 11, 12, 13, 14, 15, 21}, 14.5);
        CreateCookie(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 16.5);
        CreateCookie(new Integer[]{1, 21}, 18.5);
        CreateCookie(new Integer[]{1, 2, 3, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 20, 21}, 20.5);
        CreateCookie(new Integer[]{5, 17}, 22.5);
        root.getChildren().add(this.pacman);
        this.generateGhosts();
        root.getChildren().addAll(this.ghosts);
        this.scoreBoard = new Score(root);
    }
    
    /**
     * Draws the difficult board of the game with the cookies and the Pacman
     */
    public void drawDifficultBoard() {
    	 this.maze.CreateDifficultMaze(root);
    	 CreateCookie(new Integer[]{}, 2.5);
    	 CreateCookie(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21}, 4.5);
    	 CreateCookie(new Integer[]{1, 21}, 6.5);
    	 CreateCookie(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 8.5);
    	 CreateCookie(new Integer[]{1, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 19, 21}, 10.5);
    	 CreateCookie(new Integer[]{3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19}, 12.5);
    	 CreateCookie(new Integer[]{1, 3, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 21}, 14.5);
    	 CreateCookie(new Integer[]{1, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 21}, 16.5);
    	 CreateCookie(new Integer[]{1, 21}, 18.5);
    	 CreateCookie(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 21}, 20.5);
    	 CreateCookie(new Integer[]{}, 22.5);
         root.getChildren().add(this.pacman);
         this.generateGhosts();
         root.getChildren().addAll(this.ghosts);
         this.scoreBoard = new Score(root);
    }
    
    /**
     * Generates the ghosts for the pacman!
     * The ghost number is corresponding to the setting which can be chosen from 3 -5.
     */
    public void generateGhosts() {
    	GhostFactory gf = new GhostFactory();
    	Ghost gst1 = gf.createGhost("ghost1", this);
    	this.ghosts.add(gst1);
    	Ghost gst2 = gf.createGhost("ghost2", this);
    	this.ghosts.add(gst2);
    	Ghost gst3 = gf.createGhost("ghost3", this);
    	this.ghosts.add(gst3);
        if(ghostNum >= 4) {
        Ghost gst4 = gf.createGhost("ghost4", this);
        this.ghosts.add(gst4);
        }
        if(ghostNum == 5) {
        Ghost gst5 = gf.createGhost("ghost5", this);
        this.ghosts.add(gst5);
        }
    }

    /**
     * Moves the pacman
     * @param event - keyboard input
     */
    public void movePacman(KeyEvent event) {
    	if(!gameEnded) {
	        for (Ghost ghost : this.ghosts) {
	            ghost.run();
	        }
	        switch(event.getCode()) {
	            case RIGHT:
	            	this.rightPacmanAnimation.start();
	                break;
	            case LEFT:
	            	this.leftPacmanAnimation.start();
	                break;
	            case UP:
	                this.upPacmanAnimation.start();
	                break;
	            case DOWN:
	                this.downPacmanAnimation.start();
	                break;
	        }
    	}
    }

    /**
     * Stops the pacman
     * @param event - keyboard input
     */
    public void stopPacman(KeyEvent event) {
        switch(event.getCode()) {
            case RIGHT:
                this.rightPacmanAnimation.stop();
                break;
            case LEFT:
                this.leftPacmanAnimation.stop();
                break;
            case UP:
                this.upPacmanAnimation.stop();
                break;
            case DOWN:
                this.downPacmanAnimation.stop();
                break;
        }
    }

    /**
     * Creates an animation of the movement.
     * The direction of pacman's mouth will toward the move direction.
     * Pacman will stop if there is an obstacle.
     * @param direction - left or right or up or down
     * @return new AnimationTimer()
     */
    private AnimationTimer createAnimation(String direction) {
        double step = 5;
        return new AnimationTimer()
        {
            public void handle(long currentNanoTime) {
	            switch (direction) {
	                case "left":
	                	if(pacman.getCenterX() < 0 && pacman.getCenterY() >= 11* BarObstacle.THICKNESS && pacman.getCenterY() <= 15* BarObstacle.THICKNESS) {
	                		pacman.setCenterX(1223.5);
	                	}
	                	pacman.setRotate(180);
	                    if (!maze.isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
	                        pacman.setCenterX(pacman.getCenterX() - step);
	                        checkCookieCoalition(pacman, "x");
	                        checkGhostCoalition();
	                    }
	                    break;
	                case "right":
	                	if(pacman.getCenterX() > 1225 && pacman.getCenterY() >= 11* BarObstacle.THICKNESS && pacman.getCenterY() <= 15* BarObstacle.THICKNESS) {
	                		pacman.setCenterX(2.5);
	                	}
	                	pacman.setRotate(0);
	                    if (!maze.isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
	                        pacman.setCenterX(pacman.getCenterX() + step);
	                        checkCookieCoalition(pacman, "x");
	                        checkGhostCoalition();
	                    }
	                    break;
	                case "up":
	                	pacman.setRotate(270);
	                    if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
	                        pacman.setCenterY(pacman.getCenterY() - step);
	                        checkCookieCoalition(pacman, "y");
	                        checkGhostCoalition();
	                    }
	                    break;
	                case "down":
	                	pacman.setRotate(90);
	                   if (!maze.isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
	                       pacman.setCenterY(pacman.getCenterY() + step);
	                       checkCookieCoalition(pacman, "y");
	                       checkGhostCoalition();
	                   }
	                   break;
	            }
            }
        };
    }

    /**
     * Set the layout of pacman.
     * @param pacLay - the layout of pacman
     */
	private void setPacmanLayout(PacmanLayout pacLay) {
		pacLay.pacmanCenterY = pacman.getCenterY();
		pacLay.pacmanCenterX = pacman.getCenterX();
		pacLay.pacmanLeftEdge = pacLay.pacmanCenterX - pacman.getRadius();
		pacLay.pacmanRightEdge = pacLay.pacmanCenterX + pacman.getRadius();
		pacLay.pacmanTopEdge = pacLay.pacmanCenterY - pacman.getRadius();
		pacLay.pacmanBottomEdge = pacLay.pacmanCenterY + pacman.getRadius();
	}
	
	/**
     * Set the layout of cookie.
     * @param cokLay - the layout of cookie
     * @param cookie - cookie object
     */
	private void setCookieLayout(CookieLayout cokLay, Cookie cookie) {
		cokLay.cookieCenterX = cookie.getCenterX();
		cokLay.cookieCenterY = cookie.getCenterY();
		cokLay.cookieLeftEdge = cokLay.cookieCenterX - cookie.getRadius();
		cokLay.cookieRightEdge = cokLay.cookieCenterX + cookie.getRadius();
		cokLay.cookieTopEdge = cokLay.cookieCenterY - cookie.getRadius();
		cokLay.cookieBottomEdge = cokLay.cookieCenterY + cookie.getRadius();
	}
    	
    /**
     * Checks if the Pacman touches cookies.
     * If all the cookies are eaten, the game will end with winning notification and show the score page.
     * @param pacman - pacman object
     * @param axis - axis for pacman
     */
    private void checkCookieCoalition(Pacman pacman, String axis) {
    	PacmanLayout pacLay = new PacmanLayout();
    	setPacmanLayout(pacLay);
        for (Cookie cookie:cookieSet) {
        	CookieLayout cokLay = new CookieLayout();
        	setCookieLayout(cokLay, cookie);
            if (axis.equals("x")) {
                // pacman goes right
                if ((cokLay.cookieCenterY >= pacLay.pacmanTopEdge && cokLay.cookieCenterY <= pacLay.pacmanBottomEdge) && (pacLay.pacmanRightEdge >= cokLay.cookieLeftEdge && pacLay.pacmanRightEdge <= cokLay.cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes left
                if ((cokLay.cookieCenterY >= pacLay.pacmanTopEdge && cokLay.cookieCenterY <= pacLay.pacmanBottomEdge) && (pacLay.pacmanLeftEdge >= cokLay.cookieLeftEdge && pacLay.pacmanLeftEdge <= cokLay.cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            } else {
                // pacman goes up
                if ((cokLay.cookieCenterX >= pacLay.pacmanLeftEdge && cokLay.cookieCenterX <= pacLay.pacmanRightEdge) && (pacLay.pacmanBottomEdge >= cokLay.cookieTopEdge && pacLay.pacmanBottomEdge <= cokLay.cookieBottomEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes down
                if ((cokLay.cookieCenterX >= pacLay.pacmanLeftEdge && cokLay.cookieCenterX <= pacLay.pacmanRightEdge) && (pacLay.pacmanTopEdge <= cokLay.cookieBottomEdge && pacLay.pacmanTopEdge >= cokLay.cookieTopEdge)) {
                    if (cookie.isVisible()) {
                        this.score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            }
            switch(GameManager.difficulty) {
				case 1:
					this.scoreBoard.score.setText("Score: " + this.score + " X1.0");
					break;
				case 2:
					this.scoreBoard.score.setText("Score: " + this.score + " X1.2");
					break;
				case 3:
					this.scoreBoard.score.setText("Score: " + this.score + " X1.5");
					break;
            }
            WinEnding();
        }
    }
    
    /**
     * Terminate when all the cookies are eaten.
     */
    private void WinEnding() {
    	if (this.cookiesEaten == this.cookieSet.size()) {
        	if(!this.gameEnded){
        		root.getChildren().remove(view3);
            	root.getChildren().remove(view2);
            	root.getChildren().remove(view);
            	switch(GameManager.difficulty) {
        		case 1:
        			this.addFile(String.valueOf(this.score * 1.0), "./src/pacman/Score.csv");
        			break;
        		case 2:
        			this.addFile(String.valueOf(this.score * 1.2), "./src/pacman/Score.csv");
        			break;
        		case 3:
        			this.addFile(String.valueOf(this.score * 1.5), "./src/pacman/Score.csv");
        			break;
            	}
				try {
					this.endGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
                javafx.scene.text.Text endGame = new javafx.scene.text.Text("You Win!! press ESC to restart");
                endGame.setX(BarObstacle.THICKNESS * 3);
                endGame.setY(BarObstacle.THICKNESS * 28);
                endGame.setFont(Font.font("Arial", 40));
                endGame.setFill(Color.ROYALBLUE);
                root.getChildren().add(endGame);
        	}
        }
    }

    /**
     * Checks if pacman is touching a ghost.
     * Check the position of the pacman and ghosts.
     * If pacman touches a ghost, invoke lifeLost() to minus a life.
     */
    public void checkGhostCoalition() {
    	PacmanLayout pacLay = new PacmanLayout();
    	setPacmanLayout(pacLay);
        for (Ghost ghost : ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            double ghostTopEdge = ghost.getY();
            double ghostBottomEdge = ghost.getY() + ghost.getHeight();
            if ((pacLay.pacmanLeftEdge <= ghostRightEdge && pacLay.pacmanLeftEdge >= ghostLeftEdge) || (pacLay.pacmanRightEdge >= ghostLeftEdge && pacLay.pacmanRightEdge <= ghostRightEdge)) {
                if ((pacLay.pacmanTopEdge <= ghostBottomEdge && pacLay.pacmanTopEdge >= ghostTopEdge) || (pacLay.pacmanBottomEdge >= ghostTopEdge && pacLay.pacmanBottomEdge <= ghostBottomEdge)) {
                    lifeLost();
                }
            }
        }
    }
}
