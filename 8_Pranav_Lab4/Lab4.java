
/**
 * Write a description of class Lab4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;

public class Lab4
{
    private HashMap<String, ArrayList> quizData; 
    ArrayList<String> failStudents = new ArrayList<>();
    
    /**
     * Main method
     * Creates a scanner class which reads the input file, and then passed it to the constructor 
     * to populate the HashMap
     */
    public static void main(String[] args){
        Scanner scanner;
        try{ 
            scanner = new Scanner(new File("input_8.txt"));
            Lab4 lab = new Lab4(scanner);
        }catch(Exception e){
            System.out.print("Error opening the file!");
        }
    }
    
    
    /**
     * Constructor
     * Takes in the data from the scanner of the text file data and populates the HashMap. Then it calls
     * the appropriate methods in order to print out data of the students. 
     * 
     * @parameter scanner: contains the data the Scanner class got from the file 
     */
    public Lab4(Scanner scanner)
    {   
        quizData = new HashMap<>();
        while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] items = line.split(" ");
                if(items[0].equals("Name")) continue;
                //full name of the student
                String name = items[0] + " " + items[1];
                ArrayList<Double> scores = new ArrayList<>();
                for(int i = 2; i < items.length; i++){
                    scores.add(Double.valueOf(items[i]));
                } 
                quizData.put(name, scores);
        }
        //calls all respective methods needed
        printInfo();
        System.out.println();
        printAvg();
        System.out.println();
        printFail();
    }
    
    /**
     * Prints all of the student data of the names and all their quiz scores nicely, along with headings
     * at the top. 
     */
    public void printInfo(){
        System.out.println("Student Information:");
        System.out.printf("%-20s%-10s%-10s%-10s\n", "Name", "Quiz1", "Quiz2", "Quiz3");
        for(String element : quizData.keySet()){
            ArrayList<Double> scores = quizData.get(element);
            System.out.printf("%-20s%-10s%-10s%-10s\n", element, String.valueOf(scores.get(1)), String.valueOf(scores.get(2)), String.valueOf(scores.get(3)));
        }
    }
    
    /**
     * Gets all the average scores of the students, and then prints them out nicely with their names. Students
     * with an average below 70.0 are pushed to an ArrayList to be used by a different method.
     */
    public void printAvg(){
        System.out.println("Average score for each student:");
        for(String element : quizData.keySet()){
            ArrayList<Double> scores = quizData.get(element);
            Double total = 0.0;
            for(Double value: scores){
                total += value;
            }
            Double avg = total/(scores.size());
            if(avg < 70.0){ 
                failStudents.add(element);     
            }
            // the 0.2 rounds to 2 decimal places
            System.out.printf("%-20s%-100.2f\n", element, avg);
        }
    }
    
    /**
     * Prints out all of the students that are failing using the failStudents ArrayList. There students quiz
     * average is found and printed out nicely with printf command, and at the end the total number students
     * which are failing is printed.
     */
    public void printFail(){
        System.out.println("\nFollowing students have below 70%, a failing grade:");
        for(String element: failStudents){
            ArrayList<Double> scores = quizData.get(element);
            Double total = 0.0;
            for(Double value: scores){
                total += value;
            }
            Double avg = total/3;
            if(avg < 70.0) failStudents.add(element);
            System.out.printf("%-20s%-100.2f\n", element, avg);
        }
        System.out.printf("%-20s", "In all " + failStudents.size() + " are failing");
    }


}
