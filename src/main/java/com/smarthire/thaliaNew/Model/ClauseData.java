/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;


import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 *
 * @author Nathalia
 */
public class ClauseData {
    
	private String head;
    private String content;
    private String sentencePattern;
	private String mood;
    private String subMood;
    private String type;
    private String voice;
    private String aspect;
    private String tense;
	private int naturePolarity;
    private HashMap<String, PartPointer> parts;
	
	public ClauseData() {
		super();
	}
    
	public ClauseData(String head, String content, String sentencePattern, String mood, String subMood,
			String type, String voice, String aspect, String tense, int naturePolarity, HashMap<String, PartPointer> parts) {
		super();
		this.head = head;
		this.content = content;
		this.sentencePattern = sentencePattern;
		this.mood = mood;
		this.subMood = subMood;
		this.type = type;
		this.voice = voice;
		this.aspect = aspect;
		this.tense = tense;
		this.naturePolarity = naturePolarity;
		this.parts = parts;
	}
	
	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentencePattern() {
        return sentencePattern;
    }

    public void setSentencePattern(String sentencePattern) {
        this.sentencePattern = sentencePattern;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubMood() {
        return subMood;
    }

    public void setSubMood(String subMood) {
        this.subMood = subMood;
    }
    
    public String getTense() {
        return tense;
    }

    public void setTense(String tense) {
        this.tense = tense;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

	public int getNaturePolarity() {
		return naturePolarity;
	}

	public void setNaturePolarity(int naturePolarity) {
		this.naturePolarity = naturePolarity;
	}

	public HashMap<String, PartPointer> getParts() {
		return parts;
	}

	public void setParts(HashMap<String, PartPointer> parts) {
		this.parts = parts;
	}

	
    
}
