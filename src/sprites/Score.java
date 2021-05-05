package sprites;
import java.awt.*;
import java.util.TimerTask;
import GraphicsCommands.DrawSurface;

/**
 * Indicate the score of the player
 *
 * @author YuvalSaadati
 */
public class Score extends TimerTask implements Sprite   {
    private int score;
    /**
     * Constructor.
     * @param score the current score of the player.
     */
    public Score(int score) {
        this.score = score;
    }


    /**
     * @param number increasing score by number
     * live in the game.
     */
    public void increaseCounter(int number) {
        this.score += number;
    }


    /**
     * Drawing the score in the corner of the screen
     * @param d the surface of the game
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE.darker());
        d.fillRectangle(0, 0, 100, 20);
        d.setColor(Color.BLACK);
        d.drawText(20, 13, "Score: " + this.score, 12);
    }


    /**
     * Do nothing.
     */
    public void timePassed() {}


    /**
     * Do nothing.
     */
    @Override
    public void hitTarget() {}


    /**
     * Increasing the score every second by 1.
     */
    @Override
    public void run() {
        this.score++;
    }
}
