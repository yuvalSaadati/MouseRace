package sprites;
import GraphicsCommands.GUI;
import java.awt.*;

/**
 * Chase object will be a square and the game will end when it hit the mouse
 *
 * @author YuvalSaadati
 */
public class Chase implements Sprite {
    private int x;
    private int y;
    private int width;
    private int height;
    private GUI gui;
    private int velocity;

    /**
     * constructor .
     *
     */
    public Chase(int velocity, int x, int y, int width, int height, GUI gui) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gui = gui;
        this.velocity = velocity;
    }
    /**
     * Draw chase object on the screen
     *
     * @param surface of the game.
     */

    @Override
    public void drawOn(GraphicsCommands.DrawSurface surface) {
        surface.setColor(Color.BLUE);
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
        int newX = this.x;
        int newY = this.y;
        // check object colliding in the game framework
        if (this.x < mouseX) {
            newX += this.velocity;
        } else {
            newX -= this.velocity;
        }
        if (this.y < mouseY) {
            newY += this.velocity;
        } else {
            newY -= this.velocity;
        }
        // checking if the mouse is hitting the object
        if (mouseX <= this.width + newX && mouseX >= newX
                && mouseY <= newY + this.height && mouseY >= newY) {
            hitTarget();
        }
        // checking if the object is out
        if (newX <= 1) {
            newX = 1;
        } else if (newX + this.width > widthScreen) {
            newX = widthScreen - (this.width + 7);
        }
        if (newY <= 20) {
            newY = 20;
        } else if (newY + this.height > heightScreen) {
            newY = heightScreen - (this.height + 20);
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
        this.gui.close();
    }
}

