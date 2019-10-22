/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author Nathalia
 */

public class PronunciationProcess {
    
    private HashMap<String, String> pronunciationList;
    //String path = "C:\\Users\\Nathalia\\Desktop\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew";
    String path = "D:\\THESIS TO GO MAN\\Final Integration\\SMARTHire\\src\\main\\java\\com\\smarthire\\thaliaNew";
	
	
    public PronunciationProcess()
    {
        pronunciationList = new HashMap();
        FileReader fr;
        try {
            fr = new FileReader(path+"\\dat\\cmudict_SPHINX_40.txt");
        
            BufferedReader br = new BufferedReader(fr);
            String input_string;
            while((input_string = br.readLine())!=null)
            {
                String temp[] = input_string.trim().split("\t");
                pronunciationList.put(temp[0], temp[1]);
                
            }
        } catch (Exception ex) {
            
        }
    }
    
    public String getPronunciation(String input)
    {
        System.out.println(">>INPUT-->"+input);
        String output;
        if((output = pronunciationList.get(input.trim().toUpperCase()))!=null)
        {
            return output;
        }
        else
        {
            return input;
        }
        
    }
    
    public boolean isPronunciationStartsWithVowel(String input)
    {
        String result = getPronunciation(input).toLowerCase().trim();
        if(result.startsWith("a")||result.startsWith("e")||result.startsWith("i")
                ||result.startsWith("o")||result.startsWith("u"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
}
