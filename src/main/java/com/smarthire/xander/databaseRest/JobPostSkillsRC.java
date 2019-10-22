package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.JobPostSkills;
import com.smarthire.xander.service.JobPostSkillsService;

@RestController
@RequestMapping(value="/jobPostSkillsRC")
public class JobPostSkillsRC {

	@Autowired
	JobPostSkillsService jobPostSkillsService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<JobPostSkills> create(@RequestBody JobPostSkills jps){	
		return jobPostSkillsService.create(jps);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<JobPostSkills> update(@RequestBody JobPostSkills jps){
		return jobPostSkillsService.update(jps);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<JobPostSkills> read(@PathVariable Long id){
		return jobPostSkillsService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<JobPostSkills> delete(@PathVariable Long id){
		return jobPostSkillsService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<JobPostSkills>> getList(){
		return jobPostSkillsService.getList();
	}
	
	@RequestMapping(value="/getListById/{id}/",method=RequestMethod.GET)
	public ResponseEntity<List<JobPostSkills>> getListById(@PathVariable Long id){
		return jobPostSkillsService.getListById(id);
	}
	
	@RequestMapping(value="/deleteAllByJobPostId/{job_post_id}", method=RequestMethod.GET)
	public ResponseEntity<List<JobPostSkills>> deleteAllByJobPostId(@PathVariable Long job_post_id){
		return jobPostSkillsService.deleteAllByJobPostId(job_post_id);
	}
	
}
