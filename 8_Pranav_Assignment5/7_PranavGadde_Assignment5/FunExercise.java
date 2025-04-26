
/**
 * Write a description of class FunExercise here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FunExercise
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class FunExercise
     */
    public FunExercise()
    {
        // initialise instance variables
        x = 0;
    }

    public void Lestweforget(){
    //prints the first few lines with the stars
    for(var i = 0; i < 5; i++){
        for(var a = 0; a < 6; a++){
            System.out.print("* ");    
        }
        for(var b = 0; b < 35; b++){
            System.out.print("=");    
        }
        if(i < 4){
           System.out.println("");
           System.out.print(" ");     
           for(var c = 0; c < 6; c++){
               System.out.print("* ");    
           } 
           System.out.println("");
        }
    }
    //prints the remaining stars at the bottom of the flag
    for(var i = 0; i < 3; i++){
        System.out.println("");
        System.out.println("");
        for(var k = 0; k < 47;k++){
            System.out.print("=");
        }
    }

    }
    
    public void conversion(String tempType, int temp){
        if(tempType == "f"){
            int newTemp = (temp-32) * (5/9);
            System.out.println(temp + " degrees Fahrenheit is " + newTemp + " degrees Celsius");
        }else if(tempType == "c"){
            int newTemp = (temp*9/5 ) + 32;
            System.out.println(temp + " degrees Celsius is " + newTemp + " degrees Fahrenheit");
        }
        
    }
}
