package geometric;
/**
 * A point has x value on the x-axis and  y value on the y-axis
 *
 * @author YuvalSaadati
 */

public class Point {
    private double x;
    private double y;

    /**
     * Constructor to initialize the class fields.
     *
     * @param x is the value on the x-axis
     * @param y is the value on the y-axis
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Return the x-axis position of the point
     *
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the y-axis position of the point
     *
     * @return the y values of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Updating point values.
     * @param newX is the new value of x at the point
     * @param newY is the new value of y at the point
     */
    public void setPoint(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
}
