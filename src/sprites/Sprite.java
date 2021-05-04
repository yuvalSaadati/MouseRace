package sprites;
import GraphicsCommands.DrawSurface;

/**
 * Sprite interface will be implemented by the objects.
 *
 * @author YuvalSaadati
 */
public interface Sprite {
    /**
     * Drawing the sprite to the screen.
     *
     * @param d the surface of the game
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     *
     */
    void timePassed();

    /**
     * when object hit his own target.
     *
     */
    void hitTarget();

}
