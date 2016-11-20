import java.awt.*;
import objectdraw.*;

/**
 * The RacingMohos Class handles all of the user mouse events and
 * contains knowledge of the DrawingCanvas.
 * The class displays the racing lines as well as the visible images of the four mascots
 * at the start of the program.
 * On a user's mouse click, this class starts the animation by invoking start().
 * @author Sabirah Shuaybi
 * @version 11/01/16
 */
public class RacingMohos extends WindowController {

    //Constants for positioning racing lines, mascot icons and text
    private static final int TOP_BUFFER_SPACE = 50;
    private static final int SIDE_BUFFER_SPACE = 40;
    private static final int WINDOW_WIDTH = 840;
    private static final int WINDOW_HEIGHT = 560;
    private static final int LANE_HEIGHT = 100;
    private static final int ICON_SIZE = 75;
    private static final int ICON_BUFFER_SPACE = 10;
    private static final int TEXT_BUFFER_SPACE = 20;

    //Instance variable for the finish line
    private Line finishLine;
    
    //To store footer text to be able to manipulate it throughout the program 
    private Text text;

    //Storing certain coordinates as instance variables
    //This is so they can be used in multiple methods for arrangement/positioning purposes
    private int beginLineX;
    private int firstLaneY;
    private int middleLaneY;
    private int lastLaneY;
    private int bottomLineY;

    //Instance variables for the four instances of Mascot
    private Mascot greenMascot;
    private Mascot blueMascot;
    private Mascot redMascot;
    private Mascot yellowMascot;

    //Instance variables for storing the loaded pictures of mascots
    //Needed across two methods: displayPicture() and constructMascots()
    private Picture greenMascotPic;
    private Picture blueMascotPic;
    private Picture redMascotPic;
    private Picture yellowMascotPic;

    //Instance variables for storing the visible images of mascots
    //Needed across two methods: displayPicture() and constructMascots()
    private VisibleImage green;
    private VisibleImage blue;
    private VisibleImage red;
    private VisibleImage yellow;

    //Instance variable for winner mascot is null when program starts
    private Mascot winner = null;

    public void begin() {

        //Resizing the canvas window to desired width and height
        resize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Invoking method that will create racing lines
        createLines();

        //Invoking method that will display the pictures of the mascots
        displayPicture();

        //Invoking method that will create and display starting text "Racing Mohos"
        displayFooterText("Racing Mohos");
    }

    //This method is responsible for creating all the lines of the runway track
    //Including lines for top and bottom and start and finish as well as lines for lanes separating the mascots
    private void createLines(){

        int START_POINT_X = WINDOW_WIDTH - SIDE_BUFFER_SPACE;
        int END_POINT_X = SIDE_BUFFER_SPACE;

        int topLineY = TOP_BUFFER_SPACE;
        Line topLine = new Line(START_POINT_X, topLineY, END_POINT_X, topLineY, canvas);

        firstLaneY = TOP_BUFFER_SPACE + LANE_HEIGHT;
        Line firstLane = new Line(START_POINT_X, firstLaneY, END_POINT_X, firstLaneY, canvas);

        middleLaneY = firstLaneY + LANE_HEIGHT;
        Line middleLane = new Line(START_POINT_X, middleLaneY, END_POINT_X, middleLaneY, canvas);

        lastLaneY = middleLaneY + LANE_HEIGHT;
        Line lastLane = new Line(START_POINT_X, lastLaneY, END_POINT_X, lastLaneY, canvas);

        bottomLineY = lastLaneY + LANE_HEIGHT;
        Line bottomLine = new Line(START_POINT_X, bottomLineY, END_POINT_X, bottomLineY, canvas);

        beginLineX = WINDOW_WIDTH - SIDE_BUFFER_SPACE - ICON_SIZE;
        Line beginLine = new Line(beginLineX, TOP_BUFFER_SPACE, beginLineX, bottomLineY, canvas);

        int finishLineX = SIDE_BUFFER_SPACE + ICON_SIZE;
        finishLine = new Line(finishLineX, TOP_BUFFER_SPACE, finishLineX, bottomLineY, canvas);
    }

