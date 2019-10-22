/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

import java.io.Serializable;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 *
 * @author Nathalia
 */
@Entity
@Table(name="WORD_INFO")
public class WordInfo implements Serializable {
	

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="word")
    private String word;
	
	@Column(name="postag")
    private String postag;
	
    
	//@ManyToOne
	//@JoinColumn(name="data_id", referencedColumnName="socialMediaDataId")
	//private SocialMediaData socialMediaData;
	
	@Column(name="data_id")
    private String data_id;
	
	@Transient
	private LinkedList<GlossInfo> glossInfo;

	 public WordInfo()
	 {
		 super();
	 }
	
    public WordInfo(String word, String postag, LinkedList<GlossInfo> glossInfo) {
        this.word = word;
        this.postag = postag;
        this.glossInfo = glossInfo;
    }
	
    public WordInfo(String word, String postag) {
        this.word = word;
        this.postag = postag;
    }

	public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPostag() {
        return postag;
    }

    public void setPostag(String postag) {
        this.postag = postag;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//public SocialMediaData getSocialMediaData() {
	//	return socialMediaData;
	//}

	//public void setSocialMediaData(SocialMediaData socialMediaData) {
	//	this.socialMediaData = socialMediaData;
	//}

	public LinkedList<GlossInfo> getGlossInfo() {
		return glossInfo;
	}

	public void setGlossInfo(LinkedList<GlossInfo> glossInfo) {
		this.glossInfo = glossInfo;
	}

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}

    
    
}