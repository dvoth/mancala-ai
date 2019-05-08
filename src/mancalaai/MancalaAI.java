/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancalaai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dvoth
 */
public class MancalaAI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fname = "move.txt";
        File dataOut = new File(fname);
        printGameBoard();
        int move = firstLegalMove();
        try {
            FileWriter fw = new FileWriter(dataOut,false);
            fw.write(Integer.toString(move));
            fw.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
    public static int firstLegalMove() {
        Scanner lineScan;
        String gameboardFileName = System.getProperty("user.dir") + "/gameboard.txt";
        int currentPlayer = -1;
        int move = -1;
        File file = new File(gameboardFileName);
        int pitNumber = -1;
        writeToConsole(file.getPath() + "\n");
        
        try {
            lineScan = new Scanner(file);
            // Read through lines of file
            if (lineScan.hasNextLine()){
               currentPlayer = Integer.parseInt(lineScan.nextLine());
               writeToConsole("FROM AI: Current Player: " + currentPlayer + "\n");
            }
            
            while (lineScan.hasNextLine()) {
                pitNumber++;
                int stonesInPit = Integer.parseInt(lineScan.nextLine());
                writeToConsole("Stones in pit " + pitNumber + ": " + stonesInPit + "\n");
                // Don't choose an empty pit
                if (stonesInPit == 0)
                {
                    writeToConsole("FROM AI: Pit " + pitNumber + " is an Empty Pit\n");
                    continue;
                }
                // Don't choose other player's pit
                else if(currentPlayer == 0 && (pitNumber>6 || pitNumber <1))
                {
                    writeToConsole("FROM AI: Pit " + pitNumber  + " is illegal for " + currentPlayer + " \n");
                    continue;
                }
                // Don't choose other player's pit
                else if(currentPlayer ==1 && (pitNumber>13 || pitNumber <8))
                {
                    writeToConsole("FROM AI: Pit " + pitNumber  + " is illegal for " + currentPlayer + " \n");
                    continue;
                }
                // Yay we found one that works!
                else
                {
                    writeToConsole("FROM AI: Pit " + pitNumber  + " is legal for " + currentPlayer + " \n");
                    move = pitNumber;
                    break;
                }
            }
            
            lineScan.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        return move;
    }
    
    public static void printGameBoard() {
        Scanner lineScan;
        String fName = "gameboard.txt";
        String line = "";
        File file = new File(fName);
        
        try {
            lineScan = new Scanner(file);
            // Read through lines of file
            while (lineScan.hasNextLine()){
                line = lineScan.nextLine();
                writeToConsole(line + " ");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void writeToConsole(String message) {
        String consoleFileName = System.getProperty("user.dir") + "/console.txt";
        File dataOut = new File(consoleFileName);
        try {
            FileWriter fw = new FileWriter(dataOut,true);
            fw.write(message);
            fw.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
