package com.smarthire.main.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.JobSeeker;
import com.smarthire.main.service.JobSeekerService;

@RestController
@RequestMapping(value="/jobSeeker")
public class JobSeekerRC {

	@Autowired
	JobSeekerService jobSeekerService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<JobSeeker> create(@RequestBody JobSeeker jobSeeker){	
		return jobSeekerService.create(jobSeeker);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<JobSeeker> update(@RequestBody JobSeeker jobSeeker){
		//System.out.println("delete this");
		System.out.println("jobseeker rc/updat = printing seeker");
		System.out.println(""+jobSeeker.toString());
		return jobSeekerService.update(jobSeeker);
	}
	
	@RequestMapping(value="/read/{username}/",method=RequestMethod.GET)
	public ResponseEntity<JobSeeker> read(@PathVariable String username){
		return jobSeekerService.read(username);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<JobSeeker> delete(@PathVariable Long id){
		return jobSeekerService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<JobSeeker>> getList(){
		return jobSeekerService.getList();
	}
	
	@RequestMapping(value="/authenticate/{username}/{password}",method=RequestMethod.GET)
	public ResponseEntity<JobSeeker> authenticate(@PathVariable String username, @PathVariable String password){
		System.out.println("Here <JobSeeker> authenticate");
		return jobSeekerService.authenticate(username, password);
	}
	
}
