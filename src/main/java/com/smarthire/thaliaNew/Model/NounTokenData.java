/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

import java.util.LinkedList;

/**
 *
 * @author Nathalia
 */
public class NounTokenData {
    
    private int entityId;
    private int mentionId;
    private LinkedList<String> modifiers;
    private String headNoun;
    private String type; //proper noun, common noun or pronoun
    private String subtype; //possessive or personal
    private String number;
    private LinkedList<String> lexnames;
    private double feminine;
    private double masculine;

    public NounTokenData(int entityId, int mentionId, LinkedList<String> modifiers, String headNoun, String type, String subtype, String number, LinkedList<String> lexnames, double feminine, double masculine) {
        
        this.lexnames = lexnames;
        this.entityId = entityId;
        this.mentionId = mentionId;
        this.modifiers = modifiers;
        this.headNoun = headNoun;
        this.type = type;
        this.subtype = subtype;
        this.number = number;
        this.feminine = feminine;
        this.masculine = masculine;
    }

    public LinkedList<String> getLexnames() {
        return lexnames;
    }

    public void setLexnames(LinkedList<String> lexnames) {
        this.lexnames = lexnames;
    }
    
    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getMentionId() {
        return mentionId;
    }

    public void setMentionId(int mentionId) {
        this.mentionId = mentionId;
    }

    public LinkedList<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(LinkedList<String> modifiers) {
        this.modifiers = modifiers;
    }

    public String getHeadNoun() {
        return headNoun;
    }

    public void setHeadNoun(String headNoun) {
        this.headNoun = headNoun;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getFeminine() {
        return feminine;
    }

    public void setFeminine(double feminine) {
        this.feminine = feminine;
    }

    public double getMasculine() {
        return masculine;
    }

    public void setMasculine(double masculine) {
        this.masculine = masculine;
    }

    
}
