/**
 * GUI class that allows the user to view global statistics including CO2 emissions,
 * population, GDP, and coal CO2 emissions data for different countries.
 * This class provides both comparative line graphs for multiple countries
 * and detailed bar graphs for individual country analysis. Also allows for live data
 * to be displayed on the screen, which is based on the user's region and fetched from the Electricity Map API.
 * 
 * @author Pranav Gadde
 * @version 1.0
 */
import java.util.ArrayList; 
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color; 
import javax.swing.JOptionPane;
import java.util.Collections; 
import java.awt.BasicStroke;  
import java.awt.Font;  
import java.util.Random;  
import java.awt.geom.AffineTransform;  
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File; 
import java.awt.GraphicsEnvironment;  
import java.net.URL; 
import java.net.HttpURLConnection;  
import java.net.URI;
import java.util.Scanner;
 


public class GUI
{
    //The dropdowns for the menu pages, including the data type, how much data, and which country data
    //learned how to use JComboBox from https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
    //static means you don't need to make a instance of this class to access these variables, you can do it from a class level
    static JComboBox dropdown;
    static JComboBox dropdownData;
    static JComboBox dropdownType;
    
    private Countries countries = new Countries();
    //Data for the dropdowns as Strings to be used by JComboBox
    String countryNames[] = {"Australia", "Brazil", "Canada", "China", "Germany", "India", "Japan", "Russia", "United Kingdom", "United States", };
    String dataOptions[] = {"Individual Country", "Top 5 Countries"};
    String fieldOptions[] = {"CO2 Emissions", "Population", "GDP", "Coal CO2"};
    
    /**
     * Main method creates an instance of the GUI class
     */
    public static void main(String[] args){
        GUI gui = new GUI();
    }

    /**
     * Constructor for the GUI class, which calls the drawCanvas method to display menu
     */
    public GUI()
    {
        drawCanvas();
    }
        
