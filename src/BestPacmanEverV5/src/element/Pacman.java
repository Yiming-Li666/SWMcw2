package element;



import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {
	private static Pacman pacman;
	
	/**
	 * To initialize a pacman
	 * @param x - x-axis
	 * @param y - y-axis
	 * @return pacman - an initialized pacman
	 */
	public static Pacman getInstance(double x, double y){
	    if(pacman == null){
	    	pacman = new Pacman(x, y);
	    }
	    return pacman;
	}
	
	/**
	 * Constructor
	 * @param x - x-axis
	 * @param y - y-axis
	 */
	public Pacman(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        // use the image of pacman instead of circle
        Image pac = new Image("./image/pacman.png");
        this.setFill(new ImagePattern(pac));
    }
}
