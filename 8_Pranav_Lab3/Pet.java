
/**
 * Write a description of class Pet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class Pet
{
    private String name;
    private int age;
    private String[] favoriteToys; 
    private ArrayList<String> vaccinations;

    public Pet(String newName, int newAge, String[] newToys, ArrayList<String> newVaccinations)
    {
         name = newName; 
         age = newAge;
         favoriteToys = newToys;
         vaccinations = newVaccinations;
    }
    
    public void printInfo(){
        System.out.println("\nPet Name: " + name);
        System.out.println("Pet's Age: " + age);
        System.out.print("Favorite toys: ");
        for(String element: favoriteToys){
            System.out.print(element + " ");
        }
        System.out.print("\nVaccinations: ");
        for(String element: vaccinations){
            System.out.print(element + " ");
        }
        System.out.println("");
    }

}
