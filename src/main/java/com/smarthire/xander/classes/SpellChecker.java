/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Zander Lumapac
 */
public class SpellChecker {
      public boolean checkSpelling(String word){
        FileReader fRead = null;
        BufferedReader buffer = null;
        String path = "";
        String readLine= "";
        
        try {
            int multi[][];
            int j = 0;
            int i = word.length();
            fRead = new FileReader(path);
            buffer = new BufferedReader(fRead);
            while(!(readLine = buffer.readLine()).isEmpty()){
               backTrace(word,readLine);
            }
                
        } catch (FileNotFoundException ex) {
            System.out.println("File not found exception");
        } catch (IOException ex) {
            System.out.println("IO Exception");
        } finally {
            try {
                fRead.close();
            } catch (IOException ex) {
                System.out.println("IO Exception at fRead.close()");
            }
        }
        return false;
    }
      
      private void backTrace(String fWord, String sWord){
        int i = fWord.length();
        int j = sWord.length();
        int multi[][]= new int[i+1][j+1];
        
        for(int x = 0; x<i+1 ; x++){
            multi[x][0] = x;
        }
        
        for(int x = 0; x<j+1 ; x++){
            multi[0][x] = x;
        }
        
        for(int x = 1; x<i+1; x++){
            for(int y = 1; y<j+1; y++){
                if(fWord.charAt(x-1) == sWord.charAt(y-1)){
                    multi[x][y] = multi[x-1][y-1];
                }else{
                    int min = 999999999;
                    if(min> (multi[x-1][y] +1)){
                        min = multi[x-1][y] +1;
                    }
                    if(min> (multi[x][y-1] +1)){
                        min = multi[x][y-1] +1;
                    }
                     if(min> multi[x-1][y-1]+2)
                         multi[x][y] = multi[x-1][y-1]+2;
                     else 
                         multi[x][y] = min;
                }
            }
        }
    
    }
   
}
