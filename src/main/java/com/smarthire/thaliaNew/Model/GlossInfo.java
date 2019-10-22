/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

import edu.mit.jwi.item.IWord;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

import com.smarthire.main.models.SocialMediaData;

/**
 *
 * @author Nathalia
 */
@Entity
@Table(name="GLOSS_INFO")
public class GlossInfo implements Serializable {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="wordSenseID")
    private String wordSenseID;
	
	@Column(name="definition")
    private String definition;

	@Column(name="lexname")
    private String lexname;
	
	@Column(name="isValidInCR")
	private boolean isValidInCR;
	
	@Column(name="isValidInWSD")
	private boolean isValidInWSD;
	
	//@ManyToOne
	//@JoinColumn(name="id", referencedColumnName="wordInfoId")
	//private WordInfo wordInfo;
	
	@Column(name="wordInfoId")
	private Long wordInfoId;
	
	
	@Transient
	private LinkedList<String> synonyms;
    
	public GlossInfo()
	{
		super();
	}
	
    public GlossInfo(String wordSenseID, String definition, LinkedList<String> synonyms, String lexname) {
        this.wordSenseID = wordSenseID;
        this.definition = definition;
        this.synonyms = synonyms;
        this.lexname = lexname;
    }
	
    public GlossInfo(String wordSenseID, String definition, String lexname) {
        this.wordSenseID = wordSenseID;
        this.definition = definition;
        this.lexname = lexname;
    }

    public String getWordSenseID() {
        return wordSenseID;
    }

    public void setWordSenseID(String wordSenseID) {
        this.wordSenseID = wordSenseID;
    }
    
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getLexname() {
        return lexname;
    }

    public void setLexname(String lexname) {
        this.lexname = lexname;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//public WordInfo getWordInfo() {
	//	return wordInfo;
	//}

	//public void setWordInfo(WordInfo wordInfo) {
	//	this.wordInfo = wordInfo;
	//}
	

	public LinkedList<String> getSynonyms() {
		return synonyms;
	}

	public Long getWordInfoId() {
		return wordInfoId;
	}

	public void setWordInfoId(Long wordInfoId) {
		this.wordInfoId = wordInfoId;
	}

	public void setSynonyms(LinkedList<String> synonyms) {
		this.synonyms = synonyms;
	}

	public boolean isValidInCR() {
		return isValidInCR;
	}

	public void setValidInCR(boolean isValidInCR) {
		this.isValidInCR = isValidInCR;
	}

	public boolean isValidInWSD() {
		return isValidInWSD;
	}

	public void setValidInWSD(boolean isValidInWSD) {
		this.isValidInWSD = isValidInWSD;
	}
    
    

}
