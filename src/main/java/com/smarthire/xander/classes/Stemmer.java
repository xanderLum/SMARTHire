/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smarthire.xander.classes;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Zander Lumapac
 */
public class Stemmer {
    private String word;
    
    public Stemmer() {
       
    }

    public Stemmer(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    
    public String stem(String word){
       // System.setProperty("wordnet.database.dir", "C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
       // System.setProperty("wordnet.database.dir", "D:\\1st Sem 15-16\\IR Projects\\Lumapac,Cecillee\\Midterm\\Excercise18\\src\\com\\zander\\dict\\dict\\");
//        System.setProperty("wordnet.database.dir", "./src/com/zander/dict/dict");
        WordNetDatabase database = WordNetDatabase.getFileInstance();
        NounSynset nounSynset;
        NounSynset[] hyponyms;
        
        Synset[] synsets = database.getSynsets(word, SynsetType.NOUN);
        for(int i=0; i<synsets.length; i++){
            nounSynset = (NounSynset)(synsets[i]);
            hyponyms = nounSynset.getHyponyms();
        }
        
         String[] baseformsV = database.getBaseFormCandidates(word, SynsetType.VERB);
         String[] baseformsN = database.getBaseFormCandidates(word, SynsetType.NOUN);
         String[] baseformsADJ = database.getBaseFormCandidates(word, SynsetType.ADJECTIVE);
         String[] baseformsADV = database.getBaseFormCandidates(word, SynsetType.ADVERB);
         
         List<String> allbase = new LinkedList<>();
         allbase.addAll(Arrays.asList(baseformsN));
         allbase.addAll(Arrays.asList(baseformsV));
         allbase.addAll(Arrays.asList(baseformsADJ));
         allbase.addAll(Arrays.asList(baseformsADV));
         
         
        if(allbase.size() >0){ 
         String min = allbase.get(0);
         for(int i = 1; i< allbase.size(); i++){
             if(allbase.get(i).length() < min.length()){
                 min = allbase.get(i);
             }
         }
         allbase.clear();
            return min;
        }else{
            allbase.clear();
            return word;
        }
        
        
    }
}
