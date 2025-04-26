
/**
 * Contains all the states for the Student object, and getter methods to be used
 * by the Roster class.
 *
 * @author Pranav Gadde
 */
public class Student
{
    private String name;
    private int grade;
    private int hall;

    /**
     * Inilitiazes the instance variables name, grade, and hall.
     * 
     * @param name: name of the student
     * @param grade: the grade level of student
     * @param hall: which hall the student lives in
     */
    public Student(String newName, int newGrade, int newHall)
    {
        name = newName;
        grade = newGrade;
        hall = newHall;
    }
    
    /**
     * Getter method for variable name
     * @return name: returns the String of variable name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Getter method for grade
     * @return grade: returns the int of variable grade
     */
    public int getGrade(){
        return grade;
    }
    
    /**
     * Getter method for hall
     * @return hall: returns the int of variable hall
     */
    public int getHall(){
        return hall;
    }
}
