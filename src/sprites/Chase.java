package sprites;
import GUICommands.GUI;
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

    /**
     * constructor .
     *
     */
    public Chase(int x, int y, int width, int height, GUI gui) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gui = gui;
    }
    /**
     * Draw chase object on the screen
     *
     * @param surface of the game.
     */

    @Override
    public void drawOn(GUICommands.DrawSurface surface) {
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
            newX += 5;
        } else {
            newX -= 5;
        }
        if (this.y < mouseY) {
            newY += 5;
        } else {
            newY -= 5;
        }
        // checking if the mouse is hitting the object
        if (mouseX <= this.width + newX && mouseX >= newX
                && mouseY <= newY + this.height && mouseY >= newY) {
            hitTarget();
        }
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
        //this.square.setRectangle(newX, newY, rectangleWidth, rectangleHeight);
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

