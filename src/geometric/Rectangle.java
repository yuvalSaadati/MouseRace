package geometric;
/**
 * This class represent a Quadrilateral
 *
 * @author YuvalSaadaty
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor to initialize the class fields.
     * @param x      position on the 'x' axes
     * @param y      position on the 'y' axes
     * @param width  of the rectangle
     * @param height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Updating rectangle files.
     *
     * @param x      position on the 'x' axes
     * @param y      position on the 'y' axes
     * @param width of the rectangle
     * @param height of the rectangle
     */
    public void setRectangle(double x, double y, double width, double height) {
        this.upperLeft.setPoint(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
