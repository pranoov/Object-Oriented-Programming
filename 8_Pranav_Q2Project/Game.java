import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Game {
    //HashMap to store sick people and their contacts
    HashMap<String, ArrayList<String>> sickPeopleContacts = new HashMap<>();
    JFrame frame; 
    //File output stream and print stream for writing to a file
    //Got from File Read Write examples in canvas
    private FileOutputStream out; 
    private PrintStream ps; 

    //Main method creates an instance of the Game class
    public static void main(String[] args){
        Game game = new Game();
    }

    /**
     * Constructor for the Game class. This initializes the frame with a new JFrame, and sets its necessary values.
     * Then it calls the necessary methods in order to get data from the user write it to a file, and then draws 
     * the GUI for the actual game. 
     */
    public Game() {
        //Creates a new frame to draw the canvas on
        //Learned from https://www.geeksforgeeks.org/java-jframe/ to understand creating a frame
        frame = new JFrame("Zombie GUI");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //initializes the fields needed to write to file
        write_file();
        //prompts user for data and writes and reads from the file
        populate();
        //prints all of the contacts to the terminal 
        printContacts();
        //draws the main menu to analyze the data
        drawGame();
    }

    /**
     * Initializes the file output stream for writing contact data. Got this from File Read Write examples in canvas
     */
    public void write_file(){
        try {
            out = new FileOutputStream("sick_people_contacts.txt");
            ps = new PrintStream(out);
        } catch (Exception e) {
            System.out.println("File not found!");
        }
    }

    /**
     * Writes a string to the file which is passed to the method from parameter. 
     * @param s The string to write.
     */
    public void write(String s){
        try {
            //prints a new line with the necessary string 
            ps.println(s);
        } catch (Exception e) {
            //throws exception if cant write to file
            System.out.println("Can't write to file!");
        }
    }

    /**
     * Closes the file output stream.
     */
    public void close_file(){
        ps.close();
    }

    /**
     * This is the main function with draws the actual game GUI and provides the user with all of the options
     * to analyze the data. It provides the user 6 buttons on the screen, which when are pressed it shows the 
     * results of the method on the text box which is at the top of the screen. 
     */
    public void drawGame() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(50, 50, 50));
        frame.add(panel);
        
        //Sets the styling for each of the buttons
        Color buttonColor = new Color(102, 178, 255);  // Light blue for all buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        
        //The text area where the results are presented  
        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(50, 50, 700, 250);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 20));
        //Prevents the box from being editable so the user can only view it
        //learned it from https://forums.oracle.com/ords/apexds/post/jtextarea-disabling-selection-4317
        resultArea.setEditable(false);
        //https://docs.oracle.com/javase/8/docs/api/javax/swing/JTextArea.html
        //Allows for a better looking text box 
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(new Color(200, 200, 200));
        panel.add(resultArea);

        // Button for displaying patient zeros
        JButton button1 = new JButton("Patient Zeros");
        button1.setBounds(50, 350, 200, 100);
        button1.setFont(buttonFont);
        button1.setBackground(buttonColor);
        // When button is clicked, it displays the patient zeros
        button1.addActionListener(e -> {
            ArrayList<String> patientZero = getPatientZero();
            if(patientZero.size() > 0) {
                String lastPerson = patientZero.remove(patientZero.size() - 1);
                //learned from https://geeksforgeeks.org/java-string-join-examples/
                //Connects the patient zeros by commas using String.join and then adds the last person using and for grammer
                resultArea.setText("Patient Zero(s): " + String.join(", ", patientZero) + ", and " + lastPerson);
            } else {
                resultArea.setText("No Patient Zeros Found.");
            }
        });
        panel.add(button1);

        // Button for displaying zombies
        JButton button2 = new JButton("Zombies");
        button2.setBounds(300, 350, 200, 100);
        //Sets the font and background color of the button
        button2.setFont(buttonFont);
        button2.setBackground(buttonColor);
        //Uses an action listener to run function when button is clicked
        button2.addActionListener(e -> {
            ArrayList<String> zombies = getZombies();
            //Shows the potential zombies by commas using String.join and then adds the last person using and for grammer
            if(zombies.size() > 0) {
                String lastPerson = zombies.remove(zombies.size() - 1);
                resultArea.setText("Potential Zombies: " + String.join(", ", zombies) + ", and " + lastPerson);
            } else {
                resultArea.setText("No potential zombies found.");
            }
        });
        panel.add(button2);

        // Button for displaying people who are neither zombies nor patient zeros
        JButton button3 = new JButton("Neither");
        button3.setBounds(550, 350, 200, 100);
        button3.setFont(buttonFont);
        button3.setBackground(buttonColor);
        button3.addActionListener(e -> {
            ArrayList<String> neither = getNeither();
            if(neither.size() > 1) {
                String lastPerson = neither.remove(neither.size() - 1);
                resultArea.setText("Neither Zombies or Patient Zero: " + String.join(", ", neither) + ", and " + lastPerson);
            } else if (neither.size() == 1) {
                resultArea.setText("Neither Zombies or Patient Zero: " + neither.get(0));
            } else {
                resultArea.setText("No people who weren't zombies or patient zero were found.");
            }
        });
        panel.add(button3);

        // Button for displaying most viral people
        JButton button4 = new JButton("Most Viral");
        button4.setBounds(50, 500, 200, 100);
        button4.setFont(buttonFont);
        button4.setBackground(buttonColor);
        //learned it from https://www.geeksforgeeks.org/java-actionlistener-in-awt/
        //Action listener using lambda function to call the getMostViralPeople from method and show it on the canvas
        button4.addActionListener(e -> {
            ArrayList<String> mostViralPeople = getMostViralPeople();
            if (mostViralPeople.size() > 0) {
                String lastPerson = mostViralPeople.remove(mostViralPeople.size() - 1);
                resultArea.setText("Most Active Zombie: " + String.join(", ", mostViralPeople) + ", and " + lastPerson);
            } else {
                resultArea.setText("No most viral people found.");
            }
        });
        panel.add(button4);

        // Button for displaying most contacted people
        JButton button5 = new JButton("Most Contacted");
        button5.setBounds(300, 500, 200, 100);
        button5.setFont(buttonFont);
        button5.setBackground(buttonColor);
        button5.addActionListener(e -> {
            //Gets the most contacted people
            ArrayList<String> mostContactedPeople = getMostContactedPeople();
            if (mostContactedPeople.size() > 0) {
                //Removes the last person and connects the rest by commas using String.join and then adds the last person using and for grammer
                String lastPerson = mostContactedPeople.remove(mostContactedPeople.size() - 1);
                resultArea.setText("Tastiest Victims: " + String.join(", ", mostContactedPeople) + ", and " + lastPerson);
            } else {
                resultArea.setText("No most contacted people found.");
            }
        });
        panel.add(button5);

        // Button for displaying all contact records
        JButton button6 = new JButton("Show Contacts");
        button6.setBounds(550, 500, 200, 100);
        button6.setFont(buttonFont);
        button6.setBackground(buttonColor);
        button6.addActionListener(e -> {
            //Creates a string to store the contact records
            String contacts = "Contact Records:\n";
            //Goes through each sick person and gets their contacts
            for (String sickPeople : sickPeopleContacts.keySet()) {
                ArrayList<String> contactsList = sickPeopleContacts.get(sickPeople);
                String contactString = String.join(", ", contactsList);
                //Adds the sick person and their contacts to the contacts string
                contacts += sickPeople + " had contact with " + contactString + "\n";
            }
            resultArea.setText(contacts);
        });
        panel.add(button6);

        frame.setVisible(true);
    }
    
    /**
     * Retrieves the list of most contacted people.
     * @return An ArrayList of the most contacted people.
     */
    public ArrayList<String> getMostContactedPeople() {
        ArrayList<String> mostContactedPeople = new ArrayList<>();
        ArrayList<String> mostSickPeople = getMostViralPeople();
        
        // Goes through the most viral people and gets their contacts
        for (String sickPerson : mostSickPeople) {
            ArrayList<String> contacts = sickPeopleContacts.get(sickPerson);
            
            for (String contact : contacts) {
                // Makes sure duplicate contacts aren't added
                if (!mostContactedPeople.contains(contact)) {
                    mostContactedPeople.add(contact);
                }
            }
        }
        
        return mostContactedPeople;
    }

    /**
     * Retrieves the list of most viral people.
     * @return An ArrayList of the most viral people.
     */
    public ArrayList<String> getMostViralPeople() {
        ArrayList<String> mostViralPeople = new ArrayList<>();
        int maxContacts = 0;
    
        // Goes through each sick person and checks their contact size, and finds highest
        for (String sickPerson : sickPeopleContacts.keySet()) {
            ArrayList<String> contacts = sickPeopleContacts.get(sickPerson);
            int numContacts = contacts.size();
    
            // If they have the same or more contacts then person added to mostViralPeople arraylist
            if(numContacts > maxContacts) {
                mostViralPeople.clear(); 
                mostViralPeople.add(sickPerson);
                maxContacts = numContacts; 
            }else if (numContacts == maxContacts) {
                //If they have the same number of contacts then they are added to the mostViralPeople arraylist
                mostViralPeople.add(sickPerson);
            }
        }
    
        return mostViralPeople;
    }

    /**
     * Retrieves the list of people who are neither zombies nor patient zeros.
     * @return An ArrayList of people who are neither zombies nor patient zeros.
     */
    public ArrayList<String> getNeither() {
        ArrayList<String> allContacts = new ArrayList<>();
        ArrayList<String> sickPeople = new ArrayList<>();
        
        // Collect all sick people and their contacts
        for (String sickPerson : sickPeopleContacts.keySet()) {
            sickPeople.add(sickPerson);
            ArrayList<String> contacts = sickPeopleContacts.get(sickPerson);
            allContacts.addAll(contacts);
        }
        
        ArrayList<String> neither = new ArrayList<>();
        ArrayList<String> patientZero = getPatientZero();
        ArrayList<String> zombies = getZombies();
        
        // Add people who are not patient zero or zombies
        for (String person : sickPeople) {
            if (!patientZero.contains(person) && !zombies.contains(person) && !neither.contains(person)) {
                neither.add(person);
            }
        }
        
        for (String person : allContacts) {
            //If the person is not a patient zero or zombie, then they are added to neither
            if (!patientZero.contains(person) && !zombies.contains(person) && !neither.contains(person)) {
                neither.add(person);
            }
        }
        
        return neither;
    }
    
    /**
     * Gets all of the patient zeros from getting all of the contacts and then check if any sick people are
     * on the contacts list. 
     * @return An ArrayList of patient zeros.
     */
    public ArrayList<String> getPatientZero() {
        ArrayList<String> allContacts = new ArrayList<>();
        ArrayList<String> patientZero = new ArrayList<>();
        
        // Add all the contacts of sick people
        for (String sickPeople : sickPeopleContacts.keySet()) {
            ArrayList<String> contacts = sickPeopleContacts.get(sickPeople);
            allContacts.addAll(contacts);
        }
        
        // If the sick person is not a contact at all, then added to patientZero ArrayList
        for (String sickPeople : sickPeopleContacts.keySet()) {
            if (!allContacts.contains(sickPeople)) patientZero.add(sickPeople);
        }
        
        return patientZero;
    }
    
    /**
     * Retrieves the list of zombies.
     * @return An ArrayList of zombies.
     */
    public ArrayList<String> getZombies() {
        ArrayList<String> allContacts = new ArrayList<>();
        ArrayList<String> zombies = new ArrayList<>();
        
        // Add all the contacts of sick people
        for (String sickPeople : sickPeopleContacts.keySet()) {
            ArrayList<String> contacts = sickPeopleContacts.get(sickPeople);
            allContacts.addAll(contacts);
        }
        
        // If contacts is not a sick person, then they are added to zombies ArrayList
        for (String contact : allContacts) {
            if (!sickPeopleContacts.containsKey(contact) && !zombies.contains(contact)) {
                zombies.add(contact);
            }
        }
        
        return zombies;      
    }
        
    /**
     * Prints the contact records out by getting the sick people keyset and 
     * then adds all the contacts to a string and prints it out
     */
    public void printContacts(){
        System.out.println("\nContact Records:");
        for (String sickPeople : sickPeopleContacts.keySet()) {
            //Gets the contacts of the sick person
            ArrayList<String> contacts = sickPeopleContacts.get(sickPeople);
            String contactString = "";
            int count = 0;
            //Goes through each contact and adds it to the contactString
            for (String contactPeople : contacts) {
                count++;
                if (count == contacts.size()) {
                    contactString += "and " + contactPeople;
                } else {
                    contactString += contactPeople + ", ";
                }
            }
            System.out.println(sickPeople + " had contact with " + contactString);
        }
    }    
    
    /**
     * Populates the sickPeopleContacts map with data from user input throw joptionpane questions
     * and then writes it to the file. Then it reads it from the file and populates the map
     */
    public void populate() {
        int numberOfEntries = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of sick people:"));
        //Asks for the sick person and their contacts and writes it to the file and runs it based on number of sick people
        for (int i = 0; i < numberOfEntries; i++) {
            String sickPerson = JOptionPane.showInputDialog("Enter the name of sick person " + (i + 1) + ":");
            String contactsInput = JOptionPane.showInputDialog("Enter the contacts for " + sickPerson + " seprated by commas:");
            write(sickPerson + ":" + contactsInput);
        }
        //Closes the file
        close_file();
        //Reads the file and populates the map
        try {
            Scanner scanner = new Scanner(new File("sick_people_contacts.txt"));
            while (scanner.hasNextLine()) {
                //Reads the file line by line
                String line = scanner.nextLine();
                //Splits the line into the sick person and their contacts
                String[] items = line.split(":");
                String[] contacts = items[1].split(",");
                ArrayList<String> contactsList = new ArrayList<>();
                String sickPerson = items[0];
                //Goes through each contact and adds it to the contactsList
                for(int i = 0; i < contacts.length; i++){
                    contactsList.add(contacts[i]);
                }
                //Adds the sick person and their contacts to the map
                sickPeopleContacts.put(sickPerson, contactsList);
            }
            //Closes the scanner
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading from file!");
        }
    }
}