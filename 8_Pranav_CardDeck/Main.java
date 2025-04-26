
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner; 

public class Main
{
    private Deck deck;

    /**
     * Constructor for objects of class Main
     */
    public Main()
    {   
        //creates a new deck
        deck = new Deck();
        game();
    }
    
    public void game(){
        Scanner scanner = new Scanner(System.in); 
        System.out.println("A new deck has been created!");
        //runs the game until there are no more cards in the deck
        while(deck.getSize() > 0){
            scanner = new Scanner(System.in); 
            System.out.println("\nHow many cards would you like?");
            int num = scanner.nextInt();
            //shuffles, gets the users cards, and then sorts the deck back
            deck.shuffle();
            deck.getCards(num);
            deck.sort();
            //prints out the remaining amount of cards to the user
            System.out.println("\nThere are " + deck.getSize() + " cards left.");
            
        }
        //printed when all of the cards are over
        System.out.println("You finished the whole deck, woohoo!");
        scanner.close();
    }

}
