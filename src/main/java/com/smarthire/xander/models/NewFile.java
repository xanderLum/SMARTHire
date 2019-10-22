/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.xander.models;

import java.util.LinkedList;

import com.smarthire.xander.rankTools.TokenObject;

/**
 *
 * @author jcselec4a27
 */
public class NewFile {
    
    private int definitionIndexNo;
    private LinkedList<TokenObject> tokens;

    public int getDefinitionIndexNo() {
        return definitionIndexNo;
    }

    public void setDefinitionIndexNo(int definitionIndexNo) {
        this.definitionIndexNo = definitionIndexNo;
    }

 

    public LinkedList<TokenObject> getTokens() {
        return tokens;
    }

    public void setTokens(LinkedList<TokenObject> tokens) {
        this.tokens = tokens;
    }
    
}
