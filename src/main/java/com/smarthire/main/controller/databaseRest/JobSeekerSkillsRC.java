package com.smarthire.main.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.main.service.JobSeekerSkillsService;

@RestController
@RequestMapping(value="/jobSeekerSkills")
public class JobSeekerSkillsRC {
	
	@Autowired
	JobSeekerSkillsService jobSeekerSkillsService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<JobSeekerSkills> create(@RequestBody JobSeekerSkills jobSeekerSkills){
		return jobSeekerSkillsService.create(jobSeekerSkills);		
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<JobSeekerSkills> update(@RequestBody JobSeekerSkills jobSeekerSkills){
		return jobSeekerSkillsService.update(jobSeekerSkills);		
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<JobSeekerSkills> read(@PathVariable Long id){
		System.out.println("Here read js skills Rest");
		return jobSeekerSkillsService.read(id);		
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<JobSeekerSkills> delete(@PathVariable Long id){
		return jobSeekerSkillsService.delete(id);		
	}
	
	@RequestMapping(value="/checkSkillUser/{username}/{skill_name}/",method=RequestMethod.GET)
	public ResponseEntity<JobSeekerSkills> read(@PathVariable String username, @PathVariable String skill_name){
		System.out.println("Here getAllSkillsUser js skills Rest");
		return jobSeekerSkillsService.checkSkillUser(username, skill_name);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<JobSeekerSkills>> getList(){
		System.out.println("Here getList js skills Rest");
		return jobSeekerSkillsService.getList();		
	}	
	
	@RequestMapping(value="/getAllSkillsByUsername/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<JobSeekerSkills>> getPostByEmployer(@PathVariable String username){
		return jobSeekerSkillsService.getAllSkillsByUsername(username);
	}
}