    /**
     * Creates and displays the main menu with all the settings to choose your type of graph
     */
    public void drawCanvas() {
        // Create a new JFrame to add content to for the menu
        JFrame frame = new JFrame("Global Statistics Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().setBackground(new Color(40, 44, 52));  // Dark Blue
        
        // Create main panel with some padding
        JPanel panel = new JPanel();
        panel.setLayout(null);  
        //https://gist.github.com/altayalp/77f80ab4718e1f238073a433b5b87a59
        //Allows for a hexadecial color code to be passed for the background
        panel.setBackground(Color.decode("#244855"));
        panel.setBounds(20, 20, 760, 520);

        // Creates a new title label
        JLabel titleLabel = new JLabel("Global Statistics Viewer");
        Font customFont = null;
        
        //https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
        //importing a custom font "Helvetica Now" from a ttf file which has been downloaded
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/helveticanow.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (Exception e) {
            System.out.println("Error loading font");
        }
        //use the custom font from the variable customFont, and set font size to 40, and bolded
        titleLabel.setFont(customFont.deriveFont(Font.BOLD, 40));
        //.setForeground() sets the color of the text of the label
        titleLabel.setForeground(Color.decode("#FBE9D0"));
        titleLabel.setBounds(50, 40, 700, 50);
        panel.add(titleLabel);
        
        //Creates new header labels for the menu
        JLabel label1 = new JLabel("Select Data Type");
        //Uses the regular font version of the custom font
        label1.setFont(customFont.deriveFont(Font.PLAIN, 20));
        label1.setForeground(Color.decode("#FBE9D0"));
        label1.setBounds(50,100,200,50);
        panel.add(label1);

        //https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
        //JComboBox allows for a dropdown to be created using a ArrayList of fields
        dropdownData = new JComboBox(fieldOptions);
        dropdownData.setBounds(50,150,200,50);
    
        JLabel label2 = new JLabel("Select Data Amount");
        label2.setFont(customFont.deriveFont(Font.PLAIN, 20));
        label2.setForeground(Color.decode("#FBE9D0"));
        label2.setBounds(50,225,400,50);
        panel.add(label2);
        
        //The dropdown for if you want to see the top 5 countries or an individal country
        dropdownType = new JComboBox(dataOptions);
        dropdownType.setBounds(50,275,200,50);
        
        /*Adds an actionListener so if the user wants an indiviual country it will let user choose
         * a country or else hides the extra dropdown is user chooses top 5 countries
         */
        dropdownType.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String input = (String) dropdownType.getSelectedItem();
                if(input.equals("Individual Country")){
                    dropdown.setVisible(true);
                }else{
                    dropdown.setVisible(false);
                }
            }
        });
        
        //Contains the individual country values
        dropdown = new JComboBox(countryNames);
        dropdown.setBounds(50,350,200,50);
        
        //submit button for the menu to draw a graph
        JButton submitButton = new JButton("Get Graph");
        submitButton.setBounds(50,420,200,50);
        submitButton.setFont(customFont.deriveFont(Font.BOLD, 20));
        //Sets the color of the text and background of the button
        submitButton.setForeground(Color.decode("#244855"));
        submitButton.setBackground(Color.decode("#ec1232"));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleInput();
            } 
        });
        //Creates the button for the live data
        JButton liveButton = new JButton("Get Live Data");
        liveButton.setBounds(350,150,200,50);
        liveButton.setFont(customFont.deriveFont(Font.BOLD, 20));
        liveButton.setForeground(Color.decode("#244855"));
        liveButton.setBackground(Color.decode("#ec1232"));
        //Adds an actionListener which calls the getLiveData method when the button is clicked
        liveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getLiveData();
            } 
        });
        
        //Adds all the elements to the panel
        panel.add(dropdownData);
        panel.add(dropdownType);
        panel.add(dropdown);
        frame.add(submitButton);
        frame.add(liveButton);
        
        frame.add(panel);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
    }

    /**
     * Fetches data from the API and goes through it to form a JSON string. Then that string is broken down
     * to get individual values of Carbon Intensity, Updated Time, and Zone. 
     */
    public void getLiveData(){
        //https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751
        //Gets data from an API url and then uses Scanner class to read the data
        try {
            //Converts to a URL object and then opens a connection to the API
            URL url = new URI("https://api.electricitymap.org/v3/carbon-intensity/latest").toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //Uses Scanner class to read the data from the API
            Scanner scanner = new Scanner(url.openStream());
            String data = "";
            //Adds all of the data to the data string
            while(scanner.hasNextLine()){
                data += scanner.nextLine();
            } 
            scanner.close();
            System.out.println(data);
            
            //https://www.geeksforgeeks.org/java-program-to-convert-json-string-to-json-object/
            //Parses the string to convert to JSON format to make easier to use
            LiveData live = new LiveData();
            
            //Gets substrings of the data string to get values
            int startIndex = data.indexOf("carbonIntensity") + 17;
            //Uses the modifier methods to assign values
            live.setCarbonIntensity(Integer.valueOf(data.substring(startIndex,startIndex + 3)));
            //Gets the zone value from substring of data string
            startIndex = data.indexOf("zone") + 7;
            live.setZone(data.substring(startIndex,startIndex + 11));
            //Gets the updated time value from substring of data string
            startIndex = data.indexOf("updated") + 12;
            live.setUpdated(data.substring(startIndex,startIndex + 24));
          
            
            //draws the graph 
            drawLiveGraph(live);
        } catch(Exception e) {
            System.out.println("Error getting live data!");
        }
    }
    
    /**
     * Draws the GUI for the LiveData which shows a new window containing the carbon intensity, zone, and updated time, and this
     * data is based on the users region. All of this data is retrieved from the liveData objects fields, which have been
     * updated in the previous method. 
     * 
     * @param live: A LiveData object which contains the data from the API in its fields.
     */
    public void drawLiveGraph(LiveData live){
        //Creates a new frame for the live data
        JFrame frame = new JFrame("Live Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.getContentPane().setBackground(new Color(40, 44, 52));

        //Add the carbon intensity value label with units
        JLabel label = new JLabel( "Carbon Intensity: " + String.valueOf(live.getCarbonIntensity()) + " gCO2eq/kWh");
        //Sets the font to Arial, bolded, and size 25
        label.setFont(new Font("Arial", Font.BOLD, 25));
        //Sets text color 
        label.setForeground(Color.decode("#FBE9D0"));
        label.setBounds(50, 100, 500, 50);
        frame.add(label);
        
        //Adds the zone label
        JLabel label2 = new JLabel("Zone: " + live.getZone());
        //Sets the font to Arial, bolded, and size 15
        //Smaller not as big as the carbon intensity label to highlight that
        label2.setFont(new Font("Arial", Font.BOLD, 15));
        //Sets to same text color
        label2.setForeground(Color.decode("#FBE9D0"));
        label2.setBounds(50, 175, 200, 50);
        frame.add(label2);
        
        //Analyzes the time string to convert to proper time and CST from GST
        String data = live.getUpdated();
        //Reads the hour from time string and subtracts 6 to convert to CST
        int hour = Integer.parseInt(data.substring(11,13)) - 6  ;
        //hour is in 24 hour format, so if its greater than 12, it is in PM and subtracts 12
        String AMORPM = "AM";
        if(hour > 12){
            AMORPM = "PM";
            hour = hour - 12;
        }else if(hour == 12){
            //Accounts for 12 hours clock changing to PM at noon
            AMORPM = "PM";
        }else if(hour == 0){
            //Accounts for 12 hour clock being 12 not 0 at midnight
            hour = 12;
        }
        //Reads the minute from time string
        int minute = Integer.parseInt(data.substring(14,16));
        
        //Last updated time label 
        JLabel label3 = new JLabel("Updated Last: " + hour + ":" + minute + " " + AMORPM + " CST");
        //Sets the font smaller again as it is subdata
        label3.setFont(new Font("Arial", Font.BOLD, 15));
        label3.setForeground(Color.decode("#FBE9D0"));
        label3.setBounds(50, 225, 400, 50);
        frame.add(label3);

        //Adds a label to acknowledge the data is from the Electricity Map API
        JLabel label4 = new JLabel("            Data from Electricity Map API");
        //Sets the font smaller again as it is subtext
        label4.setFont(new Font("Arial", Font.BOLD, 15));
        label4.setForeground(Color.decode("#FBE9D0"));
        label4.setBounds(50, 300, 400, 50);
        frame.add(label4);

        frame.setVisible(true);
    }
    
    /**
     * This method is called by the Get Graph Button. Based on the data from the dropdowns, it uses switch case function
     * to call the right methods and draw the appropriate graphs. 
     */
    public void handleInput(){

        String data = (String) dropdownData.getSelectedItem();
        //Checks if the user wants an individual country or line graph of top 5
        if(dropdownType.getSelectedItem().equals("Top 5 Countries")){
            //Checks through what data type the user wants to compare
            switch(data){
                //Based on the dropdown data, it calls the right method to display the graph
                case "CO2 Emissions": 
                    displayTop5CO2();
                    break;
                case "Population":
                    displayTop5Population();
                    break;
                case "GDP":
                    displayTop5GDP();
                    break;
                case "Coal CO2":
                    displayTop5CoalCO2();
                    break;
            }
        
        }else{
            //Gets the country name from the dropdown to pass to the methods
            String country = (String) dropdown.getSelectedItem();
            //Checks through what data type the user wants to compare
            switch(data){
                //Based on the dropdown data, it calls the right method to display the graph
                case "CO2 Emissions": 
                    displayCountryCO2ByName(country);
                    break;
                case "Population": 
                    displayCountryPopulationByName(country);
                    break;
                case "GDP":
                    displayCountryGDPByName(country);
                    break;
                case "Coal CO2":
                    displayCountryCoalCO2ByName(country);
                    break;
            }
        }
    }


    /**
     * Analyzes the country data to get the top 5 countries with highest CO2 emissions by iterating through all the countries
     * data and passes necessary values to drawGraph method. 
     */
    public void displayTop5CO2() {
        // Define y-axis and x-axis values for the graph
        int[] yValues = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
        int[] xValues = {2000,2002,2004,2006,2008,2010,2012,2014,2016,2018,2020,2022};
        // Retrieve the list of CO2 records from the countries data
        ArrayList<CO2Record> records = countries.data;
        // Create a list to store the latest CO2 values for each country
        ArrayList<Double> co2Values = new ArrayList<>();
        // Create a list to store the names of the countries with the highest CO2 values
        ArrayList<String> countryNames = new ArrayList<>();
        // Populate co2Values with the most recent CO2 data for each country
        for(CO2Record record : records){
            co2Values.add(record.co2.get(record.co2.size() - 1));
        }
        // Sort the CO2 values in descending order to find the top 5
        //Learned reverse order from https://www.w3schools.com/java/ref_arraylist_sort.asp
        co2Values.sort(Collections.reverseOrder()); 
        // Find the country names corresponding to the top 5 CO2 values
        for(int i = 0; i < 5; i++){
            for(CO2Record record : records){
                if(record.co2.get(record.co2.size() - 1) == co2Values.get(i)){
                    countryNames.add(record.country);
                }
            }
        }
        // Create a ArrayList to store the top 5 CO2 records
        ArrayList<CO2Record> top5 = new ArrayList<>();
        // Populate top5 ArrayList with the records of the countries with the highest CO2 values
        for(String name : countryNames){
            for(CO2Record record : records){
                if(record.country.equals(name)){
                    top5.add(record);
                }
            }
        }
        // Draw the graph for the top 5 countries with the highest CO2 emissions
        drawGraph(top5, xValues, yValues, "CO2", "CO2 emissions (in millions of tonnes)");
    }

    /**
     * Analyzes the country data to get the top 5 countries with highest population by iterating through all the countries data
     * and passes necessary values to drawGraph method. 
     */
    public void displayTop5Population(){
        //x and y values for the chart units
        int[] yValues = {100000000,250000000,400000000,550000000,700000000,850000000,1000000000,1150000000,1300000000,1450000000};
        int[] xValues = {2000,2002,2004,2006,2008,2010,2012,2014,2016,2018,2020,2022};
        ArrayList<CO2Record> records = countries.data;
        ArrayList<Double> populationValues = new ArrayList<>();
        //Adds all the countries populations to an ArrayList
        for(CO2Record record : records){
            populationValues.add(record.population.get(record.population.size() - 1));
        }
        //https://www.w3schools.com/java/ref_arraylist_sort.asp
        //Reverses the order so its on descending order, to make it easier to get top 5
        populationValues.sort(Collections.reverseOrder());
        
        //Finds the country which matches the top 5, and adds it to new ArrayList
        ArrayList<CO2Record> top5 = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            for(CO2Record record : records){
                if(record.population.get(record.population.size() - 1) == populationValues.get(i)){
                    top5.add(record);
                }
            }
        }
        //Draws graph with all respective values
        drawGraph(top5, xValues, yValues, "Population", "Population (in millions)");
    }

    /**
     * Gets the top 5 countries with the highest GDP by adding all the values to an ArrayList and sorting it 
     * and then passes the top5 values to drawGraph
     */
    public void displayTop5GDP() {
        //x and y values for the chart units, 0 to 10 for abbriviated trillions values
        int[] yValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] xValues = {2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022};
        ArrayList<CO2Record> records = countries.data;
        ArrayList<Double> gdpValues = new ArrayList<>();
        //Adds all the countries GDPs to an ArrayList
        for (CO2Record record : records) {
            gdpValues.add(record.gdp.get(record.gdp.size() - 1));
        }
        //Reverses the order so its on descending order, to make it easier to get top 5
        gdpValues.sort(Collections.reverseOrder());
        //Finds the country which matches the top 5 GDPs, and adds it to new ArrayList
        ArrayList<CO2Record> top5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (CO2Record record : records) {
                if (record.gdp.get(record.gdp.size() - 1) == gdpValues.get(i)) {
                    top5.add(record);
                    System.out.println(record.gdp.get(record.gdp.size() - 1));
                    System.out.println(record.country);
                }
            }
        }
        //Draws graph with all respective values
        drawGraph(top5, xValues, yValues, "GDP", "GDP (in billions of USD)");
    }

    /**
     * Gets the top 5 countries with the highest coal CO2 emissions by adding all the values to an ArrayList and sorting it 
     * and then passes the top5 values to drawGraph
     */
    public void displayTop5CoalCO2() {
        //x and y values uses the thousands for millions of tonnes of Coal CO2
        int[] yValues = {0, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        int[] xValues = {2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022};
        ArrayList<CO2Record> records = countries.data;
        ArrayList<Double> coalCO2Values = new ArrayList<>();
        for (CO2Record record : records) {
            coalCO2Values.add(record.coal_co2.get(record.coal_co2.size() - 1));
        }
        //Reverses the order so its on descending order, to make it easier to get top 5
        coalCO2Values.sort(Collections.reverseOrder());
        //Finds the country which matches the top 5, and adds it to new ArrayList
        ArrayList<CO2Record> top5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (CO2Record record : records) {
                if (record.coal_co2.get(record.coal_co2.size() - 1) == coalCO2Values.get(i)) {
                    top5.add(record);
                }
            }
        }
        //Draws graph with all respective values
        drawGraph(top5, xValues, yValues, "Coal CO2", "Coal CO2 emissions (in millions of tonnes)");
    }

    /**
     * Gets the coal CO2 emissions for a country by iterating through all the countries data and 
     * passing the necessary values to drawBarGraph
     * @param country: Passes the name of the country which the user wants data for
     */
    public void displayCountryCoalCO2ByName(String country){
        // Prompt the user for a country name
        String countryName = country;
        
        ArrayList<CO2Record> records = countries.data;
        CO2Record selectedRecord = null;
        // Find the record for the specified country
            for (CO2Record record : records) {
                if (record.country.equalsIgnoreCase(countryName.trim())) {
                    selectedRecord = record;
                    break;
                }
            }
        //Checks only if the countryName exists
            if (selectedRecord != null) {
                // Prepare data for the bar graph
                int[] xValues = {2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022};
                int[] yValues = new int[selectedRecord.coal_co2.size()];
                //Gets all the y-values from the countries data
                for (int i = 0; i < selectedRecord.coal_co2.size(); i++) {
                    yValues[i] = selectedRecord.coal_co2.get(i).intValue();
                }
                
                // Draw the bar graph
                drawBarGraph(selectedRecord, xValues, yValues, "Coal CO2", "Coal CO2 emissions (in millions of tonnes)");
            } else {
                JOptionPane.showMessageDialog(null, "Country not found.");
            }  
    }

    /**
     * Gets the CO2 emissions for a country by iterating through all the countries data and 
     * passing the necessary values to drawBarGraph
     * @param country: Passes the name of the country which the user wants data for
     */
    public void displayCountryCO2ByName(String country) {
        // Prompt the user for a country name
        String countryName = country;
        
        ArrayList<CO2Record> records = countries.data;
        CO2Record selectedRecord = null;
            // Find the record for the specified country
            for (CO2Record record : records) {
                if (record.country.equals(countryName)) {
                    selectedRecord = record;
                    break;
                }
            }
            //Checks only if the countryName exists
            if (selectedRecord != null) {
                // x and y values for the chart units
                int[] xValues = {2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022};
                int[] yValues = new int[selectedRecord.co2.size()];
                //Gets all the y-values from the countries data
                for (int i = 0; i < selectedRecord.co2.size(); i++) {
                    yValues[i] = selectedRecord.co2.get(i).intValue();
                }
                
                // Draw the bar graph
                drawBarGraph(selectedRecord, xValues, yValues, "CO2", "CO2 emissions (in millions of tonnes)");
            } else {
                JOptionPane.showMessageDialog(null, "Country not found.");
            }  
    }

    /**
     * Gets the population for a country by iterating through all the countries data and 
     * passing the necessary values to drawBarGraph
     * @param country: Passes the name of the country which the user wants data for
     */
    public void displayCountryPopulationByName(String country) {
        // Store the passed country name in a local variable
        String countryName = country;   
        // Check if the country name is not null and not empty after trimming
        if (countryName != null && !countryName.trim().isEmpty()) {
            // Retrieve the list of CO2 records from the countries data
            ArrayList<CO2Record> records = countries.data;
            CO2Record selectedRecord = null;
            // Iterate through the records to find the one matching the specified country
            for (CO2Record record : records) {
                if (record.country.equalsIgnoreCase(countryName.trim())) {
                    selectedRecord = record;
                    break; 
                }
            }
            // If a matching record is found, prepare data for the bar graph
            if (selectedRecord != null) {
                // x and y values for the chart units
                int[] xValues = {2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022};
                int[] yValues = new int[selectedRecord.population.size()];
                // Populate yValues with population data from the selected record
                for (int i = 0; i < selectedRecord.population.size(); i++) {
                    yValues[i] = selectedRecord.population.get(i).intValue();
                }
                // Draw the bar graph using the prepared data
                drawBarGraph(selectedRecord, xValues, yValues, "Population", "Population (in millions)");
            } else {
                JOptionPane.showMessageDialog(null, "Country not found.");
            }
        }
    }

    /**
     * Gets the GDP for a country by iterating through all the countries data and 
     * passing the necessary values to drawBarGraph
     * @param country: Passes the name of the country which the user wants data for
     */
    public void displayCountryGDPByName(String country) {
        // Store the passed country name in a local variable
        String countryName = country;  
        // Check if the country name is not null and not empty after trimming
        if (countryName != null && !countryName.trim().isEmpty()) {
            ArrayList<CO2Record> records = countries.data;
            CO2Record selectedRecord = null;
            
            // Iterate through the records to find the one matching the specified country
            for (CO2Record record : records) {
                if (record.country.equalsIgnoreCase(countryName.trim())) {
                    selectedRecord = record;
                    break;
                }
            }
            // If a matching record is found, prepare data for the bar graph
            if (selectedRecord != null) {
                // Define x-axis values representing years
                int[] xValues = {2000, 2002, 2004, 2006, 2008, 2010, 2012, 2014, 2016, 2018, 2020, 2022};
                // Initialize y-axis values array to store GDP data
                int[] yValues = new int[selectedRecord.gdp.size()];
                
                // Populate yValues with GDP data from the selected record
                for (int i = 0; i < selectedRecord.gdp.size(); i++) {
                    // Convert GDP from billions to a more readable format
                    yValues[i] = (int)(selectedRecord.gdp.get(i) / 1_000_000_000.0); 
                }
                
                // Draw the bar graph using the prepared data
                drawBarGraph(selectedRecord, xValues, yValues, "GDP", "GDP (in trillions of USD)");
            } else {
                // Show a message dialog if the country is not found
                JOptionPane.showMessageDialog(null, "Country not found.");
            }
        }
    }

    /**
     * Draws a bar graph for a single country's data
     * @param record The CO2Record containing the country's data
     * @param xValues Array of years for x-axis
     * @param yValues Array of values for y-axis
     * @param reason Type of data being displayed ("CO2", "Population", etc.)
     * @param yUnit Label for y-axis
     */
    //https://www.geeksforgeeks.org/java-jframe/
    //https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/javax/swing/JFrame.html
    //https://www.geeksforgeeks.org/java-swing-jpanel-with-examples/
    //Sources above were used in order to make the frame, add a panel to it and then add the necessary chart lines and bar graph
    public void drawBarGraph(CO2Record record, int[] xValues, int[] yValues, String reason, String yUnit) {
        JFrame graphFrame = new JFrame(reason + " Bar Graph - " + record.country);
        graphFrame.setSize(600, 600);

        JPanel graphPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                
                //Gets dimensions of the frame so that it can be resizable
                int width = getWidth();
                int height = getHeight();
                int leftMargin = 100;  // Increased left margin
                int rightMargin = 70;
                int topMargin = 70;
                int bottomMargin = 70;
                
                //Sets the background color to black
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, width, height);
                //Sets the color of the text to white
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                //Draws the title of the graph
                String title = record.country + " - " + reason + " Data";
                //https://docs.oracle.com/javase/8/docs/api/java/awt/FontMetrics.html
                //Allows to get the width of the title based on the font so that it can be centered
                int titleWidth = g2d.getFontMetrics().stringWidth(title);
                g2d.drawString(title, (width - titleWidth) / 2, topMargin / 2);
                
                //Draws lines for the sides of the chart
                g2d.setColor(Color.WHITE);
                g2d.drawLine(leftMargin, height - bottomMargin, width - rightMargin, height - bottomMargin); // x-axis
                g2d.drawLine(leftMargin, height - bottomMargin, leftMargin, topMargin); // y-axis
                
                //Rotates the string sideways using the AffineTransform class
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                //https://docs.oracle.com/javase/8/docs/api/java/awt/geom/AffineTransform.html
                AffineTransform originalTransform = g2d.getTransform();
                //Rotates the string 90 degrees to the left to make it sideways
                g2d.rotate(-Math.PI / 2);
                //flips height because the string is sideways
                g2d.drawString(yUnit, -height / 2 - 60, leftMargin - 80); 
                g2d.setTransform(originalTransform);
                //Draws the x-axis label
                g2d.drawString("Years", width / 2 - 20, height - 20);
                
                //highest y-value for the scale depending on the type of data being drawn
                int maxYValue = 0;
                switch(reason){
                    case "GDP" : 
                        maxYValue = 20000000;
                    case "CO2" : 
                        maxYValue = 10000;
                        break;
                    case "Population" : 
                        maxYValue = 1500000000;
                        break;
                    case "Coal CO2" : 
                        maxYValue = 10000;
                        break;
                        
                }

                // Draw y-axis labels
                int numYLabels = 10;
                for (int i = 0; i <= numYLabels; i++) {
                    //Calculates the y-value for the label based on the number of labels and the height of the frame
                    int y = height - bottomMargin - (i * (height - topMargin - bottomMargin) / numYLabels);
                    String label = "";
                    //Calculates the value of the label based on the number of labels and the max y-value
                    int labelValue = i * (maxYValue / numYLabels);
                    //Checks what type of data is being drawn and then adjusts abbreviations accordingly
                    if (reason.equals("GDP")) {
                        //https://www.w3schools.com/java/ref_string_format.asp
                        //Removes large numbers and easier to read
                        label = String.format("$%.1fT", labelValue / 1_000.0); // Show GDP in trillions
                    } else if (reason.equals("CO2")) {
                        label = String.valueOf(labelValue);
                    } else if(reason.equals("Population")){
                        label = String.format("%.1fM", labelValue / 1_000_000.0);
                    }else if(reason.equals("Coal CO2")){
                        label = String.valueOf(labelValue);
                    }
                    

                    g2d.drawString(label, leftMargin - 70, y + 5);
                }

                // Calculate bar width first
                int barWidth = (width - leftMargin - rightMargin) / xValues.length;

                // Draw x-axis labels
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                for (int i = 0; i < xValues.length; i++) {
                    int x = leftMargin + i * barWidth;
                    //Draws the x-axis labels
                    //Exact numbers for barWidth position based on experimenting with the numbers
                    g2d.drawString(String.valueOf(xValues[i]), x + (barWidth - 5) / 2 - 15, height - bottomMargin + 20);
                }

                // Draws the individual bars for the graph 
                for (int i = 0; i < xValues.length; i++) {
                    int x = leftMargin + i * barWidth;
                    //Calculates the height of the bar based on the y-value and the max y-value and gets a percentage of the height of the frame
                    double barHeight = 0.83 * height * ((double) yValues[i] / (double) maxYValue);
                    //Calculates the y-value for the bar based on the height of the frame and the bar height
                    int y = height - bottomMargin - (int) (0.004*height) - (int) barHeight;

                    //Sets the color of the bar to a light blue
                    g2d.setColor(new Color(100, 150, 200));
                    g2d.fillRect(x, y, barWidth - 5,(int) barHeight);

                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Arial", Font.BOLD, 12));
                    String valueText;
                    //Sets the text inside of the bar for the values of the data 
                    //Adjust abbreviations based on the type of data
                    if (reason.equals("Population")) {
                        valueText = String.format("%.1fM", yValues[i] / 1_000_000.0);
                    } else if (reason.equals("GDP")) {
                        
                        valueText = String.format("$%.1fT", (double) yValues[i] / 1000);  // Show GDP in trillions

                    } else {
                        valueText = String.valueOf(yValues[i]);
                    }
                    //Again uses getFontMetrics to get the width of the text and then centers it in bar
                    int textWidth = g2d.getFontMetrics().stringWidth(valueText);
                    int textX = x + (barWidth - 5 - textWidth) / 2;
                    int textY = y + (int) barHeight / 2 + g2d.getFontMetrics().getAscent() / 2;
                    g2d.drawString(valueText, textX, textY);
                }
            }
        };

        graphFrame.add(graphPanel);
        graphFrame.setVisible(true);
    }

    /**
     * Draws a line graph for multiple countries' data
     * @param records List of CO2Records containing countries' data
     * @param xValues Array of years for x-axis
     * @param yValues Array of values for y-axis
     * @param reason Type of data being displayed ("CO2", "Population", etc.)
     * @param yUnit Label for y-axis
     */
    public void drawGraph(ArrayList<CO2Record> records, int[] xValues, int[] yValues, String reason, String yUnit) {
        JFrame graphFrame = new JFrame("Top 5 Countries Graph");
        graphFrame.setSize(500, 500);

        // Create a custom JPanel to draw the graph
        JPanel graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Set up the graph axes
                int width = getWidth();
                int height = getHeight();
                int margin = 70;
                //Sets the background color to black
                g2d.setColor(Color.BLACK);
                g2d.fillRect(0, 0, width, height);

                g2d.setColor(Color.WHITE);
                // Draw x and y axes
                g2d.drawLine(margin, height - margin, width - margin, height - margin); // x-axis
                g2d.drawLine(margin, height - margin, margin, margin); // y-axis

                // Draw axis labels
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                // Transforms the y-axis label 90 degrees to the left so its sideways and fits
                //https://docs.oracle.com/javase/8/docs/api/java/awt/geom/AffineTransform.html
                AffineTransform transformation = g2d.getTransform();
                g2d.rotate(-Math.PI/2);
                g2d.drawString(yUnit, -height/2 - 60, margin - 50);
                g2d.setTransform(transformation);
                
                // X-axis label
                g2d.drawString("Years", width/2 - 20, height - 20);

                // Draw x-axis labels
                for (int i = 0; i < xValues.length; i++) {
                    int x = margin + (xValues[i] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                    g2d.drawString(String.valueOf(xValues[i]), x - 10, height - margin + 20);
                }

                // Draw y-axis labels
                for (int i = 0; i < yValues.length; i++) {
                    int y = height - margin - (i * (height - 2 * margin) / (yValues.length - 1));
                    String label;
                    //Checks what type of data is being drawn and then adjusts abbreviations accordingly
                    if (yValues[i] >= 1_000_000) {
                        label = String.format("%.1fM", yValues[i] / 1_000_000.0);
                    } else if (yValues[i] >= 1_000) {
                        label = String.format("%.1fK", yValues[i] / 1_000.0);
                    }else if(reason == "GDP"){
                        label = String.valueOf(yValues[i] + "T");
                    } else {
                        label = String.valueOf(yValues[i]);
                    }
                    //Draws the y-axis labels
                    g2d.drawString(label, margin - 40, y + 5);
                }

                int count;
                //switch case to get the right graph data from the CO2 Records and then draw the lines
                switch(reason){
                    case "CO2":
                        count = 0;
                        for(CO2Record record : records){
                            Random random = new Random();
                            //Gets a random color for the lines and title based on country 
                            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                            //Checks if one of them is lower to remove errors and discrepancies
                            int loopLimit = Math.min(record.co2.size() - 1, xValues.length - 1);
                            //Iterates between all datapoints and then creates a line between each point to make one big line graph
                            for(int i = 0; i < loopLimit; i++){
                                //Calculates the x-value for the line based on the width of the frame and the x-values
                                int x1 = margin + (xValues[i] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                int y1 = (int) ((height - margin - (record.co2.get(i) /10000 * (height - 2 * margin)))) + 50;
                                //Connect end of line to the next data point beginning to make graph
                                int x2 = margin + (xValues[i+1] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                int y2 = (int)((height - margin - (record.co2.get(i+1) / 10000 * (height - 2 * margin)))) + 50;
                                //increases the thickness of the line 
                                g2d.setStroke(new BasicStroke(3));
                                g2d.drawLine(x1, y1, x2, y2);
                                //Adds the country name and final valueto the end of the line
                                if(i == loopLimit - 1){
                                    g2d.setFont(new Font("Arial", Font.BOLD, 10));
                                    g2d.drawString(record.country + " " + record.co2.get(i), x2 - 30, y2 - 10);
                                }
                            }
                            g2d.setFont(new Font("Arial", Font.BOLD, 20));
                            //Adds the countries name at the top 
                            g2d.drawString(record.country, margin + 200 * count, 20);
                            count++;
                        }
                        break;
                    case "Population":
                        count = 0;
                        for(CO2Record record : records){
                            Random random = new Random();
                            //Gets a random color for the lines and title based on country 
                            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                            int loopLimit = Math.min(record.population.size() - 1, xValues.length - 1);
                            //Iterates between all datapoints and then creates a line between each point to make one big line graph
                            for(int i = 0; i < loopLimit; i++){
                                //Calculates the x-value for the line based on the width of the frame and the x-values
                                int x1 = margin + (xValues[i] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                int y1 = (int)(height - margin - (record.population.get(i) * (height - 2 * margin) / yValues[yValues.length-1]));
                                int x2 = margin + (xValues[i+1] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                int y2 = (int)(height - margin - (record.population.get(i+1) * (height - 2 * margin) / yValues[yValues.length-1]));
                                //Increases the thickness of the line
                                g2d.setStroke(new BasicStroke(3));
                                //Draws the line between the two points
                                g2d.drawLine(x1, y1, x2, y2);
                                //Adds the country name and final valueto the end of the line
                                if(i == loopLimit - 1){
                                    g2d.setFont(new Font("Arial", Font.BOLD, 10));
                                    g2d.drawString(record.country + " " + String.format("%.1fM", record.population.get(i)/1000000.0), x2 - 30, y2 - 10);
                                }
                            }
                            //Adds the countries name at the top 
                            g2d.setFont(new Font("Arial", Font.BOLD, 20));
                            g2d.drawString(record.country, margin + 200 * count, 20);
                            count++;
                        }
                        break;
                    case "GDP":
                        count = 0;
                        //Iterates through all the countries and then draws the lines
                        for (CO2Record record : records) {
                            Random random = new Random();
                            //Gets a random color for the lines and title based on country 
                            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                            int loopLimit = Math.min(record.gdp.size() - 1, xValues.length - 1);
                            //Divides the GDP by 1000000000 to get a more readable format to convert to trillions
                            int divideAmount = 1000000000;
                            divideAmount *= 100;
                            //Iterates between all datapoints and then creates a line between each point to make one big line graph
                            for (int i = 0; i < loopLimit; i++) {
                                //Calculates the x-value for the line based on the width of the frame and the x-values
                                double currentAm = record.gdp.get(i)/(divideAmount)/1000;
                                double nextAm = record.gdp.get(i+1)/(divideAmount)/1000;
                                int x1 = margin + (xValues[i] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                //Converts to an int value because the calculations give a double
                                int y1 = (int) ((height - margin - (currentAm/10 * (height - 2 * margin)))) - 50;
                                int x2 = margin + (xValues[i + 1] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                int y2 = (int) ((height - margin - (nextAm/10 * (height - 2 * margin)))) - 50;
                                //Increases the thickness of the line
                                g2d.setStroke(new BasicStroke(3));
                                //Draws the line between the two points
                                g2d.drawLine(x1, y1, x2, y2);
                                if (i == loopLimit - 1) {
                                    g2d.setFont(new Font("Arial", Font.BOLD, 10));
                                    
                                    g2d.drawString(record.country + " $" + String.format("%.1fT", record.gdp.get(i)), x2 - 30, y2 - 10);
                                }
                            }
                            g2d.setFont(new Font("Arial", Font.BOLD, 20));
                            g2d.drawString(record.country, margin + 200 * count, 20);
                            count++;
                        }
                        break;
                    case "Coal CO2":
                        count = 0;
                        for(CO2Record record : records){
                            //Uses random class to get a random color for the lines and title based on country 
                            Random random = new Random();
                            g2d.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                            int loopLimit = Math.min(record.coal_co2.size() - 1, xValues.length - 1);
                            //Iterates between all datapoints and then creates a line between each point to make one big line graph
                            for(int i = 0; i < loopLimit; i++){
                                int x1 = margin + (xValues[i] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                //Gets the percent this y-value is of the max y-value and then multiplies it by the height of the chart space
                                int y1 = (int)(height - margin - (record.coal_co2.get(i) * (height - 2 * margin) / yValues[yValues.length-1]));
                                //Similar to y, where it is the x-value for the line based on the width of the frame and the x-values
                                int x2 = margin + (xValues[i+1] - xValues[0]) * (width - 2 * margin) / (xValues[xValues.length - 1] - xValues[0]);
                                int y2 = (int)(height - margin - (record.coal_co2.get(i+1) * (height - 2 * margin) / yValues[yValues.length-1]));
                                g2d.setStroke(new BasicStroke(3));
                                g2d.drawLine(x1, y1, x2, y2);
                                if(i == loopLimit - 1){
                                    g2d.setFont(new Font("Arial", Font.BOLD, 10));
                                    g2d.drawString(record.country + " " + record.coal_co2.get(i), x2 - 30, y2 - 10);
                                }
                            }
                            //Adds the countries name at the top 
                            g2d.setFont(new Font("Arial", Font.BOLD, 20));
                            g2d.drawString(record.country, margin + 200 * count, 20);
                            count++;
                        }
                        break;
                }
            }
        };

        graphFrame.add(graphPanel);
        graphFrame.setVisible(true);
    }
}
