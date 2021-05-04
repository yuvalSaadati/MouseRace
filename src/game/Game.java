package game;
import GUICommands.GUI;
import sprites.Ball;
import sprites.*;
import geometric.Rectangle;
import java.awt.*;
import java.util.Timer;


/**
 * class Game is running from main.
 *
 * @author YuvalSaadati
 */
public class Game {
    Rectangle Square;
    Chase squareChase ;
    Rectangle rectangle1;
    Rectangle rectangle2;
    Escape rectangleEscape1 ;
    Escape rectangleEscape2 ;
    Ball ball1;
    Ball ball2;
    private SpriteCollection sprites;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenWidth = (int)screenSize.getWidth();
    int screenHeight = (int)screenSize.getHeight();
    private GUI gui = new GUI("Mouse race", screenWidth, screenHeight, this);
    private GUICommands.DrawSurface drawSurface = this.gui.getDrawSurface();
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
        this.Square = new Rectangle(400, 250, 25, 25);
        this.squareChase = new Chase(Square,  this.gui);
        this.rectangle1 = new Rectangle(200, 100, 75, 25);
        this.rectangleEscape1 = new Escape(rectangle1, this.score);
        this.rectangle2 = new Rectangle(400, 400, 75, 25);
        this.rectangleEscape2 = new Escape(rectangle2, this.score);
        this.ball1 = new Ball(500, 300, 20, this.gui);
        this.ball2 = new Ball(400, 300, 20, this.gui);

    }


    /**
     * @param s sprite that will be added to the sprite collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initialize a new game: create all the objects that will be show on the screen
     */
    public void initialize() {
        this.addSprite(this.squareChase);
        this.addSprite(this.rectangleEscape1);
        this.addSprite(this.rectangleEscape2);
        this.addSprite(this.score);
        this.addSprite(this.ball1);
        this.addSprite(this.ball2);

        this.sprites.drawAllOn(drawSurface);
        this.gui.show(drawSurface);
    }


    /**
     * Drawing all the objects
     */
    public void playOneTurn() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            drawSurface = this.gui.getDrawSurface();
            long startTime = System.currentTimeMillis(); // timing
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
