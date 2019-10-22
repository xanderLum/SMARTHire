/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author Zander Lumapac
 */
public class Synonyms {
    private  LinkedList<String> list;
    private  LinkedList<String> definitionList;
    private  String[] usage;
        
    public LinkedList<String> getSynonyms(String word) {
        list = new LinkedList<>();
        definitionList = new LinkedList<>();
//        System.setProperty("wordnet.database.dir", "./src/com/zander/dict/dict/");
        WordNetDatabase database = WordNetDatabase.getFileInstance();
        Synset[] synset = database.getSynsets(word);
        for(Synset i: synset){
            StringTokenizer st = new StringTokenizer(i.getDefinition(), ",");
            while(st.hasMoreElements()){
                String line = st.nextToken();
                definitionList.add(line);
            }
            //definitionList.add(i.getDefinition());
            usage = i.getUsageExamples();
            for(String x: i.getWordForms()){
                if(!isExisting(list, x) && !x.equals(word))
                    list.add(x);
            }
        }
        return list;
    }
    
    private boolean isExisting(LinkedList<String> list,String word){
        if (list.stream().anyMatch((i) -> (i.equals(word)))) {
            return true;
        }
        return false;
    }

    public LinkedList<String> getList() {
        return list;
    }

    public LinkedList<String> getDefinitionList() {
        return definitionList;
    }

    public String[] getUsage() {
        return usage;
    }
    
    
    
    
    
}
