package com.smarthire.thaliaNew.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="GRAMMAR_ERROR")
public class GrammarError implements Serializable {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
    private String name;
	
	@Column(name="count")
    private int count;
	
	//@ManyToOne
	//@JoinColumn(name="id", referencedColumnName="sentenceDataId")
	//private SentenceData sentenceData;
	
	@Column(name="sentenceDataId")
	private Long sentenceDataId;

	public GrammarError()
	{
		super();
	}
	
	public GrammarError(String name, int count, Long sentenceDataId) {
		super();
		this.name = name;
		this.count = count;
		this.sentenceDataId = sentenceDataId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getSentenceDataId() {
		return sentenceDataId;
	}

	public void setSentenceDataId(Long sentenceDataId) {
		this.sentenceDataId = sentenceDataId;
	}

	//public SentenceData getSentenceData() {
	//	return sentenceData;
	//}

	//public void setSentenceData(SentenceData sentenceData) {
	//	this.sentenceData = sentenceData;
	//}

	
	
	
    // Ab = a faulty abbreviation
    // Agr = agreement problem: subject/verb or pronoun/antecedent
    // Awk = awkward expression or construction
    // Cap = faulty capitalization
    // CS = comma splice
    // DICT = faulty diction
    // Dgl = dangling construction
    // -ed = problem with final -ed
    // Frag = fragment
    // || = problem in parallel form
    // P/A = pronoun/antecedent agreement
    // Pron = problem with pronoun
    // Rep = unnecessary repetition
    // R-O = run-on sentence
    // Sp = spelling error
    // -s = problem with final -s
    // STET = let it stand
    // S/V = subject/verb agreement
    // T = verb tense problem
    // Wdy = wordy
    // WW = wrong word
	
	
        
}
