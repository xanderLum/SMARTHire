package com.smarthire.thaliaNew.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smarthire.main.models.SocialMediaData;

@Entity
@Table(name="BACKEND_PROCESS_STATUS")
public class BackendProcessStatus implements Serializable {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="isChunkingDone")
	private boolean isChunkingDone;
	
	@Column(name="isCoreferenceResolutionDone")
	private boolean isCoreferenceResolutionDone;
	
	@Column(name="isWordSenseDisambiguationDone")
	private boolean isWordSenseDisambiguationDone;

	//@OneToOne
	//@JoinColumn(name="data_id", referencedColumnName="socialMediaDataId")
	//private SocialMediaData socialMediaData;

	//@OneToOne(fetch=FetchType.LAZY, mappedBy="address")
	//private SocialMediaData socialMediaData;

	@Column(name="data_id")
	private String data_id;
	
	

	public BackendProcessStatus() {
		super();
	}


	public BackendProcessStatus(boolean isChunkingDone, boolean isCoreferenceResolutionDone, boolean isWordSenseDisambiguationDone, String data_id) {
		super();
		this.isChunkingDone = isChunkingDone;
		this.isCoreferenceResolutionDone = isCoreferenceResolutionDone;
		this.isWordSenseDisambiguationDone = isWordSenseDisambiguationDone;
		this.data_id = data_id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public boolean isChunkingDone() {
		return isChunkingDone;
	}


	public void setChunkingDone(boolean isChunkingDone) {
		this.isChunkingDone = isChunkingDone;
	}


	public boolean isCoreferenceResolutionDone() {
		return isCoreferenceResolutionDone;
	}


	public void setCoreferenceResolutionDone(boolean isCoreferenceResolutionDone) {
		this.isCoreferenceResolutionDone = isCoreferenceResolutionDone;
	}


	public boolean isWordSenseDisambiguationDone() {
		return isWordSenseDisambiguationDone;
	}


	public void setWordSenseDisambiguationDone(boolean isWordSenseDisambiguationDone) {
		this.isWordSenseDisambiguationDone = isWordSenseDisambiguationDone;
	}


	public String getData_id() {
		return data_id;
	}


	public void setData_id(String data_id) {
		this.data_id = data_id;
	}


	
	
	//public SocialMediaData getSocialMediaData() {
	//	return socialMediaData;
	//}


	//public void setSocialMediaData(SocialMediaData socialMediaData) {
	//	this.socialMediaData = socialMediaData;
	//}
	
	

}
