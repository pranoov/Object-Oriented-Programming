
/**
 * Write a description of class Deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Deck
{
    // Used https://medium.com/@zachlandis91/arrays-and-collections-in-java-bb1bfbe38a7d to learn about flexible collection types
    private List<Card> deck = new ArrayList<>();

    public Deck()
    {
        setup();
    }
    
    //randomly places cards from the current deck into a new deck until all are used, to simulate shuffling
    public void shuffle(){
        int length = deck.size();
        Random randy = new Random();
        //temporary deck to store the shuffled cards
        List<Card> tempDeck = new ArrayList<>();
        for(int i = 0; i < length; i++){
            int randomNum = randy.nextInt(deck.size());
            tempDeck.add(deck.get(randomNum));
            deck.remove(randomNum);
        }
        
        deck = tempDeck;
    }
    
    //Sorts all the cards using bubble sort from lowest to highest shape and lowest to highest number
    public void sort(){
        List<Card> tempDeck = new ArrayList<>();
        int size = deck.size();
        for(int k = 0; k < size; k++){
            int low = 0;
            for(int i = 1; i < deck.size(); i++){
                Card tempCard = deck.get(i);
                //the number and shape of the previous lowest card to compare with
                int lowNum = deck.get(low).getNumber();
                int lowShape = getValue(deck.get(low).getShape());
                //compares the current card and the lowest card to determine which is lower
                if(getValue(tempCard.getShape()) < lowShape || getValue(tempCard.getShape()) == lowShape && tempCard.getNumber() < lowNum){ 
                    low = i;
                }
            }
            tempDeck.add(deck.get(low));
            deck.remove(low);
        }
        deck = tempDeck;
    }
    
    //assigns numerical values to the shapes, in order to compare then in the sort method
    public int getValue(String shape){
        switch(shape){
            case "clubs":
                return 1;
            case "hearts":
                return 2;
            case "diamonds":
                return 3;
            case "spades":
                return 4;
        }
        return 0;
    }
    
    //returns the size of the deck
    public int getSize(){
        return deck.size();
    }
    
    //prints out the number of the cards the user wants to draw
    public void getCards(int num){
        for(int i = 0; i < num; i++){
            System.out.println(deck.get(0).print());
            deck.remove(0);
        }
    }
    
    //prints out the whole deck
    public void print(){
        for(int i = 0; i < deck.size(); i++){
            System.out.println(deck.get(i).print());
        }
    }
    
    //creates the original deck in order 
    public void setup(){
        String color = "black";
        String shape = "spades";
        
        for(int num = 1; num < 14; num++){
            //creates a new card using the card class
            Card newCard = new Card(color, num, shape);
            deck.add(newCard);
            //uses this switch case method to move on from shape to shape in order
            if(num == 13 && shape != "clubs"){
               switch(shape){
                   case "spades": 
                       color = "red";
                       shape = "hearts";
                       break;
                   case "hearts": 
                       shape = "diamonds";
                       break;        
                   case "diamonds": 
                       shape = "clubs";
                       color = "black";
                       break;   
               } 
               num = 0;
            }
        }
    }

}
