package com.smarthire.main.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.JobPost;
import com.smarthire.main.models.SocialMediaAccess;
import com.smarthire.main.service.JobPostService;

@RestController
@RequestMapping(value="/jobPostRC")
public class JobPostRC {
	@Autowired
	JobPostService jobPostService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<JobPost> create(@RequestBody JobPost jobPost){	
		return jobPostService.create(jobPost);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<JobPost> update(@RequestBody JobPost jobPost){
		return jobPostService.update(jobPost);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<JobPost> read(@PathVariable Long id){
		return jobPostService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<JobPost> delete(@PathVariable Long id){
		return jobPostService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<JobPost>> getList(){
		return jobPostService.getList();
	}
	
	@RequestMapping(value="/getPostByEmployer/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<JobPost>> getPostByEmployer(@PathVariable String username){
		return jobPostService.getPostByEmployer(username);
	}
	
	//@RequestMapping(value="/delete/{}")
	
}
