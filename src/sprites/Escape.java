package sprites;
import java.awt.*;
import java.util.Random;

/**
 * Escape object will get 5 points for every hit and will
 * set up to new position and the shape of that is a rectangle
 *
 * @author YuvalSaadati
 */
public class Escape implements Sprite {
    private int x;
    private int y;
    private int width;
    private int height;
    private Score score;


    /**
     * constructor .
     *
     */
    public Escape(int x, int y, int width, int height, Score score) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = score;
    }

    /**
     * Draw chase object on the screen
     *
     * @param surface of the game.
     */
    @Override
    public void drawOn(GraphicsCommands.DrawSurface surface) {
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
        int newX = x;
        int newY = y;
        int changeVal = 7;
        if (this.x < mouseX) {
            newX -= changeVal;
        } else {
            newX += changeVal;
        }
        if (this.y < mouseY) {
            newY -= changeVal;
        } else {
            newY += changeVal;
        }
        // checking if the mouse is hitting the object
        if (mouseX <= this.width + newX && mouseX >= newX
                && mouseY <= newY + this.height && mouseY >= newY) {

            this.hitTarget();
        }
        // check object colliding in the game framework
        if (newX <= 1) {
            newX = 1;
        } else if (newX + this.width > widthScreen) {
            newX = widthScreen - (this.width + 7);
        }
        if (newY <= 20) {
            newY = 20;
        } else if (newY + this.height > heightScreen ) {
            newY = heightScreen - (this.height + 30);
        }
        this.y = newY;
        this.x = newX;
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
        int upperboundX = widthScreen - (int) this.width;
        int upperboundY = heightScreen - (int) this.height;
        this.x = rand.nextInt(upperboundX);
        this.y = rand.nextInt(upperboundY);
        this.score.increaseCounter(5);
    }
}