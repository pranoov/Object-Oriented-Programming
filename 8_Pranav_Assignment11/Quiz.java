
/**
 * Write a description of class Quiz here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Scanner;

public class Quiz
{
    // instance variables - replace the example below with your own
    private int x;
    Random randy = new Random();
    // java array from w3schools
    int[] numbers = {0,0,0,0,0,0,0,0};
    //all the questions and answers to use for questions
    int[]historyAns = {1492, 1776, 27, 1989};
    String[]historyQuestions = {"In what year did Christopher Columbus first sail to the Americas?","In what year did the United States declare independence from Great Britain?","How many amendments are in the U.S. Constitution?","In what year did the Berlin Wall fall?"};
    int[]scienceAns = {8,0,206,1};
    String[]scienceQuestions = {"How many planets are in our solar system?","What is the freezing point of water in degrees Celsius?","How many bones are in the adult human body?","How many protons does a hydrogen atom have?"};
    String[]operators = {"*","+","-","/"};
    int answer1 = 0;
    int answer2 = 0;
    int answer3 = 0;
    int answer4 = 0;
    int count = 0;
    String mode = "math";

    public Quiz()
    {
        for(x=0; x < numbers.length;x++){
            numbers[x] = randomNum();
        }
    }
    
    public void quizQuestion(){
        for(x = 0; x < 80; x++){
            System.out.print("*");
        }
        System.out.println("");
        for(x = 0; x < 25; x++){
            System.out.print(" ");
        }
        System.out.println("Welcome to Pranav's Quiz Page!");
        System.out.println("    You are to answer 4 questions to test your skills. Good luck!");
        
        count = 0;
        for(x = 0; x < 80; x++){
            System.out.print("*");
        }
        
        //prints out questions according to mode chosen
        if(mode == "math"){
            for(x = 1; x < 5; x++){
                System.out.print("\n" + x + ")      Find      " + numbers[x + (x-2)] + " " + operators[x -1] + " " + numbers[2*x-1] + " = ");
            }
        }else if(mode == "science"){
            for(x = 1; x < 5; x++){
                System.out.print("\n" + x + ")      Find      " +  scienceQuestions[x - 1]);
            }
        }else if(mode == "history"){
            for(x = 1; x < 5; x++){
                System.out.print("\n" + x + ")      Find      " +  historyQuestions[x - 1]);
            }
        }

        System.out.println("");
        for(x = 0; x < 80; x++){
            System.out.print("*");
        }
        Scanner scanner = new Scanner(System.in);
        for(x = 1; x<5; x++){
            System.out.println("\nEnter answer for question " + x + ": " );
            int answer = scanner.nextInt(); 
            if(mode == "math"){
                int correctAns = 0;
                // I learned switch from js last year, helps to set correct answer based on which question it is
                switch(x){
                    case 1: correctAns = numbers[x + (x-2)] * numbers[2*x-1];
                    break;
                    case 2: correctAns = numbers[x + (x-2)] + numbers[2*x-1];
                    break;
                    case 3: correctAns = numbers[x + (x-2)] - numbers[2*x-1];
                    break; 
                    case 4: correctAns = numbers[x + (x-2)] / numbers[2*x-1];
                }
                if(answer == correctAns){
                    System.out.println("CORRECT ANSWER");
                    count++;
                }else{
                    System.out.println("WRONG ANSWER");
                }
            }else if(mode == "science"){
                if(answer == scienceAns[x - 1]){
                    System.out.println("CORRECT ANSWER");
                    count++;
                }else{
                    System.out.println("WRONG ANSWER");
                }
            }else if(mode == "history"){
                if(answer == historyAns[x - 1]){
                    System.out.println("CORRECT ANSWER");
                    count++;
                }else{
                    System.out.println("WRONG ANSWER");
                }
            }
      
            System.out.println("");
        }
        //prints out the user score
        System.out.println("You got a " + count + "/4 with a " + count*25.0 + "%!");
        System.out.println("");
        System.out.println("1) Retake it");
        System.out.println("2) Take a Science Quiz");
        System.out.println("3) Take a History Quiz");
        System.out.println("4) End Quiz");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice == 1){
            mode = "math";
            quizQuestion();
        }else if(choice == 2){
            mode = "science";
            quizQuestion();
        }else if(choice == 3){
            mode = "history";
            quizQuestion();
        }else{
            System.out.println("\nThanks for playing my quiz!");
        }
        
        
    }

    public int randomNum(){
        int num = randy.nextInt(100,500);
        return num;
    }
}
