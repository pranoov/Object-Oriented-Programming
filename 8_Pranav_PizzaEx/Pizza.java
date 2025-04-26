
/**
 * Write a description of class Pizza here.
 *
 * @author Pranav Gadde
 */
import java.util.ArrayList;

public class Pizza
{
    private int size;
    private double cost;
    private String crust; 
    private ArrayList<String> toppings; 

    /**
     * Constructor for objects of class Pizza
     */
    public Pizza(int newSize, String newCrust, ArrayList<String> newToppings)
    {
        size = newSize;
        crust = newCrust; 
        toppings = newToppings; 
        cost = getCost();
    }
    
    /**
     * Returns the cost of the pizza based on the size of it
     */
    public double getCost(){
        double temp = 0.00;
        temp = size + 0.99;
        if(toppings.size() > 3){
            temp += (toppings.size() - 3) * 1.5;
        }
        return temp;
    }
    
}
