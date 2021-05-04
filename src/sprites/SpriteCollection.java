package sprites;
import java.util.ArrayList;
import java.util.List;
import GUICommands.DrawSurface;

/**
 * SpriteCollection hold a collection of sprites.
 *
 * @author YuvalSaadati
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }


    /**
     * Add sprite to the sprite collection.
     * @param s the sprite that will be added
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }


    /**
     * Call timePassed() in all the sprite objects.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copyList = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : copyList) {
            s.timePassed();
        }
    }


    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d the surface of the game
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
