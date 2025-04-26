
/**
 * Write a description of class Order here.
 *
 * @author Pranav Gadde
 */
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.File;

public class Order
{
    private Scanner scanner; 
    String name; 
    int size;
    String crust;
    ArrayList<String> toppings;
    ArrayList<String> names;
    
    /**
     * Creates a new instance of Order object
     */
    public static void main (String[] args){
        Order order = new Order();
    }
    
    /**
     * Constructor creating the ArrayList for names and toppings to be used, and calling methods to run.
     */
    public Order()
    {   
        toppings = new ArrayList<>();
        names = new ArrayList<>();
        getNames();
        askUser();
    }
    
    /**
     * Goes through all the names from file, and adds them to ArrayList
     */
    public void getNames(){
        try{
            scanner = new Scanner(new File("Names.txt"));
            while(scanner.hasNext()){
                names.add(scanner.nextLine());
            }
        }catch(Exception e){
            System.out.println("File not found");
        }
    }
    
    /**
     * Takes the whole users order and then, tells them the details about their order.
     * If their names matches one of the matching ones, it gives them a discount.
     */
    public void askUser(){
        scanner = new Scanner(System.in);
        System.out.println("Welcome to Pranav's Pizza!");
        System.out.println("\nWhat is your name?");
        name = scanner.nextLine();
        boolean discount = false;
        if(name.equals(names.get(0)) || name.equals(names.get(1))){
            System.out.println("\n" + name + " you are eligible for 20% coupon!");
            discount = true;
        }
        System.out.println("\nWhat pizza size would you like? (10in, 12in, 14in, and 16in)");
        scanner = new Scanner(System.in);
        size = scanner.nextInt();
        
        System.out.println("\nWhat crust would you like? (Thin, Hand-Tossed, or Deep-Dish)");
        scanner = new Scanner(System.in);
        crust = scanner.nextLine();
        
        System.out.println("\nWhat toppings would you like? (1.Pepperoni, 2.Sausage, 3.Peppers, 4.Onion, 5.Olive, 6.Tomato)");
        System.out.println("3 toppings free, and any extra are $1.50 each. Enter each topping by number seperated by commas.");
        scanner = new Scanner(System.in);
        String list = scanner.nextLine();
        String[] stuff = list.split(",");
        for(String element: stuff){
            String food = "";
            if(element.equals("1"))food = "Pepperoni";
            if(element.equals("2"))food = "Sausage";
            if(element.equals("3"))food = "Peppers";
            if(element.equals("4"))food = "Onion";
            if(element.equals("5"))food = "Olive";
            if(element.equals("6"))food = "Tomato";
            toppings.add(food);
        }
        
        Pizza newPizza = new Pizza(size,crust,toppings);
        
        System.out.println("\n" + name + " you chose a " + size + " inch " + crust + " pizza with " + toppings);
        System.out.println("The cost of the pizza is " + newPizza.getCost() +  ". You paid 8% tax of the total cost of the pizza");
        
        double finalCost = newPizza.getCost();
        if(discount)finalCost *= 0.8;
        finalCost = (finalCost) * 0.08 + finalCost;
       
        System.out.println("\nYour total cost is: $" + finalCost);
        System.out.println("Your order will be ready in 30 min");
        scanner.close();
    }

}
