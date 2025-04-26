
/**
 * Write a description of class MyScanner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;



public class MyScanner
{
    // instance variables - replace the example below with your own
    private int x;

    
    public MyScanner()
    {
      
    }
    
    //goes through 5 integers to get the sum and average of them
    public void getSumAndAvg(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 5 numbers separated by spaces:");
        int sum = 0;
        int total = 0;
        
        //loops through all 5 of the numbers entered
        while (total < 5) {
            int number = scanner.nextInt();
            sum += number;
            total++;
        }
        //divide by 5 to find average
        double avg = sum/5.0;
        System.out.println("Sum: " + sum + ", Average: " + avg);
        scanner.close();
    }
    
    //prints the first character of string and last character
    public void printStartEndChar(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String string1 = scanner.nextLine();

        System.out.println("First character: " + string1.substring(0,1) + ", Last character: " + string1.substring(string1.length() - 1, string1.length()));
        
        scanner.close();
    }
    
    //makes all the first letters of words uppercase
    public void firstToUpper(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your string: ");
        
        String newString = scanner.nextLine();
        //split method from w3 schools to get individual words
        String[] words = newString.split(" ");
        String finalString = "";
        
        //goes through every word from array makes first letter uppercase than concactinates it to new string
        for(x = 0; x < words.length; x++){
            String tempStr = words[x];
            tempStr = tempStr.substring(0,1).toUpperCase() + tempStr.substring(1, tempStr.length());
            if(x == 0){
                finalString = finalString + tempStr;
            }else{
                finalString = finalString + " " + tempStr;
            }   
        }
        
        System.out.println(finalString); 
        
        scanner.close();
    }
    
    //compares 2 strings lexicographically
    public void compareStrings(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your string 1: ");
        String string1 = scanner.nextLine();
        scanner = new Scanner(System.in);
        System.out.println("Enter your string 2: ");
        String string2 = scanner.nextLine();
        
        
        //used compareTo from oracle docs, if value is positive the string comes after, if its negative is comes before, and 0 means they are same lexicographically
        if(string1.compareTo(string2) > 0){
            System.out.println(string2 + " comes before " + string1 + " lexicographically.");
        }else if(string1.compareTo(string2) < 0){
            System.out.println(string1 + " comes before " + string2 + " lexicographically.");
        }else{
            System.out.println(string1 + " and " + string2 + " are the same lexicographically.");
        }
        scanner.close();
    }
    
    //makes the first half of string uppercase and the second half lowercase
    public void firstUpperSecondLower(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String string = scanner.nextLine();
        //to figure out how many characters of string need to be uppercase
        int half = string.length()/2;
        //makes characters upto int half uppercase and remaining lowercase
        String newString = string.substring(0,half).toUpperCase() + string.substring(half, string.length()).toLowerCase();
        System.out.println(newString);
        
        scanner.close();
    }
    
    //makes the first and last name given backwards 
    public void backwardsName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name: ");
        String firstName = scanner.nextLine();
        scanner = new Scanner(System.in);
        System.out.println("Enter Last Name: ");
        String lastName = scanner.nextLine();
        String newString = "";
        
        //takes the last character of the string and adds to front to make it backwards
        for(x = 0; x < lastName.length(); x++){
            newString = newString + lastName.substring(lastName.length() - x - 1, lastName.length() - x);
        }
        //adds the space in the middle
        newString += " ";
        for(x = 0; x < firstName.length(); x++){
            newString = newString + firstName.substring(firstName.length() - x - 1, firstName.length() - x);
        }
        
        System.out.println("Your name backwards is: " + newString);
        
        scanner.close();
    
    
    }
    

}
