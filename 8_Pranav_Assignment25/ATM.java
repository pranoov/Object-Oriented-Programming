
/**
 * @author Pranav Gadde
 * @version (a version number or a date)
 */

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;  

public class ATM
{
    HashMap<Integer, ArrayList> accounts = new HashMap<>();
    
    /**
     * Main method creates a new instance of ATM class
     */
    public static void main(String[] args){
        ATM atm = new ATM();
    }
    
    
    /**
     * Constructor for objects of class ATM
     */
    public ATM()
    {   
        populate();
        System.out.println("Welcome to the ATM");
        boolean gameOver = false; 
        
        while(!gameOver){
            System.out.println("\nWould you like to try out the ATM? y/n: ");
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine().toLowerCase();
            if(!userAnswer.equals("y") && !userAnswer.equals("yes")){
                gameOver = true;  
                break;
            }else{
                gameOver = false;
            }
            scanner = new Scanner(System.in);
            System.out.println("\nChoose one of the following:");
            System.out.println("1. Deposit money");
            System.out.println("2. Withdraw money");
            System.out.println("3. Print transaction for this account");
            System.out.println("4. Print all ATM transactions");
            int choice = scanner.nextInt();
            //switch case handles all of the user choices
            switch(choice){
                case 1: 
                    System.out.println("\nEnter the account number you want to deposit in: ");
                    scanner = new Scanner(System.in);
                    int account = scanner.nextInt();
                    System.out.println("Enter the amount that you want to deposit: ");
                    scanner = new Scanner(System.in);
                    double amount = Double.valueOf(scanner.next());
                    deposit(account,amount);
                    System.out.println("Money deposited in the account");
                    break;
                case 2: 
                    System.out.println("\nEnter the account number you want to withdraw from: ");
                    scanner = new Scanner(System.in);
                    int account2 = scanner.nextInt();
                    System.out.println("Enter the amount that you want to withdraw: ");
                    scanner = new Scanner(System.in);
                    double amount2 = Double.valueOf(scanner.next());
                    withdraw(account2,amount2);
                    System.out.println("Money withdrawn from the account");
                    break;
                case 3: 
                    System.out.println("\nEnter the account number you want to get history for: ");
                    scanner = new Scanner(System.in);
                    int account3 = scanner.nextInt();
                    printTransactionHistory(account3);
                    break; 
                case 4: 
                    printAll();
                    break;
            }
            
            scanner.close();
        }
        System.out.println("\nThank you for using the ATM");
    }
    
    /**
     * Uses printTransactionHistory method to print all accounts and transactions
     */
    public void printAll(){
        for(int nums: accounts.keySet()){
            printTransactionHistory(nums);
        }
    }
    
    /**
     * Using an account number returns the transaction history and balance for that account
     */
    public void printTransactionHistory(int account){
        ArrayList<String> transactions = accounts.get(account);
        System.out.println("\nAccount: " + account);
        System.out.print("Transactions: ");
        int count = 0;
        for(String element: transactions){
            
            if(count == transactions.size() - 1){
                System.out.print("Balance: " + element);
            }else{
                System.out.print(element + " ");
            }
            count++;
        }
    }
    
    /**
     * Deposits money to account, and adds to transaction history for account
     */
    public void deposit(int account, double amount){
        ArrayList<String> transactions = accounts.get(account);
        double currentBalance = Double.valueOf(transactions.get(transactions.size() - 1)); 
        currentBalance += amount; 
        transactions.set(transactions.size() - 1, String.valueOf(currentBalance));
        transactions.add(transactions.size() - 1, String.valueOf(amount));
    }
    
    /**
     * Withdraws money from an account, and adds it to transaction history
     */
    public void withdraw(int account, double amount){
        ArrayList<String> transactions = accounts.get(account);
        double currentBalance = Double.valueOf(transactions.get(transactions.size() - 1)); 
        currentBalance -= amount; 
        transactions.set(transactions.size() - 1, String.valueOf(currentBalance));
        transactions.add(transactions.size() - 1, "-" + amount);
    }
    
    /**
     * Uses the file, to populate the accounts and their transactions history and balance to a HashMap
     */
    public void populate(){
        try{
            Scanner scanner = new Scanner(new File("account-transactions.txt"));
            while(scanner.hasNextLine()){
                String[] items = scanner.nextLine().split(" ");
                Integer accountNumber = Integer.parseInt(items[0]);
                ArrayList<String> transactions = new ArrayList<>();
                for(int i = 1; i < items.length; i++){
                    transactions.add(items[i]);
                }
                accounts.put(accountNumber, transactions);
            }
            
            scanner.close();
        }catch(Exception E){
        
        
        }
        System.out.println(accounts);
    }
    
    

}
