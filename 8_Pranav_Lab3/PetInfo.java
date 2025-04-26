
/**
 * Write a description of class PetInfo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class PetInfo
{    
    public static void main(String[] args){
        try{
            Scanner file = new Scanner(new File("data.txt"));
            
            while(file.hasNext()){
                String line = file.nextLine();
                String[] elements = line.split(" ");
                String[] favToys = elements[2].split(",");
                ArrayList<String> vacs = new ArrayList<>();
                for(int i = 3; i < elements.length; i++){
                    vacs.add(elements[i]);
                }
                Pet newPet = new Pet(elements[0],Integer.parseInt(elements[1]), favToys,vacs);
                newPet.printInfo();
            }
        }catch(IOException e){
            System.out.println("File not found!");
        }
    }

}
