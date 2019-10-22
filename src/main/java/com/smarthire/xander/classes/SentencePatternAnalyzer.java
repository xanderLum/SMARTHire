/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;
import com.smarthire.xander.interfaces.SentencePatternRules;
import com.smarthire.xander.interfaces.UseOfVerbsRules;

import java.util.LinkedList;

/**
 *
 * @author Zander Lumapac
 */
public class SentencePatternAnalyzer implements SentencePatternRules, UseOfVerbsRules{

    public SentencePatternAnalyzer() {
    }
    
    public void analyze(LinkedList<String> listOfTags){
        LinkedList<Integer> listOfIndeces;
        listOfIndeces = new LinkedList<>();
        int index;
        for(String s: listOfTags){
            if((s.equals("VB") || s.equals("VBZ") || s.equals("VBP"))){
                index = listOfTags.indexOf(s);
                listOfIndeces.add(index);
            }
        }
        
        System.out.println("indeces point to chunk");
        for(int x: listOfIndeces){
            System.out.println("\t"+x);
        }
    }
    
    public void getSubject(LinkedList<Integer> list){
        if(list.isEmpty()){
            System.out.println("Plain phrase.");
        }else{
            for(int x: list){
                if(x!=0){
                    
                }
            }
        }
    }
    
    
    public void getPredicate(){
        
    }
    
    
}
