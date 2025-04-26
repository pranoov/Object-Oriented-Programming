
/**
 * Write a description of class Computer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Computer
{
    // instance variables - replace the example below with your own
    private String name;
    private int cost; 
    private int storage;
    private int ram; 
    private int size; 
    private String color;
    
    

    /**
     * Constructor for objects of class Computer
     */
    public Computer(String compName,int compCost, int compRam, String compColor, int compStorage, int compSize)
    {
        // initialise instance variables
           name = compName;
           cost = compCost;
           ram = compRam;
           color = compColor;
           storage = compStorage;
           size = compSize;
           
           System.out.println(name.toString() + " has " + Integer.toString(storage) + "GB of Storage, " + Integer.toString(ram) + "GB of Ram, " + Integer.toString(size) + " inches, " + color.toString() + " color, and costs $" + Integer.toString(cost));
            
    }
    
    public String getName(){
        return name;
    }
    
    public int getCost(){
        return cost;
    }
    
    public int getRam(){
        return ram;
    }
    
    public String getColor(){
        return color;
    }
    
    public int getSize(){
        return size;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public void setCost(int newCost){
        cost = newCost;
    }
    
    public void setRam(int newRam){
        ram = newRam;
    }
    
    public void setColor(String newColor){
        color = newColor;
    }
    
    public void setSize(int newSize){
        size = newSize;
    }
    
    public void printOut(){
        System.out.println(name.toString() + " has " + Integer.toString(storage) + "GB of Storage, " + Integer.toString(ram) + "GB of Ram, " + Integer.toString(size) + " inches, " + color.toString() + " color, and costs $" + Integer.toString(cost));
    }
    
    public void upgradeComputer(){
        ram += 2;
        storage += 100;
        cost += 100;
        size += 1;
    }

}
