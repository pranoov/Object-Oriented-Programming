
/**
 * This project simulates a race between a users bike and the computers bike. The user can decide what terrain 
 * to race on and then the race starts. Both are incremented random amounts, but throughout the race
 * the user is given scenarios which they can accept to take, which can possibly hurt or help them in the race. 
 * After both have reached the end, the winner is determined and results are shown. The user can keep playing races, and when
 * they finish the total stats will be printed. 
 *
 * @Pranav Gadde
 * Quarter Project (OOP) 2024
 */
import javax.swing.*; 
import java.util.Random; 

public class Game
{
    Canvas c; 
    //learned about JFrame and Graphics 2D from youtube video: https://www.youtube.com/watch?v=KcEvHq8Pqs0
    JFrame frame = new JFrame();
    int bikeX = 0;
    int compX = 0;
    int progress = 0;
    int bikeCycles = 0;
    int compCycles = 0;
    int bikeWins = 0;
    int compWins = 0;
    int totalRaces = 0;
    int length = 1500;
    int questionCount = 0;
    boolean gameOn = false;
    
    String[] questions = {"You’ve found a risky shortcut. Do you want to take it?","An obstacle lays ahead. Do you want to slow down to avoid it?","Would you like a tire upgrade and install new tires?","A dirt path shortcut is available, but it’s slippery due to rain. Do you want to try it?","Your engine is overheating. Do you want to reduce speed to cool it down?","A fallen tree is ahead! Do you want to slow down?","You spot an energy drink on the ground. Do you want it?"};
    String[][] answers = {{"The shortcut gets you there faster, good choice!","The shortcut was wrong and loses you time, better luck next time."},{"Slowing down helped you avoid the rocks, good job!","Slowing down took to much of your time, choose wiser."},{"The new tires are much better, good choice!", "The tires are the same and you lost crucial time waiting, choose wisely next time."},{"Although it was slippery, it bought you time, great job!","You fell of your bike because it was too slippery, better luck next time."},{"Reducing your speed helped cool the engine down, and return it to normal, good job!","You wasted too much time cooling down, choose wiser next time."},{"Stopping for the tree helped you to pass safely, good choice!", "You spent a lot of time slowing down, and still tripped over the tree, choose wisely."},{"The energy drink recharges you and you feel strong again, awesome choice!", "The drink was contaminated and slows you down, be careful."}};
    
    public static void main(String[] args){
        Game g = new Game();
    }

    /**
     * Sets up a new canvas and asks the user if they want to play the game. If they do it calls on the actual 
     * game method to show the game. It also draws a welcome screen in java canvas to the user. 
     */
    public Game()
    {
        c = Canvas.getCanvas();
        c.setVisible(true);
        c.draw();
        c.wait(1000);
        //learned about JOptionPane: https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/JOptionPane.html
        int choice = JOptionPane.showConfirmDialog(null,"Would you like play the game? (y/n)", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION); 
        if(choice == JOptionPane.YES_OPTION){
            game();
        }
        
    }
    
    /**
     * The main method of the actual game. It starts with prompting the user to enter what terrain they
     * want to race on. After it will start a while loop which will keep playing games until the user decides
     * to stop. Inside the while loop it starts a race between the user bike and computer bike. It increments 
     * both randomely, and often prompts the user with a question which can help or hurt them. After both have reached
     * their goal, it will tell the user the results and ask if they want to play again or end the game. 
     */
    public void game(){
        c.wait(100);
        
        c.chooseTerrain();
        int terrain = JOptionPane.showConfirmDialog(null, "Which terrain would you like (1 = Yes, 2 = No)?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION); 
        if(terrain == JOptionPane.YES_OPTION){ 
            c.setBackground("sunny-background.png");
        }else if(terrain == JOptionPane.NO_OPTION){
            c.setBackground("dark-background.png");
        }
        
        gameOn = true;
        while(gameOn){
            totalRaces++;
            while(bikeX < length || compX < length){
                move();
                if(bikeX > 400 * questionCount + 100){
                    getQuestions();
                }
                
                if(bikeX < length){
                    bikeCycles++; 
                }
                if(compX < length){
                    compCycles++;               
                }
                c.drawGame(bikeX,compX,progress);
                c.wait(50);
            }
            if(bikeCycles > compCycles){
                JOptionPane.showMessageDialog(null,"You Lost :( Your bike had " + bikeCycles + " cycles the computer had " + compCycles + " cycles.","Bike Racing Game",JOptionPane.INFORMATION_MESSAGE);     
                compWins++;
            }else if(bikeCycles < compCycles){
                JOptionPane.showMessageDialog(null,"You Won :) Your bike had " + bikeCycles + " cycles the computer had " + compCycles + " cycles.","Bike Racing Game",JOptionPane.INFORMATION_MESSAGE);   
                bikeWins++;
            }else if(bikeCycles == compCycles){
                JOptionPane.showMessageDialog(null,"You Tied. Your bike had " + bikeCycles + " cycles the computer had " + compCycles + " cycles.","Bike Racing Game",JOptionPane.INFORMATION_MESSAGE);   
                compWins++;
                bikeWins++;
            }
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION); 
            if(choice == JOptionPane.YES_OPTION){
                reset();
            }else{
                gameOn = false;
            }
        }
        
        c.gameEnd(totalRaces, bikeWins, compWins);
    }
    
    /** 
     * gets random questions for the user randomly from the array. If they accept the scenario it will 
     * randomly decide if it helps or hurts the users distance and tells them an appropriate response back.
     * This advantage is also given to the computer bike, where it randomly can be boosted up or down to simulate
     * what is given to the user. 
     */
    public void getQuestions(){
        Random num = new Random();
        int index = num.nextInt(0,questions.length);
        int choice = JOptionPane.showConfirmDialog(null, questions[index], "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION); 
        if(choice == JOptionPane.YES_OPTION){
            int choice2 = num.nextInt(1,3);
            if(choice2 == 1){
                JOptionPane.showMessageDialog(null,answers[index][choice2 - 1],"Bike Racing Game",JOptionPane.INFORMATION_MESSAGE);
                bikeX += num.nextInt(100,300);
            }
            if(choice2 == 2){
                JOptionPane.showMessageDialog(null,answers[index][choice2 - 1],"Bike Racing Game",JOptionPane.INFORMATION_MESSAGE);
                bikeX -= num.nextInt(50,100);
            }
        }
        int compChoice = num.nextInt(1,3);
        if(compChoice == 1){
            int compChoice2 = num.nextInt(1,3);
            if(compChoice2 == 1){
                compX += num.nextInt(100,300);
            }else{
                compX -= num.nextInt(50,100);
            }
        }
        questionCount++;
        
    }
    
    /**
     * Resets all the counters so the user can play another game
     */
    public void reset(){
        bikeX = 0;
        compX = 0;
        bikeCycles = 0;
        compCycles = 0;
        questionCount = 0;
    }
    
    /**
     * Moves all of the fields like the bikeX and the compX a random distance in order to simulate the race.
     * It also calculates the progress the computer bike has made based on distance. 
     */
    public void move(){
        
        if(bikeX < length){
            bikeX += getRandom();
        }
        
        if(compX < length){
            compX += getRandom();
        }
        
        if(compX/15 > 100){
            progress = 100;
        }else{
            progress = compX/15;
        }
    }
    
    /**
     * Method used to get a random number for incrementing the bikes everytime
     */
    public int getRandom(){
        Random randy = new Random(); 
        return randy.nextInt(5,15);
    }
}
