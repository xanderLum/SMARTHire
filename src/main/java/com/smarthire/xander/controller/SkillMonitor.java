package com.smarthire.xander.controller;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class SkillMonitor {

	private LinkedList<String> jobPostRequiredSkill;
	private LinkedList<String> jobseekerSkills;
	private double skill_score;

	private LinkedList<String> matchedJsSkills;

	public LinkedList<String> getJobPostRequiredSkill() {
		return jobPostRequiredSkill;
	}

	public void setJobPostRequiredSkill(LinkedList<String> jobPostRequiredSkill) {
		this.jobPostRequiredSkill = jobPostRequiredSkill;
	}

	public LinkedList<String> getJobseekerSkills() {
		return jobseekerSkills;
	}

	public void setJobseekerSkills(LinkedList<String> jobseekerSkills) {
		this.jobseekerSkills = jobseekerSkills;
	}

	public double getSkill_score() {
		return skill_score;
	}

	public void setSkill_score(double skill_score) {
		this.skill_score = skill_score;
	}

	public SkillMonitor(LinkedList<String> jobseekerSkills, LinkedList<String> jobPostRequiredSkill) {
		System.out.println("SEEKER SKILLS");
		for (String i : jobseekerSkills){
			System.err.println("-------->" + i);
		}
		System.out.println("SIZE = "+jobseekerSkills.size());
		System.out.println("REQUIRED SKILLS");
		for(String i: jobPostRequiredSkill){
			System.err.println("-------->"+i);
		}
		System.out.println("SIZE = "+jobPostRequiredSkill.size());
		this.jobseekerSkills = jobseekerSkills;
		this.jobPostRequiredSkill = jobPostRequiredSkill;
		this.skill_score = 0.0;
	}

	public boolean isFound(String skill_name) {
		for (String skill : jobPostRequiredSkill) {
			if (skill.equalsIgnoreCase(skill_name)) {
				return true;
			}
		}
		return false;
	}

	public double computeSkillScore() {
		DecimalFormat df2 = new DecimalFormat(".##");
		System.out.println("jobSeekerSkills = " + jobseekerSkills.size());
		double score = 0.0;
		matchedJsSkills = new LinkedList<>();
		for (String skill : jobseekerSkills) {
			if (isFound(skill)) {
				matchedJsSkills.add(skill);
				System.out.println("match found!");
				skill_score += 1.0;
			}
		}

		score = (double) this.skill_score / jobPostRequiredSkill.size();
		System.out.println("score  = " + (double) this.skill_score / jobPostRequiredSkill.size());
		score = ((double) Math.round(score * 100) / 100) * 100;
		score = Double.parseDouble(df2.format(score));
		return score;
	}

	public SkillMonitor() {
		super();
	}

	public LinkedList<String> getMatchedJsSkills() {
		return matchedJsSkills;
	}

	public void setMatchedJsSkills(LinkedList<String> matchedJsSkills) {
		this.matchedJsSkills = matchedJsSkills;
	}

}
