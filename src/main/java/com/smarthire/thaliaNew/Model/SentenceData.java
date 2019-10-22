/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarthire.thaliaNew.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
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

import org.springframework.util.SocketUtils;

import com.smarthire.main.models.SocialMediaData;

/**
 *
 * @author Nathalia
 */
@Entity
@Table(name="SENTENCE_DATA")
public class SentenceData implements Serializable{
    
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="chunk")
	private String chunk;
	
	@Column(name="content")
    private String content;
	
	@Column(name="remnant")
    private String remnant;

	//@ManyToOne
	//@JoinColumn(name="data_id", referencedColumnName="socialMediaDataId")
	//private SocialMediaData socialMediaData;
	
	@Column(name="data_id")
	private String data_id;
	


	@Transient
    private HashMap<String, LinkedList<ClauseData>> clauseData;
	
	@Transient
    private HashMap<String, String> error;

	public SentenceData()
	{
		super();
	}
	
    public SentenceData(String chunk, String content, HashMap<String, LinkedList<ClauseData>> clauseData, HashMap<String, String> error, String remnant) {
        
    	this.chunk = chunk;
    	this.content = content;
        this.clauseData = clauseData;
        this.error = error;
        this.remnant = remnant;
    }
    
    public SentenceData(String content, String remnant) {
        this.content = content;
        this.remnant = remnant;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
    public String getRemnant() {
        return remnant;
    }

    public void setRemnant(String remnant) {
        this.remnant = remnant;
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

	public String getData_id() {
		return data_id;
	}

	public void setData_id(String data_id) {
		this.data_id = data_id;
	}
/*
	@Transient
	public LinkedList<String> getPhrase() {
		return (LinkedList<String>) Arrays.asList(chunk.split(" "));
	}
*/
	public HashMap<String, LinkedList<ClauseData>> getClauseData() {
		return clauseData;
	}

	public void setClauseData(HashMap<String, LinkedList<ClauseData>> clauseData) {
		this.clauseData = clauseData;
	}

	public HashMap<String, String> getError() {
		return error;
	}

	public void setError(HashMap<String, String> error) {
		this.error = error;
	}

	public String getChunk() {
		return chunk;
	}

	public void setChunk(String chunk) {
		this.chunk = chunk;
	}
    
    
  
    
}
