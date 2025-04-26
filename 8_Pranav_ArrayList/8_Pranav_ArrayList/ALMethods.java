

//The ArrayList class is used by importing the java.util package
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class ALMethods
{   
    /*
    The angle brackets define the type of the elements being stored in the collection. It is not necessary
    to redefine them when making a new ArrayList as it will automatically take the property defined for the 
    variable.
    */
    private ArrayList<String> list = new ArrayList<>();
    
    public ALMethods()
    {   
        //Uses the scanner class to read all the words from file and populate them in the ArrayList list 
        try{
            Scanner scanner = new Scanner(new File("words.txt"));
            while(scanner.hasNext()){
                list.add(scanner.nextLine());
            }
            System.out.println(list);
            scanner.close();
        }catch(Exception e){
            System.out.println("File not found!");
        }
    }
    
    public void methods(){
        //3: uses .size() method to get size of ArrayList
        System.out.println("\nNumber of Elements: " + list.size());
        //4 .isEmpty() returns boolean value if ArrayList is empty or not
        if(list.isEmpty()){
            System.out.println("\nArrayList is empty");
        }else{
            System.out.println("\nArrayList is not empty");
        }
        //5 uses .contains() method to check is String Student is in ArrayList
        System.out.println(list.contains("Student") ? "\nThe ArrayList contains the string Student" : "\nThe ArrayList doesn't contain the string Student");
        //6 use .indexOf() to get the index of String Academic, if not in list integer -1 will be returned
        System.out.println(list.indexOf("Academic") == -1 ? "\nArrayList doesn't contain string Academic" : "\nArrayList contains string Academic at index " + list.indexOf("Academic"));
        //7 uses .toArray() to convert ArrayList to Array
        Object[] items = list.toArray();
        System.out.println("\nItems in Array");
        for(Object element: items){
            System.out.println(element);
        }
        //8 uses .get() method to get element at index 2
        System.out.println("\n" + list.get(2));
        //9 uses .set() method to set string Biology at index 2 in ArrayList
        list.set(2,"Biology");
        System.out.println("\n" + list);
        //10 adds string Government to end of ArrayList using .add()
        list.add("Government");
        System.out.println("\n" + list);
        //11 .add() method can also add string Computer at certain index like 3
        list.add(3,"Computer");
        System.out.println("\n" + list);
        //12 uses .remove() method to remove 5th index of ArrayList
        list.remove(5);
        System.out.println("\n" + list);
        //13 .remove() also removes elements, like string Biology
        list.remove("Biology");
        System.out.println("\n" + list);
        //14 .clear() method clears the array, use a temporary ArrayList to not clear ArrayList list
        ArrayList<String> tempList = new ArrayList<>(list);
        tempList.clear();
        System.out.println("\n" + tempList);
        //15 can join two ArrayLists using .addAll() method
        ArrayList Stuff = new ArrayList<>();
        Stuff.add("Table");
        Stuff.add("Desk");
        System.out.println("\nStuff ArrayList: " + Stuff);
        list.addAll(Stuff);
        System.out.println("\n" + list);
        //16 uses .addAll() method to add ArrayList or multiple element at certain index
        list.addAll(1,Stuff);
        System.out.println("\n" + list);
    }

}
