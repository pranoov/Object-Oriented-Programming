
/**
 * Write a description of class Numbers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;


public class Numbers
{
    // instance variables - replace the example below with your own
    private int x;
    Random randy = new Random();

    /**
     * Constructor for objects of class Numbers
     */
    public Numbers()
    {
        // initialise instance variables
        x = 0;
    }

    public int genNumbers(){
        
        int num = randy.nextInt();
        
        return num;
    }
    
    public void genRange(){
        int num = randy.nextInt(1,4);
        if(num == 1){
            System.out.println("Good morning!");
        }else if(num == 2){
            System.out.println("Good afternoon.");
        }else if(num == 3){
            System.out.println("Good evening.");
        }
    }
    
    public void getRangeModified(int min, int max){
        int num = randy.nextInt(min,max);
        System.out.println("Your random number between " + min + " and " + max + " is " + num + ".");
    }
}
