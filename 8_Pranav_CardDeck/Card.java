
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{   
    //3 instance variables to define the card
    private String color;
    private int number;
    private String shape;
    
    public Card(String newColor, int newValue, String newShape)
    {
        color = newColor;
        number = newValue;
        shape = newShape;
    }
    
    //prints out the details of the card, and takes into account face cards and ace card
    public String print(){
        if(number == 1){
            return "A " + color + " Ace of " + shape;
        }else if(number <= 10 && number != 1){
            return "A " + color + " " + number + " of " + shape;
        }else{
            String face = "";
            switch(number){
                case 11: 
                    face = "jack";
                    break;
                case 12: 
                    face = "queen";
                    break;
                case 13: 
                    face = "king";
                    break;
            }
            return "A " + color + " " + face + " of " + shape;
        }
    }
    
    //returns the number of the card
    public int getNumber(){
        return number;
    }
    
    //returns the shape of the card
    public String getShape(){
        return shape;
    }

}
