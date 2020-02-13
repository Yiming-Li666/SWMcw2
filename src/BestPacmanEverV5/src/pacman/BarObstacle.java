package pacman;



import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BarObstacle extends Rectangle {
	public static Paint color = Color.CADETBLUE;
    public static double THICKNESS = 25;
    
    /**
     *
     * @param x coordinate - x-axis
     * @param y coordinate - y-axis
     * @param orientation - horizontal or vertical
     * @param length - the length of the bar (1 == 100px)
     */
    public BarObstacle(double x, double y, String orientation, double length) {
        this.setX(x);
        this.setY(y);
        if (orientation.equals("horizontal")) {
            this.setHeight(BarObstacle.THICKNESS);
            this.setWidth(length * BarObstacle.THICKNESS);
        } else {
            this.setHeight(length * BarObstacle.THICKNESS);
            this.setWidth(BarObstacle.THICKNESS);
        }
        // set the color of the obstacle
        this.setFill(color);
        this.setStrokeWidth(3);
    }
}
