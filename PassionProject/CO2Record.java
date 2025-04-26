
/**
 * The main object used the in the program, which represents one Country and their data including
 * CO2 emissions, GDP, Population, and Coal CO2 emissions. 
 * 
 */
import java.util.ArrayList;

public class CO2Record
{      
    //public fields so they can be easily accessed by other classes
    public String country;
    //Arraylists for all the data points as they are in the data.txt file
    public ArrayList<Double> co2;
    public ArrayList<Double> co2_per_capita;
    public ArrayList<Double> gdp;
    public ArrayList<Double> population;
    public ArrayList<Double> coal_co2;
 

    /**
     * Constructor of the class CO2Record, which assigns all values to the fields given in the parameters
     */
    public CO2Record(String newCountry, ArrayList<Double> newCo2, ArrayList<Double> newGdp, ArrayList<Double> newPopulation, ArrayList<Double> newCoal_co2)
    {   
        //Assigns values to the fields
        country = newCountry;
        co2 = newCo2;
        gdp = newGdp;
        population = newPopulation;
        coal_co2 = newCoal_co2;
    }

}
