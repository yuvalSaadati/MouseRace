package game;
import GraphicsCommands.GUI;
import sprites.Ball;
import sprites.*;
import java.awt.*;
import java.util.Timer;


/**
 * Class Game is running from main and initialize and run all the objects.
 *
 * @author YuvalSaadati
 */
public class Game {
    Chase squareChase ; // creating chase object
    Escape rectangleEscape1 ; // creating escape object
    Escape rectangleEscape2 ;
    Ball ball1;
    Ball ball2;
    private SpriteCollection sprites;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)screenSize.getWidth();
    int screenHeight = (int)screenSize.getHeight();
    private GUI gui = new GUI("Mouse race", screenWidth, screenHeight, this);
    private GraphicsCommands.DrawSurface drawSurface = this.gui.getDrawSurface();
    private Score score;
    // timer for increasing the score by 1 every second
    Timer timer = new Timer();


    /**
     *  Constructor
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.score = new Score(0);
        this.timer.schedule(this.score, 0, 1000);
        this.squareChase = new Chase(5, 400, 250, 25, 25,  this.gui);
        this.rectangleEscape1 = new Escape(7, 200, 100, 100, 50, this.score);
        this.rectangleEscape2 = new Escape(7, 800, 400, 100, 50, this.score);
        this.ball1 = new Ball(5, 500, 300, 20, this.gui);
        this.ball2 = new Ball(5, 400, 300, 20, this.gui);
    }


    /**
     * Initialize a new game: create all the objects that will be show on the screen
     */
    public void initialize() {
        this.sprites.addSprite(this.squareChase);
        this.sprites.addSprite(this.rectangleEscape1);
        this.sprites.addSprite(this.rectangleEscape2);
        this.sprites.addSprite(this.score);
        this.sprites.addSprite(this.ball1);
        this.sprites.addSprite(this.ball2);
        this.sprites.drawAllOn(drawSurface);
        this.gui.show(drawSurface);
    }


    /**
     * Drawing all the objects and play
     */
    public void playOneTurn() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            drawSurface = this.gui.getDrawSurface();
            long startTime = System.currentTimeMillis(); // timing
            // drawing all the objects
            this.sprites.drawAllOn(drawSurface);
            // showing all the objects on the screen
            this.gui.show(drawSurface);
            // activate al the sprite objects
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                try {
                    Thread.sleep(milliSecondLeftToSleep);
                } catch (InterruptedException var4) {
                    var4.printStackTrace();
                }
            }

        }
    }


    /**
     * The method active the method playOneTurn until some hit has happened
     */
    public void run() {
        while(true){
            playOneTurn();
            }
        }
    }
