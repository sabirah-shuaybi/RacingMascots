import java.awt.*;
import objectdraw.*;
import java.util.Random;

/**
 * The Mascot class is responsible for defining a blueprint for a racing mascot.
 * Along with the constructor for Mascot, this class contains a method that returns a random speed,
 * which is a characteristic of a Mascot object. The run method defines how the animation will run once
 * the start method is invoked (Start() invokes run).
 *
 * @author Sabirah Shuaybi
 * @version 11/01/16
 */
public class Mascot extends ActiveObject {

    //Holds mascot image
    private VisibleImage visibleImage;
    //Holds finish line
    private Line finishLine;
    //Reference to parent class -> RacingMohos class
    private RacingMohos racingMohos;

    //Delay time for animation in seconds
    private static final double PAUSE_TIME_SECONDS = 0.5;

    /* Mascot constructor */
    public Mascot(VisibleImage image, Line finishLine, RacingMohos racingMohos) {

        System.out.println("Entering Mascot Construction");

        //Save references into instance variables
        this.finishLine = finishLine;
        this.visibleImage = image;
        this.racingMohos = racingMohos;
    }

    /* Returns a random integer */
    private int getRrandomDistance() {

        Random r = new Random();

        //Adding 20 to set a reasonable minimum distance of travel for mascots
        //Adjusted range is now 20-90
        return r.nextInt(70)+20;
    }

    /* Responsible for defining various components of the animation such as pause time */
    public void run() {

        System.out.println("run");

        //While mascot has not yet passed finish line,
        //pause animation for designated time and move it a random distance to the left
        //(hence the -1 indicating left-ward direction)
        while(visibleImage.getX() >= finishLine.getStart().getX()) {

            pause(PAUSE_TIME_SECONDS * 1000);
            visibleImage.move(getRrandomDistance() * -1, 0);
        }

        //Define final resting place locs for mascots after completion of race
        double restingPlaceX = finishLine.getStart().getX() - visibleImage.getWidth() - 10;
        double restingPlaceY = visibleImage.getY();
        //Move mascots to this resting destination
        visibleImage.moveTo(restingPlaceX, restingPlaceY);

        //Pass running instance of mascot as a parameter to registerFinish method in parent class
        //Significance: so each instance of mascot has the ability ro 'register' if first to finish
        racingMohos.registerFinish(this);
    }

}

