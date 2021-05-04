package sprites;
import GUICommands.GUI;
import geometric.Rectangle;
import java.awt.*;

/**
 * Chase object will be a square and the game will end when it hit the mouse
 *
 * @author YuvalSaadati
 */
public class Chase implements Sprite {
    private Rectangle square;
    private double rectangleX;
    private double rectangleY;
    private double rectangleWidth;
    private double rectangleHeight;
    private GUI gui;

    /**
     * constructor .
     *
     * @param square is the the shape of that object
     */
    public Chase(Rectangle square, GUI gui) {
        this.square = square;
        this.rectangleX = square.getUpperLeft().getX();
        this.rectangleY = square.getUpperLeft().getY();
        this.rectangleWidth = square.getWidth();
        this.rectangleHeight = square.getHeight();
        this.gui = gui;
    }
    /**
     * Draw chase object on the screen
     *
     * @param surface of the game.
     */

    @Override
    public void drawOn(GUICommands.DrawSurface surface) {
        int x = (int) this.square.getUpperLeft().getX();
        int y = (int) this.square.getUpperLeft().getY();
        int width = (int) this.square.getWidth();
        int height = (int) this.square.getHeight();
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
        double newX = this.rectangleX;
        double newY = this.rectangleY;
        // check object colliding in the game framework
        if (this.rectangleX < mouseX) {
            newX += 5;
        } else {
            newX -= 5;
        }
        if (this.rectangleY < mouseY) {
            newY += 5;
        } else {
            newY -= 5;
        }
        // checking if the mouse is hitting the object
        if (mouseX <= this.rectangleWidth + newX && mouseX >= newX
                && mouseY <= newY + this.rectangleHeight && mouseY >= newY) {
            hitTarget();
        }
        if (newX <= 1) {
            newX = 1;
        } else if (newX + this.rectangleWidth > widthScreen) {
            newX = widthScreen - (this.rectangleWidth + 7);
        }
        if (newY <= 20) {
            newY = 20;
        } else if (newY + this.rectangleHeight > heightScreen) {
            newY = heightScreen - (this.rectangleHeight + 20);
        }
        this.rectangleY = newY;
        this.rectangleX = newX;
        this.square.setRectangle(newX, newY, rectangleWidth, rectangleHeight);
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

