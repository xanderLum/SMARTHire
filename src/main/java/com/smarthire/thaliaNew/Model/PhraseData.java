/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 *
 * @author Nathalia
 */
public class PhraseData {
	
    private String type;
    private String headTag;
	private LinkedList<String> equation; 

	public PhraseData()
	{
		super();
	}
	
    public PhraseData(String type, String headTag, LinkedList<String> equation) {
        this.type = type;
        this.headTag = headTag;
        this.equation = equation;
    }

    public PhraseData(String type, String headTag) {
        this.type = type;
        this.headTag = headTag;
    }
    
    public String getHeadTag() {
        return headTag;
    }

    public void setHeadTag(String headTag) {
        this.headTag = headTag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public LinkedList<String> getEquation() {
		
        return equation;
	}

	public void setEquation(LinkedList<String> equation) {
		this.equation = equation;
	}
    
    
}
