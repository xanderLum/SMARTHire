package com.smarthire.thaliaNew.Process;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jcselec4a13
 */
public class TokenCount {
    
    //String definition;
    int count;
    String word;

//    public TokenCount(String definition, int count, String word) {
//        this.definition = definition;
//        this.count = count;
//        this.word = word;
//    }
    
    public TokenCount(int count, String word) {
        this.count = count;
        this.word = word;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

//    public String getDefinition() {
//        return definition;
//    }
//
//    public void setDefinition(String definition) {
//        this.definition = definition;
//    }
    
}
