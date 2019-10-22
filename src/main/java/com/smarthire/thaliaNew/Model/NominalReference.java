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
public class NominalReference {
    
    private int entityId;
    private int mentionId;
    private String type;
    private LinkedList<NounTokenData> nountoken;
    private String position;
    private String number;
    private boolean isMvReportingVerb;
    private LinkedList<String> lexnames;

    public NominalReference(int entityId, int mentionId, String type, LinkedList<NounTokenData> nountoken, 
            String position, String number, boolean isMvReportingVerb, LinkedList<String> lexnames) {
        
        this.entityId = entityId;
        this.mentionId = mentionId;
        this.type = type;
        this.nountoken = nountoken;
        this.position = position;
        this.number = number;
        this.isMvReportingVerb = isMvReportingVerb;
        this.lexnames = lexnames;
    }
    
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isIsMvReportingVerb() {
        return isMvReportingVerb;
    }

    public void setIsMvReportingVerb(boolean isMvReportingVerb) {
        this.isMvReportingVerb = isMvReportingVerb;
    }

    public LinkedList<NounTokenData> getNountoken() {
        return nountoken;
    }

    public void setNountoken(LinkedList<NounTokenData> nountoken) {
        this.nountoken = nountoken;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public LinkedList<String> getLexnames() {
        return lexnames;
    }

    public void setLexnames(LinkedList<String> lexnames) {
        this.lexnames = lexnames;
    }
    
}
