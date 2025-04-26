
/**
 * Write a description of class Birthday here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class Birthday
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Birthday
     */
    public Birthday()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Birthday Game!");
        System.out.println("1. Determine your birth month (January = 1, February = 2, and so on)");
        System.out.println("2. Multiply that number by 5");
        System.out.println("3. Add 6 to that number");
        System.out.println("4. Multiply the number by 4");
        System.out.println("5. Add 9 to the number");
        System.out.println("6. Multiply that number by 5");
        System.out.println("7. Add your birth day to the number (10 if the 10th and so on).");
        System.out.println("Enter the number you got from Step 7:");
        
        //does all the operations to get day and month
        int number = Integer.parseInt(scanner.nextLine());
        number -= 165;
        int month = number/100;
        String wordMonth = "";
        int day = number%100;
        
        //I learned switch case in js, based on month number it sets the text month
        switch(month){
        case 1:
            wordMonth = "January";
            break;
        case 2: 
            wordMonth = "February"; 
            break;
        case 3: 
            wordMonth = "March";
            break;
        case 4: 
            wordMonth = "April"; 
            break;
        case 5: 
            wordMonth = "May";
            break;
        case 6: 
            wordMonth = "June";
            break;
        case 7: 
            wordMonth = "July";
            break;
        case 8: 
            wordMonth = "August";
            break;
        case 9: 
            wordMonth = "September";
            break;
        case 10: 
            wordMonth = "October";
            break;
        case 11: 
            wordMonth = "November";
            break;
        case 12:
            wordMonth = "December";
            break;  
        }
        
        //prints out the user birthday
        System.out.println("month: " + wordMonth + ", day: "+ day);
        
        scanner.close();
    }

    
}
