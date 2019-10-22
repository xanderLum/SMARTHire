package com.smarthire.xander.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="JOB_POST_SKILLS")
public class JobPostSkills implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="job_post_id")
	private Long job_post_id;
	
	@Column(name="skill_name")
	private String skill_name;

	public JobPostSkills() {
		super();
	}
	
	public JobPostSkills(Long id, Long job_post_id, String skill_name) {
		super();
		this.id = id;
		this.job_post_id = job_post_id;
		this.skill_name = skill_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJob_post_id() {
		return job_post_id;
	}

	public void setJob_post_id(Long job_post_id) {
		this.job_post_id = job_post_id;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	
	
}
