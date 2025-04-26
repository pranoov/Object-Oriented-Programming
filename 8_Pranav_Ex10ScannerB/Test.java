
/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.util.Scanner;

public class Test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Test
     */
    public Test()
    {
        x = 0;
    }

    public void intToDigit(String string){
        String tempString = string;
        //the final string outputed
        String newString = "";
        for(x = 0; x < tempString.length(); x++){
            // got the Character.isDigit method from geeksforgeeks.org
            if(Character.isDigit(tempString.charAt(x)) && x != tempString.length() - 1 && !Character.isDigit(tempString.charAt(x+1))){
                //adds the correct amount of letters based on number
                for(int k = 0; k < Integer.parseInt(tempString.substring(x,x+1)); k++){
                    newString += tempString.charAt(x+1); 
                }
            }else{
                newString += string.charAt(x);
            }
        }
        //removes numbers within the string
        for(x = 0; x < 10; x++){
            newString = newString.replaceAll(Integer.toString(x), "");
        }
        //removes integer at the end of the string
        if(Character.isDigit(newString.charAt(newString.length()-1))){
            newString = newString.substring(0, newString.length() - 1);
        }
        System.out.println("Orginal String: " + string + "\nNew String: " + newString);
    }
}
