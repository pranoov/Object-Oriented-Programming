/**
 * Countries class manages the loading and storage of country-specific data
 * including CO2 emissions, population, GDP, and coal CO2 emissions.
 * 
 * This class reads data from a file and stores it in an ArrayList of CO2Record objects.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Countries
{
    //data ArrayList which contains all the countries records
    public ArrayList<CO2Record> data;

    /**
     * Constructor that initializes the data ArrayList and populates it with data from a file.
     */
    public Countries()
    {
        data = new ArrayList<>();
        populate();
    }
    
    /**
     * Reads data from the data.txt file and populates the data ArrayList.
     * Each country's data includes CO2 emissions, population, GDP, and coal CO2 emissions
     * over multiple years.
     */
    public void populate() {
        try {
            //Creates a scanner to read the data.txt file
            Scanner scanner = new Scanner(new File("data.txt"));
 
            scanner.nextLine();
            
            //Iterates through all the lines in the file
            while(scanner.hasNextLine()) {
                String country = "";
                //Creates an arraylist for each of the data points
                ArrayList<Double> co2 = new ArrayList<>();
                ArrayList<Double> gdp = new ArrayList<>();
                ArrayList<Double> population = new ArrayList<>();
                ArrayList<Double> coal_co2 = new ArrayList<>();
                
                //Reads for the country data containing 24 individual years
                for(int i = 0; i <= 23; i++){
                    String line = scanner.nextLine();
                    //Splits the line into an array of strings
                    String[] items = line.split(",");
                    //Sets the country name
                    country = items[0];
                    //Adds the data points to the arraylists
                    //https://www.geeksforgeeks.org/double-doublevalue-method-in-java-with-examples/
                    //Converts the string data to a double
                    co2.add(Double.valueOf(items[2]));
                    gdp.add(Double.valueOf(items[4]));
                    population.add(Double.valueOf(items[5]));
                    coal_co2.add(Double.valueOf(items[6]));
                }
                
                //Adds the CO2Record to the data set
                CO2Record newRecord = new CO2Record(country, co2, gdp, population, coal_co2);
                data.add(newRecord);
            }
            scanner.close();
        } catch(Exception e) {
            //If the file can't be opened, it prints an error message
            System.out.println("File can't be opened!");
        }
    }

}
