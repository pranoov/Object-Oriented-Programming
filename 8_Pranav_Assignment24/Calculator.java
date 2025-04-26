
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import javax.swing.JOptionPane;

public class Calculator {
    
    Button[] buttons = new Button[16];
    String[] buttonLabels = {"+","1","2","3","-","4","5","6","*","7","8","9","/","0","C","="};
    String equation = "";
    int runtime = 0;
    Frame frame; 
    Label title;
    private FileOutputStream out;
    private PrintStream ps;
    
    /**
     * This is the main method which creates and instance of Calculator class.
     */
    public static void main(String[] args) throws ScriptException
    {
        Calculator calculator = new Calculator();
    }
    
    /**
     * Calls for the GUI method to draw the calculator
     */
    public Calculator(){
        calculatorGUI();
        try{
            out = new FileOutputStream("summary.txt");
            ps = new PrintStream(out);
        }catch(Exception e){
            System.out.println("Error opening the file");
        }
    }
    
    /**
     * Evaluates the equation string using order of operations doing one operation at the time and then 
     * returning the final answer. It also takes into account for invalid equations and dividing by 0
     * error. 
     */
    public void eval(){
        double total = 0;
        boolean undefined = false;
        String originalEquation = equation;
        //Checks if the equation ends in a operator meaning that its not a valid equation
        ArrayList<String> pieces = new ArrayList<>(Arrays.asList(equation.split(" ")));
        if(!Character.isDigit(pieces.get(pieces.size() - 1).charAt(0))){
            JOptionPane.showMessageDialog(null, "Not a valid equation!", "GUI Calculator", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(runtime == 0) pieces.remove(0);
        
        //simplifies the equation until it become a single answer
        while(pieces.size() > 1){
            String highestOperator = "";
            //first find the highest operator for order of operations
            for(String elements: pieces){
                switch(elements){
                    case "+":
                        if(highestOperator == "") highestOperator = "+";
                        break;
                    case "-":
                        if(highestOperator == "") highestOperator = "-";
                        break;
                    case "*":
                        if(highestOperator == "" || highestOperator == "+" || highestOperator == "-") highestOperator = "*";
                        break;
                    case "/":
                        if(highestOperator == "" || highestOperator == "+" || highestOperator == "-") highestOperator = "/";
                        break;
                }
            }
            int index = pieces.indexOf(highestOperator);
            double newNum = 0;
            //then it does the operation and simplifies the equation array
            switch(highestOperator){
                case "+":
                    newNum = Double.parseDouble(pieces.get(index - 1)) + Double.parseDouble(pieces.get(index + 1));
                    break;
                case "-":
                    newNum = Double.parseDouble(pieces.get(index - 1)) - Double.parseDouble(pieces.get(index + 1));
                    break;
                case "*":
                    newNum = Double.parseDouble(pieces.get(index - 1)) * Double.parseDouble(pieces.get(index + 1));
                    break;
                case "/":
                    if(Double.parseDouble(pieces.get(index + 1)) == 0){
                       undefined = true; 
                    }else{
                        newNum = Double.parseDouble(pieces.get(index - 1)) / Double.parseDouble(pieces.get(index + 1));
                    }
                    
                    break;
            }
            if(!undefined){
                pieces.remove(index - 1);
                pieces.remove(index - 1);
                
                pieces.set(index - 1, String.valueOf(newNum));
            }else{
                break;
            }
            
        }
        if(!undefined){
            equation = pieces.get(0);
            title.setText(equation + " ");
            frame.repaint();
            runtime++;
            write(originalEquation + " = " + equation);
            JOptionPane.showMessageDialog(null, "Equation Was Saved To File.", "GUI Calculator", JOptionPane.INFORMATION_MESSAGE);                          
        }else{
            equation = "";
            title.setText("Undefined ");
            frame.repaint();
            runtime = 0;
        }
        
    }
    
    /**
     * Makes the GUI for the actual calculator, with all the buttons interface color, etc.
     */
    public void calculatorGUI(){
        //learned adding buttons to a frame from geeksforgeeks.org java awt button
        // Create a frame
        frame = new Frame("Calculator");
        for(int i = 0; i < 16; i++){
            Button button = new Button(buttonLabels[i]);
            
            int verticalCount = 0;
            int horizontalCount = i;
            while(horizontalCount > 3){
                horizontalCount -= 4;
                verticalCount++;
            }
            button.setBounds(17 + (118 * horizontalCount), 200 + (110 * verticalCount), 110, 100);
            button.setBackground(new Color(255, 111, 97));
            button.setFont(new Font("monospaced", Font.BOLD, 40));
            String buttonLabel = buttonLabels[i];
            //also from geeksforgeeks allows to listen for button click, then this method is called
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event) {
                    displayEquation(buttonLabel);
                }
            }); 
            buttons[i] = button;
            // Add the button to the frame
            frame.add(button);
        }
        // Set the frame size and layout
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setBackground(new Color(45, 85, 90));
        // Set the frame visibility to true
        frame.setVisible(true);
        title = new Label(equation);
        //Sets the positions of the text of the calculator
        title.setBounds(18, 70, 464, 80); 
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setBackground(new Color(255, 255, 204));
        //sets the text of calculator to the right, so it looks like a normal calculator
        title.setAlignment(Label.RIGHT);
        
        frame.add(title);
    }
    
    
    /**
     * Handles all the methods to happen when a button is pressed in the calculator GUI
     */
    public void displayEquation(String label){
        if(label == "C")clear();
        if(label == "=")eval();
        boolean twoOperators = false;
        //Adds the numbers and operators to the equation to the calculator 
        if(label != "C" && label != "="){
            if(!Character.isDigit(label.charAt(0)) && equation.equals("")) return;
            if(!Character.isDigit(label.charAt(0)) && !Character.isDigit(equation.charAt(equation.length() - 1))) return;
            if(!twoOperators){
                if(!equation.equals("") && Character.isDigit(equation.charAt(equation.length() - 1)) && Character.isDigit(label.charAt(0))){
                    equation += label;
                }else{
                    equation += " " + label;
                }
                
                title.setText(equation + " ");
                frame.repaint();
            }
        }
    }
    
    /**
     * Clears the equation from the calculator
     */
    public void clear(){
        equation = "";
        runtime = 0;
        title.setText(equation);
        frame.repaint();
    }
    
    /**
     * Writes the equation to the file
     * 
     * @parameter s: The equation that should be written to the file
     */
    public void write(String s){
        try{
            ps.println(s);
        }catch(Exception e){
            System.out.println("Error while writing to the file");
        }
    }
    
    /**
     * Closes the file writer 
     */
    public void close()
    {
        try{
            ps.close();
        }catch(Exception e){
            System.out.println("Error closing the file");
        }
    }

    
}