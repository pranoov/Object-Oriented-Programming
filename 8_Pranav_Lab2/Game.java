
/**
 * Write a description of class Main here.
 *
 * @Pranav Gadde (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner; 
import java.util.Random;

public class Game
{   
    //the correct randomNum the user needs to guess
    private int randomNumber; 
    //amount of attempts made by user
    private int guessAmount;
    //boolean whether or not the user guessed the number
    private boolean numGuessed;

    public Game()
    {   
        //uses getRandom method to get random number between 1 and 50 inclusive
        randomNumber = getRandomNum();
        guessAmount = 0;
        numGuessed = false;
        playGame();
    }
    
    public void playGame(){
        System.out.println("Welcome to my Random Number Game!");
        System.out.println("\nGuess a random number between 1 and 50:");
        Scanner scanner = new Scanner(System.in);
        //runs until the user guesses the number indicated by the boolean numGuessed
        while(!numGuessed){
            //Checks if the user had entered an integer
            if(scanner.hasNextInt()){
                int userNum = scanner.nextInt();
                //Checks whether if the users number is too high or low or right
                if(userNum < randomNumber){
                    System.out.println("\nToo Low! Try Higher."); 
                }else if(userNum > randomNumber){
                    System.out.println("\nToo High! Try Lower.");   
                }else if(userNum == randomNumber){
                    //Sets boolean numGuessed to true to end while loop
                    numGuessed = true; 
                }
                guessAmount++;
            }else{
                //Tells the user to enter and integer between 1 and 50 if they didn't
                System.out.println("\nPlease enter an integer between 1 and 50!");
                scanner = new Scanner(System.in);
            }    
        }
        //Tells the user they got the number and how many attempts it took
        System.out.print("\nCongrats! The number was " + randomNumber + " and you guessed it in " + guessAmount + " attempts.");
        scanner.close();
    }

    public int getRandomNum(){
        Random randy = new Random();
        //Generates a random number between 1 and 50 inclusive
        int num = randy.nextInt(1,51); 
        
        return num;
    }
}