    //Method that is in charge of loading mascot picture from web and transforming it into a visible image
    //displayPicture also positions the visible images in their corresponding places at the start line of race
    private void displayPicture(){

        int pictureX = beginLineX + ICON_BUFFER_SPACE;

        greenMascotPic = new Picture("http://www.mtholyoke.edu/~blerner/green_griffin.jpg");

        int greenY = TOP_BUFFER_SPACE + ICON_BUFFER_SPACE;
        green = greenMascotPic.createVisibleImage(pictureX, greenY, canvas);

        blueMascotPic = new Picture("http://www.mtholyoke.edu/~blerner/lion.png");

        int blueY = firstLaneY + ICON_BUFFER_SPACE;
        blue = blueMascotPic.createVisibleImage(pictureX, blueY, canvas);

        redMascotPic = new Picture("http://www.mtholyoke.edu/~blerner/pegasus.png");

        int redY = middleLaneY + ICON_BUFFER_SPACE;
        red = redMascotPic.createVisibleImage(pictureX, redY, canvas);

        yellowMascotPic = new Picture("http://www.mtholyoke.edu/~blerner/sphinx.png");

        int yellowY = lastLaneY + ICON_BUFFER_SPACE;
        yellow = yellowMascotPic.createVisibleImage(pictureX, yellowY, canvas);

    }

    //Responsible for creating, positioning and formatting the text display at bottom of screen
    private void displayFooterText(String textContent){

        //Constructing a text object at an arbitrary location
        text = new Text(textContent, 10, 10, canvas);

        //Print text's font and size on terminal to then manipulate font size (original text size = 13)
        System.out.println(text.getFont());
        text.setFontSize(20);

        //Defining a new color and setting text to that color
        Color darkMagenta = new Color(139, 0, 139);
        text.setColor(darkMagenta);

        //Making the text bold
        boolean bold = true;
        text.setBold(bold);

        //Centering the text object within the canvas without hard-coding
        double textLocX = (canvas.getWidth()/2) - (text.getWidth()/2);
        double textLocY = bottomLineY + TEXT_BUFFER_SPACE;
        text.moveTo(textLocX, textLocY);
    }

    //A mini-method that clears the previous text object to eventally enable a fresh text display announcing winner
    private void clearFooterText(){
        text.removeFromCanvas();
    }
    
    //Private method that constructs 4 instances of the Mascot class and assigns to corresponding inst vars
    private void constructMascots(){
        //For debugging purposes
        System.out.println("Entering constructMascots");

        //Along with visible image and finish line, "this" is also passed into the constructor
        //Passing "this" allows the Mascot class to have a reference to the parent class aka RacingMohos
        greenMascot = new Mascot(green, finishLine, this);
        blueMascot = new Mascot(blue, finishLine, this);
        redMascot = new Mascot(red, finishLine, this);
        yellowMascot = new Mascot(yellow, finishLine, this);
    }

    public void onMouseClick(Location point){

        System.out.println("Entering onMouseClick");

        //Constructing all the mascots on a user click
        constructMascots();

        //Calling start on each instance to invoke run which is in charge of activating the animation
        greenMascot.start();
        blueMascot.start();
        redMascot.start();
        yellowMascot.start();
    }

    //A method that assigns winner to the first mascot who finishes the race and "registers"
    //This method is invoked by all the mascots and only the first one is registered as the winner
    public void registerFinish(Mascot mascot) {
        
        if (winner == null) {
            winner = mascot;

            //Announcing the winning mascot
            announceWinner(mascot);
        }
    }

    //This method uses if/else conditions to check who is the winner
    //After identifying the winner, this method displays the corresponding text by passing in...
    //the appropriate string into displayFooterText()
    //Before diplaying corresponding text, however, this method first clears the existing footer text...
    //to prevent overlap of text objects
    private void announceWinner(Mascot mascot) {
        
        clearFooterText();
        
        if (winner == greenMascot) {
            displayFooterText("The green griffin has won!");
        }
        else if (winner == blueMascot) {
            displayFooterText("The blue griffin has won!");
        }
        else if (winner == redMascot) {
            displayFooterText("The red griffin has won!");
        }
        else if (winner == yellowMascot) {
            displayFooterText("The yellow griffin has won!");
        }
    }

}

