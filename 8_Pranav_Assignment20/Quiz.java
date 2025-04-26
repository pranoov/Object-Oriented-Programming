
/**
 * It gives the user a series of multiple choice questions, and then asks for an answer. At the
 * end their score is printed out, along with a respective message. 
 *
 * @author Pranav Gadde
 */

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Quiz
{
    private ArrayList<String> questions;
    private ArrayList<String[]> answerChoices;
    private Character[] correctAnswers;
    private ArrayList<Character> userAnswers;
    private Scanner scanner;
    
    /**
     * Creates a new Quiz object to call the constructor
     */
    public static void main(String[] args){
        Quiz quiz = new Quiz();
    }
    
    /**
     * Initializes all of the ArrayLists and Arrays needed for the quiz.
     * Also sets up the scanner to read the file with questions and answer choices,
     * and calls the respective methods. 
     */
    public Quiz()
    {
        questions = new ArrayList<>();
        answerChoices = new ArrayList<>();
        userAnswers = new ArrayList<>();
        //Initializes the Array with values, instead of size
        correctAnswers = new Character[]{'c','d','a','c'};
        try{
            scanner = new Scanner(new File("quiz-questions.txt"));
        }catch(Exception e){
            System.out.println("The file was not found!");
        }
        populate();
        createQuiz();
        score();
    }

    /**
     * Reads the questions and answer choices from the file, and then adds them to their
     * respective ArrayLists.
     */
    public void populate() {
            int count = 0;
            while (count < 4) {
                String question = scanner.nextLine();
                questions.add(question);
                String[] answers = new String[]{"","","",""};
                for(int i = 0; i < 4; i++){
                    answers[i] = scanner.nextLine();
                }
                answerChoices.add(answers);
                count++;
            }
            scanner.close();
    }
    
    /**
     * Prints out all of the quiz questions and answer choices, and waits for the users
     * response. Loops through all the questions, and formats them nicely.
     */
    public void createQuiz(){
        System.out.println("\nWelcome to the online quiz! Please select a best possible answer for each of the following");

        int count = 1;
        for(String element: questions){
            scanner = new Scanner(System.in);
            System.out.println("\n" + count + ". " + element);
            int count2 = 1;
            for(String item: answerChoices.get(count - 1)){
                Character tempChar = 'a';
                if(count2 == 2)tempChar = 'b';
                if(count2 == 3)tempChar = 'c';
                if(count2 == 4)tempChar = 'd';
                System.out.println("\t" + tempChar + ". " + item);
                count2++;
            }
            count++;
            System.out.print("\nYour answer please: ");
            userAnswers.add(scanner.next().charAt(0));
        }
    }
    
    /**
     * Called at the end of the game, and using the ArrayList with the users answers, it checks
     * to see how many questions they got right. Then it is printed along with an appropriate
     * message.
     */
    public void score(){
        int score = 0;
        int count = 0;
        for(Character element: userAnswers){
            if(element == correctAnswers[count]){
                score++;
            }
            count++;
        }
        System.out.println("\nYou scored " + score + " correct answers out of 4.");
        if(score == 1)System.out.println("You didn't do to good, better study more!");
        if(score == 2)System.out.println("Almost close to passing, better study more!");
        if(score == 3)System.out.println("One off, not bad you were close!");
        if(score == 4)System.out.println("A perfect score, great job!");
    }
}
