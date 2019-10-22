/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;

import java.util.LinkedList;
/**
 *
 * @author Zander Lumapac
 */
public class DataCleaner {
    public boolean hasMeaning(String word){
        Synonyms getSyn = new Synonyms();
        if(word.length() == 1 && !word.equalsIgnoreCase("i")){
            return false;
        }
        
        LinkedList<String> syns = getSyn.getSynonyms(word);
        
        return !syns.isEmpty();
    }
}
