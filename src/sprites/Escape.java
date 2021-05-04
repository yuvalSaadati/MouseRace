package sprites;
import geometric.Rectangle;
import java.awt.*;
import java.util.Random;

/**
 * Escape object will get 5 points for every hit and will
 * set up to new position and the shape of that is a rectangle
 *
 * @author YuvalSaadati
 */
public class Escape implements Sprite {
    private Rectangle rectangle;
    private double rectangleX;
    private double rectangleY;
    private double rectangleWidth;
    private double rectangleHeight;
    private Score score;


    /**
     * constructor .
     *
     * @param rectangle is the the shape of that object
     */
    public Escape(Rectangle rectangle, Score score) {
        this.rectangle = rectangle;
        this.rectangleX = rectangle.getUpperLeft().getX();
        this.rectangleY = rectangle.getUpperLeft().getY();
        this.rectangleWidth = rectangle.getWidth();
        this.rectangleHeight = rectangle.getHeight();
        this.score = score;
    }

    /**
     * Draw chase object on the screen
     *
     * @param surface of the game.
     */
    @Override
    public void drawOn(GUICommands.DrawSurface surface) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        surface.setColor(Color.YELLOW);
        surface.fillRectangle(x, y, width, height);
    }
    /**
     * The chase object is chasing the mouse.
     */
    public void moveOneStep() {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) size.getWidth();
        int heightScreen = (int) size.getHeight();
        PointerInfo a = MouseInfo.getPointerInfo();
        int mouseX = (int) a.getLocation().getX();
        int mouseY = (int) a.getLocation().getY();
        double newX = this.rectangleX;
        double newY = this.rectangleY;
        if (this.rectangleX < mouseX) {
            newX -= 5;
        } else {
            newX += 5;
        }
        if (this.rectangleY < mouseY) {
            newY -= 5;
        } else {
            newY += 5;
        }
        // checking if the mouse is hitting the object
        if (mouseX <= this.rectangleWidth + newX && mouseX >= newX
                && mouseY <= newY + this.rectangleHeight && mouseY >= newY) {

            this.hitTarget();
        }
        // check object colliding in the game framework
        if (newX <= 1) {
            newX = 1;
        } else if (newX + this.rectangleWidth > widthScreen) {
            newX = widthScreen - (this.rectangleWidth + 7);
        }
        if (newY <= 20) {
            newY = 20;
        } else if (newY + this.rectangleHeight > heightScreen ) {
            newY = heightScreen - (this.rectangleHeight + 30);
        }

        this.rectangleY = newY;
        this.rectangleX = newX;
        this.rectangle.setRectangle(this.rectangleX, this.rectangleY, rectangleWidth, rectangleHeight);
    }


    /**
     * Calling the next step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }


    /**
     *
     * Object hit his own target
     */
    @Override
    public void hitTarget() {
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) size.getWidth();
        int heightScreen = (int) size.getHeight();
        // create random point for the new position of that rectangle
        Random rand = new Random();
        int upperboundX = widthScreen - (int) this.rectangleWidth;
        int upperboundY = heightScreen - (int) this.rectangleHeight;
        this.rectangleX = rand.nextInt(upperboundX);
        this.rectangleY = rand.nextInt(upperboundY);
        this.score.increaseCounter(5);
    }
}