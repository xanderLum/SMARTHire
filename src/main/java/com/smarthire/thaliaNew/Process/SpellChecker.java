/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Process;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Nathalia
 */
public class SpellChecker {
    
    static HashMap<String, LinkedList<String>> terms;
    //C:\\Users\\Nathalia\\Desktop\\
    public void init()
    {
        terms = new HashMap();
        
        terms.put("noun", getDataFromFile(System.getProperty("user.dir") + "\\src\\data\\noun-terms.txt"));
        terms.put("verb", getDataFromFile(System.getProperty("user.dir") + "\\src\\data\\verb-terms.txt"));
        terms.put("adjective", getDataFromFile(System.getProperty("user.dir") + "\\src\\data\\adjective-terms.txt"));
        terms.put("adverb", getDataFromFile(System.getProperty("user.dir") + "\\src\\data\\adverb-terms.txt"));
        
    }

    public LinkedList<String> getDataFromFile(String path)
    {
        long startTime = System.nanoTime();
        LinkedList<String> result = new LinkedList();
        FileReader fr;
        try {
            fr = new FileReader(path);
        
            BufferedReader br = new BufferedReader(fr);
            String input_string;
            while((input_string = br.readLine())!=null)
            {
                if(!result.contains(input_string.trim()))
                {
                    result.addAll(Arrays.asList(input_string.trim().split("\t")));
                }
            }
        } catch (Exception ex) {
        }
        
        //code
        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime) + " ns"); 
        return result;
    }
	
    private String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
    private final String[] satiric_misspelling = {"(a|\\Q@\\E|4)",
        "(b|6|v|\\Q|3\\E|8)",
        "(c|k|s|\\Q(\\E|\\Q[\\E|\\Q<\\E|¢)",
        "(d)",
        "(e|i|3|€)",
        "(f|p)",
        "(g|j|9)",
        "(h|\\Q#\\E)",
        "(i|\\Q!\\E|1|\\Q|\\E)",
        "(j|\\Q;\\E)",
        "(k)",
        "(l|1|£|\\Q|\\E)",
        "(m)",
        "(n)",
        "(o|0|@)",
        "(p)",
        "(q|k)",
        "(r)",
        "(s|z|\\Q$\\E|5)",
        "(t|\\Q+\\E|7)",
        "(u)",
        "(v|b)",
        "(w)",
        "(x|\\Q%\\E)",
        "(y|¥)",
        "(z|Z|S|s)"
            
    };
    
    private HashMap<String, String> keyboardMatrixVal = new HashMap<String, String>() {{
        
        put("q","0 0");
        put("w","0 1");
        put("e","0 2");
        put("r","0 3");
        put("t","0 4");
        put("y","0 5");
        put("u","0 6");
        put("i","0 7");
        put("o","0 8");
        put("p","0 9");
        put("a","1 0");
        put("s","1 1");
        put("d","1 2");
        put("f","1 3");
        put("g","1 4");
        put("h","1 5");
        put("j","1 6");
        put("k","1 7");
        put("l","1 8");
        put("z","2 0");
        put("x","2 1");
        put("c","2 2");
        put("v","2 3");
        put("b","2 4");
        put("n","2 5");
        put("m","2 6");
        
    }};        
    
    public boolean areCharactersIdentical(String s1, String s2)
    {
        s1=s1.toLowerCase();
        s2=s2.toLowerCase();
        StringEvaluator streval = new StringEvaluator();
        if(streval.isValidAccToRule(s1, "[a-z]"))
        {
            return streval.isValidAccToRule(s2, satiric_misspelling[s1.charAt(0)-97]);
        }
        else if(streval.isValidAccToRule(s2, "[a-z]"))
        {
            return streval.isValidAccToRule(s1, satiric_misspelling[s2.charAt(0)-97]);
        }
        return false;
    }
    
    public double getKeyboardDistance(char c1, char c2)
    {
        try{
        String[] v1 = keyboardMatrixVal.get(""+c1).toLowerCase().trim().split(" ");
        String[] v2 = keyboardMatrixVal.get(""+c2).toLowerCase().trim().split(" ");
        boolean flag = false;
        return Math.sqrt(Math.pow((Integer.parseInt(v2[0])-Integer.parseInt(v1[0])),2) + Math.pow((Integer.parseInt(v2[1])-Integer.parseInt(v1[1])),2));
        }catch(Exception e){
        }
        return -1;
    }
    
    public double WeightedLevenshtein(String b1, String b2) {
        //b1 = b1.toUpperCase();
        //b2 = b2.toUpperCase();

        DataCleaner dc = new DataCleaner(new KeySearchProcess());
        b1 = dc.removeRepeatedCharacters(b1.toLowerCase(), 1).toLowerCase();
        b2 = dc.removeRepeatedCharacters(b2.toLowerCase(), 1).toLowerCase();
        //b1 = b1.toLowerCase();
        //b2 = b2.toLowerCase();
        
        double[][] matrix = new double[b1.length()+ 1][b2.length() + 1];

        for (int i = 1; i <= b1.length(); i++) {
            matrix[i][0] = i;
        }

        for (int i = 1; i <= b2.length(); i++) {
            matrix[0][i] = i;
        }

        for (int i = 1; i <= b1.length(); i++) {
            for (int j = 1; j <= b2.length(); j++) {
                double distance_replace = matrix[i-1][j-1];
                if (b1.charAt(i-1) != b2.charAt(j-1)) {
                    // Cost of replace
                    if(!areCharactersIdentical(""+b1.charAt(i-1), ""+b2.charAt(j-1)))
                    {
                        double temp;
                        if((temp = getKeyboardDistance(b1.charAt(i-1), b2.charAt(j-1)))!=-1)
                        {
                            distance_replace+=temp;
                        }
                        else
                        {
                            distance_replace=Math.max(b1.length(), b2.length());
                        }
                        
                    }
                    else
                    {
                        distance_replace+=0.1;
                    }
//                    distance_replace += Math.abs((float)(b1.charAt(i-1)) - b2.charAt(j-1)) / ('Z'-'A');
                }
                // Cost of remove = 1 
                double distance_remove = matrix[i-1][j] + 1;
                // Cost of add = 1
                double distance_add = matrix[i][j-1] + 1;

                matrix[i][j] = Math.min(distance_replace, 
                                    Math.min(distance_add, distance_remove));
            }
        }

        return matrix[b1.length()][b2.length()] ;
    }
    
    public String processWithPostag(String input, String postag)
    {
        LinkedList<String> basisList = new LinkedList();
        KeySearchProcess ksp = new KeySearchProcess();
        basisList.addAll(ksp.getStopWords());
        //for(String key : terms.keySet())
        //{
        //    basisList.addAll(terms.get(key));
        //}
        basisList.addAll(terms.get(postag.trim()));
        return process(input, basisList);
    }

    public String process(String input, LinkedList<String> basisList)
    {
        init();
        long startTime = System.nanoTime();
        double min = -1;
        String output="";
        System.out.println("start...");
        for(String t : basisList)
        {
            System.out.println("term sub==>"+t);
            double distance = WeightedLevenshtein(t,input);
            System.out.println("==>"+distance);
            if(min==-1 || min>distance)
            {
                min=distance;
                output = t;
            }
            if(min==0)
            {
                break;
            }
        }
        System.out.println("OUTPUT===>"+output);
        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime) + " ns"); 
        return output;
    }
}
