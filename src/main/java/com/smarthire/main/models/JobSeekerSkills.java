package com.smarthire.main.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOBSEEKER_SKILLS")
public class JobSeekerSkills implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="js_skills_id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long js_skills_id;
	
	@Column(name="username") 
	private String username;
	
	@Column(name="skill_name") 
	private String skill_name;
	
	@Column(name="testId")
	private Long testId;
	
	@Column(name="certificateId")
	private Long certificateId;
	
	public JobSeekerSkills(Long js_skills_id, String username, String skill_name, Long testId, Long certificateId) {
		super();
		this.js_skills_id = js_skills_id;
		this.username = username;
		this.skill_name = skill_name;
		this.testId = testId;
		this.certificateId = certificateId;
	}

	public Long getJs_skills_id() {
		return js_skills_id;
	}

	public void setJs_skills_id(Long js_skills_id) {
		this.js_skills_id = js_skills_id;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(Long certificateId) {
		this.certificateId = certificateId;
	}

	public JobSeekerSkills(String username, String skill_name) {
		super();
		this.username = username;
		this.skill_name = skill_name;
	}

	public JobSeekerSkills() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

}
