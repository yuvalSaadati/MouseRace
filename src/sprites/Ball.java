package sprites;
import GraphicsCommands.DrawSurface;
import GraphicsCommands.GUI;
import java.awt.*;
import java.util.Random;

/**
 * Ball object is type of random collider and the game will end when it hit the mouse
 *
 * @author YuvalSaadati
 */
public class Ball implements Sprite {
    private int r;
    private int x;
    private int y;
    private java.awt.Color color;
    private GUI gui;
    /**
     * Constructor
     *
     * @param x     value of the center of the ball by the 'x' axes.
     * @param y     value of the center of the ball by the 'y' axes.
     * @param r     is the size (radius) of the ball
     */
    public Ball(int x, int y, int r, GUI gui) {
        this.r = r;
        this.x = x;
        this.y = y;
        this.gui = gui;
    }


    /**
     * Draw ball on screen
     *
     * @param surface type DrawSurface object
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.RED);
        surface.fillCircle(this.x, this.y, this.r);
    }


    /**
     * Moving the ball to the next point
     */
    public void moveOneStep() {
        PointerInfo a = MouseInfo.getPointerInfo();
        int mouseX = (int) a.getLocation().getX();
        int mouseY = (int) a.getLocation().getY();
        Dimension size
                = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = (int) size.getWidth();
        int heightScreen = (int) size.getHeight();
        int newX = this.x;
        int newY = this.y;
        if (newX - this.r <= 1 || newX +this.r >= widthScreen
        || newY - this.r <=1 || newY + this.r >= heightScreen) {
            // the ball has collided the game framework
            Random rand = new Random();
            int upperboundX = widthScreen -  this.r;
            int upperboundY = heightScreen - this.r;
            newX = rand.nextInt(upperboundX);
            newY = rand.nextInt(upperboundY);
            this.x = newX;
            this.y = newY;
        } else if (mouseX <= this.r + newX && mouseX >= newX
                    && mouseY <= newY + this.r && mouseY >= newY) {
                // checking if the mouse is hitting the object
                this.hitTarget();
            }
        else {
            this.x = newX + 5;
            this.y = newY + 5;
        }
    }


    /**
     *
     * moveOneStep on the object
     */
    @Override
    public void timePassed() {
        moveOneStep();
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
