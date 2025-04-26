
/**
 * This class uses the class Student in order to make a collection of students. Then it gives the user the option
 * for many methods to be applied to this collection like analyzing it and removing students. The user
 * can keep doing this until they decide to quit. 
 *
 * @author Pranav Gadde
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Iterator;


public class Roster
{   
    private ArrayList<Student> students;
    private Scanner scanner;
    
    public static void main(String[] args){
        Roster roster = new Roster();
    }
    
    /**
     * The constructor builds the new ArrayList containing the students, from the text file. The it gives
     * the user the option to enact various methods like print, find, remove and analyze on the collection.
     * Once the user is done they can quit, and then a thank you message appears.
     */
    public Roster()
    {
        students = new ArrayList<>();
        try{
            scanner = new Scanner(new File("StudentInfo.txt"));
        }catch(Exception e){
            System.out.println("File not found!");
        }
        populate();
        boolean quit = false;
        while(!quit){
            System.out.println("\nChoose with method you want to run (1-5): ");
            System.out.println("1. Print(): Prints all students in certain grade");
            System.out.println("2. Find(): Prints details of certain student by name");
            System.out.println("3. Remove(): Remove certain student by name");
            System.out.println("4. Analyze(): Prints number of students by hall");
            System.out.println("5. Quit");
            scanner = new Scanner(System.in);
            while(!scanner.hasNextInt() && scanner.nextInt() >= 1 && scanner.nextInt() <= 5){
                System.out.println("\nEnter a number from 1 - 5: ");
            }
            switch(scanner.nextInt()){
                case 1: 
                    print();
                    break;
                case 2: 
                    find();
                    break;
                case 3:
                    remove();
                    break;
                case 4: 
                    analyze();
                    break;
                case 5:
                    quit = true;
                    break;
            }
        }
        System.out.println("Thank you for playing!");
    }
    
    /**
     * This reads every line from the text file, and splits it to an array, by name, grade and hall. 
     * Then a student object is made with this information and added to the collection. 
     */
    public void populate(){
        String line = scanner.nextLine();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            String[] items = line.split(" ");
            items[0] = items[0] + " " + items[1];
            
            if(items[0] != "Name") {
                Student newStudent = new Student(items[0], Integer.parseInt(items[2]), Integer.parseInt(items[3]));
                students.add(newStudent);                                                                                 
            }
           
        }
    }
    
    /**
     * Asks for the user to enter a grade between 10 and 12, and will keep asking until they
     * enter a valid response. Then it uses that number to print the details of all students in that 
     * grade. 
     */
    public void print(){
        scanner = new Scanner(System.in);
        System.out.println("\nEnter a grade level between 10-12");
        while(!scanner.hasNextInt() && scanner.nextInt() >= 10 && scanner.nextInt() <= 12){
            System.out.println("\nEnter a grade level between 10-12!!");
        }
        int grade = scanner.nextInt();
        scanner.close();
        for(Student element: students){
            if(element.getGrade() == grade){
                //Learnmed printf from w3schools, sets them to left side, and gives space and type accordingly
                System.out.printf("%-20s %-7d %-7s \n",element.getName(), grade, element.getHall());
            }
        }
        
    }
    
    /**
     * It asks the user to enter the name of the student and will keep prompting until a valid response is
     * inputed. Then it checks through the student array to see if the name matches, and prints 
     * out the students details. Else a message that the student wasn't found is printed.
     */
    public void find(){
        scanner = new Scanner(System.in);
        System.out.println("Enter student name to find: ");
        while(!scanner.hasNext()){
            scanner = new Scanner(System.in);
            System.out.println("Enter student name to find: ");
        }
              
        String name = scanner.nextLine();
        boolean found = false;
           
        for(Student element: students){
            if(element.getName().equals(name)){
               found = true;
               System.out.println("\nName: " + element.getName());
               System.out.println("Grade: " + element.getGrade());
               System.out.println("Hall: " + element.getHall());     
            }
        }    
        if(!found) System.out.println("\nStudent Not Found");
    
        scanner.close();
    }
    
    /**
     * Similar to the find method, it searches through the whole collection to find the student
     * matching to what the user entered. Instead of a for loop, it uses an iterator, and then 
     * removes the matching student when found from collection.
     */
    public void remove(){
        scanner = new Scanner(System.in);
        System.out.println("\nEnter student name to remove: ");
        
        while(!scanner.hasNext()){
            System.out.println("\nEnter student name to remove: ");  
        }
        Iterator itr = students.iterator();
        boolean found = false;
        String name = scanner.nextLine();
        while(itr.hasNext()){
            if(itr.next().equals(name)){
                itr.remove();
                found = true;
            }
        }
        if(!found)System.out.println("\nStudent was not found!");
        
        scanner.close();
    }
    
    /**
     * Creates a blank array, and then goes through the whole students collection,
     * and keeps increasing the values in the array corresponding to the hall of the students
     * which is then printed out in the end. 
     */
    public void analyze(){
        int[] hallCounts = new int[7];
        for(int count: hallCounts){
            count = 0;
        }
        for(Student element: students){
            int hall = element.getHall();
            hallCounts[hall - 1501]++;
        }
        System.out.println("\nNumber of Students by Halls: ");
        for(int i = 0; i < hallCounts.length; i++){
            System.out.println(i + 1501 + ": " + hallCounts[i]);
        }
    }

}
