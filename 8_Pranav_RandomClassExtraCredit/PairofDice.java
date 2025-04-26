
/**
 * Write a description of class PairofDice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;

public class PairofDice
{
    int count = 0;
    Random randy = new Random();
    /**
     * Constructor for objects of class PairofDice
     */
    public PairofDice()
    {
       
    }

    public void rollDie()
    {
        int num1 = randy.nextInt(1,7);
        int num2 = randy.nextInt(1,7);
        count++;
        if(num1 == 1 && num2 == 1){
            System.out.println("Congrats you got snake eyes after " + count + " rolls!");
            count = 0;
        }else{
            System.out.println("You rolled a " + num1 + " and " + num2 + ", and didn't get snake eyes.");
            System.out.println("Total times rolled is " + count + ". Please try again.");
        }

    }
}
