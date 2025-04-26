
/**
 * Write a description of class Digits here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;


public class Digits
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Digits
     */
    public Digits()
    {
        // initialise instance variables
        printDigits();
    }

    public void printDigits(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a 2 digit number: ");
        String number = scanner.nextLine();  
        //keeps prompting the user until they enter a 2 digit number
        while(number.length() != 2){
            scanner = new Scanner(System.in);
            System.out.println("You didn't enter a 2 digit number, try again!");
            System.out.println("Enter a 2 digit number: ");
            number = scanner.nextLine(); 
        }
        //uses substring to get the ones place and tens place
        System.out.println("The ones place digit is " + number.substring(1,2) + ", and tens place digit is " + number.substring(0,1));
        
        scanner.close();
    }
}
