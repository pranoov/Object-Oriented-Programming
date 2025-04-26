import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * 
 * This Canvas class is a modified version of the Canvas class from the figures project. The basic Canvas 
 * structures are used to draw the graphics of this game. 
 */
public class Canvas
{
 
    private static Canvas canvasSingleton;

    /**
     * This makes a new canvas object of this class, and returns it and calls the setVisible method to display 
     * something initally on the canvas. Allows for the Game class to create a new canvas to use. 
     * 
     * @return canvasSingleton which contains the new Canvas object
     */
    public static Canvas getCanvas()
    {
        if(canvasSingleton == null) {
            canvasSingleton = new Canvas("Bike Racing Game", 800, 500, Color.white);
        }
        canvasSingleton.setVisible(true);
        return canvasSingleton;
    }


    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color backgroundColor;
    private Image canvasImage;
    
    //image icon from oracle docs: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/ImageIcon.html
    Image image = new ImageIcon("bike.png").getImage();
    Image progressImg = new ImageIcon("progressBike.png").getImage();
    Image backgroundImg = new ImageIcon("background.png").getImage();
    Image endImg = new ImageIcon("endBg.png").getImage();
    //learned to use font from javatpoint: https://www.javatpoint.com/java-font
    Font font = new Font("Helvetica", Font.BOLD, 40);
    boolean darkMode = false;
    Image sunnyTempImg = new ImageIcon("sunny-template.png").getImage();
    Image darkTempImg = new ImageIcon("dark-template.png").getImage();
    
    
    /**
     * The constructor of this class which actually makes a custom canvas and is provided by the figures project.
     * @param title: a title for the canvas 
     * @param width: a int for the width of the canvas
     * @param height: a int for the height of the canvas
     * @param bgColor: type Color to set for the background of the canvas
     */
    private Canvas(String title, int width, int height, Color bgColor)
    {
        frame = new JFrame();
        canvas = new CanvasPane();
        frame.setContentPane(canvas);
        frame.setTitle(title);
        frame.setLocation(30, 30);
        canvas.setPreferredSize(new Dimension(width, height));
        backgroundColor = bgColor;
        frame.pack();
    }

    /**
     * When the canvas is initally created this method is called to initialize graphics for the canvas. This allows
     * for the drawing of strings, images, shapes...
     * @param visible to set the canvas visible or not
     */
    public void setVisible(boolean visible)
    {
        if(graphic == null) {
            Dimension size = canvas.getSize();
            canvasImage = canvas.createImage(size.width, size.height);
            //graphic contains the 2D graphics of the canvas, and internal methods of this can be called to draw items
            graphic = (Graphics2D)canvasImage.getGraphics();
            graphic.setColor(backgroundColor);
            graphic.fillRect(0, 0, size.width, size.height);
            graphic.setColor(Color.black);
        }
        frame.setVisible(visible);
    }
    
    /**
     * Draws the initial screen which welcomes the user to the game
     */
    public void draw()
    {   
        graphic.setColor(Color.black);
        graphic.fillRect(0,0,800,500);
        graphic.setColor(Color.white);
        graphic.setFont(font);
        graphic.drawString("Welcome to the Game!",150,240); 
        //used to update the canvas
        canvas.repaint();
    }
    
    /**
     * Sets up the screen where the user can choose their terrain. It draws a background and the two images
     * along with 1 and 2 to indicate the users options. canvas.repaint() is also called to update this 
     * to the canvas
     */
    public void chooseTerrain(){
        graphic.setColor(Color.black);
        graphic.fillRect(0,0,800,500);
        //learned drawImage method: https://www.rgraph.net/canvas/reference/drawimage.html
        graphic.drawImage(sunnyTempImg, 150,150,200,200,null);
        graphic.drawImage(darkTempImg, 450,150,200,200,null);
        graphic.setFont(font);
        graphic.setColor(Color.white);
        graphic.drawString("1",240,420);
        graphic.drawString("2",540,420);
        canvas.repaint();
    }
    
    /**
     * Based on what the user chooses for their terrain, it sets the path of that corresponding image
     * to be the background for the game. Also it sets the variable darkMode to true, if they chose the dark
     * forest terrain to be used in future methods.
     * 
     * @params path: the path to the background image to set
     */
    public void setBackground(String path){
        backgroundImg = new ImageIcon(path).getImage();
        if(path == "dark-background.png"){
            darkMode = true;
        }
    }
    
    /**
     * Draws the majority of the actual game. It draws the bike in the center, and the background, and draws the progress
     * bar of the computer bike according to values given by the Game class. It also draws the percent the user
     * has made it. 
     * 
     * @param bikeX: How far the bike has traveled in pixels to move the background accordingly
     * @param compX: How far the computer bike has traveled in order to move the computer bike accordingly
     * @param progress: The percent progress the computer bike has made to update the progress bar
     */
    public void drawGame(int bikeX,int compX, int progress){
        //The percent the user bike has made it
        int percent = bikeX/15;
        if(percent > 100)percent = 100;
        graphic.setColor(Color.black);
        graphic.fillRect(0,0,800,500);
        
        graphic.drawImage(backgroundImg,0 - bikeX,0,2500,500,null);
        if(darkMode){
            graphic.drawImage(image,250,260,240,200,null);
        }else{
            graphic.drawImage(image,250,240,240,200,null);        
        }
        graphic.setColor(Color.gray);
        graphic.fillRect(100,450,600,20);
        graphic.setColor(Color.white);
        graphic.fillRect(100,450,progress * 6,20);
        graphic.drawImage(progressImg,progress * 6 + 80,440,50,50,null);
        graphic.setColor(Color.black);
        graphic.setFont(font);
        graphic.setColor(Color.white);
        //Draws the percent the user bike has made it
        graphic.drawString(percent + "%", 50,50);
        canvas.repaint();
    }
    
    
    /**
     * Prints out the final stats of the games including the total number of races and wins for the user bike and 
     * the computer bike. 
     * 
     * @param races: The total number of races done
     * @param bikeWins: How many times the user's bike has won
     * @param compWins: The number of time the computer bike has won 
     */
    public void gameEnd(int races,int bikeWins,int compWins){
        graphic.drawImage(endImg,0,0,800,500,null);
        graphic.setColor(Color.white);
        graphic.drawString("Your Race Summary:",50,50); 
        graphic.drawString("Total Races: " + races,50,100);
        graphic.drawString("Bike Wins: " + bikeWins,50,150);
        graphic.drawString("Computer Wins: " + compWins,50,200);
        graphic.drawString("Thank you for playing!",50,250);
        canvas.repaint();
    }
    
    
    /** 
     * From the figures project. The method is useful in pausing the code for a certain amount of time. 
     * 
     * @param milliseconds: The number of milliseconds the execution of the code should be paused for
     */
    public void wait(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        } 
        catch (Exception e)
        {
            // ignoring exception at the moment
        }
    }
    
    
    /**
     * Also from the figures project, allows for the graphics to actually been drawn on to the canvas
     */
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel
    {
        public void paint(Graphics g)
        {
            g.drawImage(canvasImage, 0, 0, null);
        }
    }


}
