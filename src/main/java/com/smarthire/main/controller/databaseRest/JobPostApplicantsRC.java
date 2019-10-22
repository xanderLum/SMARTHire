package com.smarthire.main.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.JobPostApplicants;
import com.smarthire.main.models.JobSeeker;
import com.smarthire.main.service.JobPostApplicantsService;

@RestController
@RequestMapping(value="/jobPostApplicantsRC")
public class JobPostApplicantsRC {

	@Autowired
	JobPostApplicantsService jobPostApplicantsService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<JobPostApplicants> create(@RequestBody JobPostApplicants jpa){	
		System.out.println("Here2");
		return jobPostApplicantsService.create(jpa);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<JobPostApplicants> update(@RequestBody JobPostApplicants jpa){
		return jobPostApplicantsService.update(jpa);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<JobPostApplicants> read(@PathVariable Long id){
		return jobPostApplicantsService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<JobPostApplicants> delete(@PathVariable Long id){
		return jobPostApplicantsService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<JobPostApplicants>> getList(){
		return jobPostApplicantsService.getList();
	}
	
	@RequestMapping(value="/getAllApplicants/{job_post_id}",method=RequestMethod.GET)
	public ResponseEntity<List<JobSeeker>> getAllApplicants(@PathVariable Long job_post_id){
		return jobPostApplicantsService.getAllApplicants(job_post_id);
	}
	
	@RequestMapping(value="/checkExistApplication/{job_post_id}/{username}/")
	public ResponseEntity<JobPostApplicants> checkExistApplication(@PathVariable Long job_post_id, @PathVariable String username){
		return jobPostApplicantsService.checkExistApplication(job_post_id, username);
	}
	
	@RequestMapping(value="/getAllJobPosts/{username}",method=RequestMethod.GET)
	public ResponseEntity<List<JobPostApplicants>> getAllJobPosts(@PathVariable String username){
		return jobPostApplicantsService.getAllJobPosts(username);
	}
}
