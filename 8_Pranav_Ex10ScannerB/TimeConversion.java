
/**
 * Write a description of class TimeConversion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class TimeConversion
{
    /**
     * Constructor for objects of class TimeConversion
     */
    public TimeConversion()
    {
        convertMinutes();       
    }
    
    public void convertMinutes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an amount of minutes: ");
        //parse int from oracle docs
        int timeGiven = Integer.parseInt(scanner.nextLine());
        int hours = 0;
        int minutes = 0;
        //checks if amount is over an hour to set correct amount of hours
        if(timeGiven >= 60){
            hours = timeGiven/60;
            minutes = timeGiven%60;
        }else{
            minutes = timeGiven;
        }
        
        //adds extra zero if minutes are under 10
        if(minutes < 10){
            System.out.println("Time is " + hours + ":0" + minutes); 
        }else{
            System.out.println("Time is " + hours + ":" + minutes);
        }
        
        scanner.close();
    }
    
    
}
