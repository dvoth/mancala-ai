/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancalaai;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        try {
            FileWriter fw = new FileWriter(dataOut,false);
            fw.write("1");
            fw.close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
    
}
