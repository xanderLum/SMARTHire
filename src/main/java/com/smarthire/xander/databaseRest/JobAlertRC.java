package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.JobAlert;
import com.smarthire.xander.service.JobAlertService;

@RestController
@RequestMapping(value="/jobAlertRC")
public class JobAlertRC {
	
	@Autowired
	JobAlertService jobAlertService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<JobAlert> create(@RequestBody JobAlert catSkills){	
		return jobAlertService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<JobAlert> update(@RequestBody JobAlert catSkills){
		return jobAlertService.update(catSkills);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<JobAlert> read(@PathVariable Long id){
		return jobAlertService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<JobAlert> delete(@PathVariable Long id){
		return jobAlertService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<JobAlert>> getList(){
		return jobAlertService.getList();
	}
	
	@RequestMapping(value="/readByJPId/{job_post_id}/",method=RequestMethod.GET)
	public ResponseEntity<JobAlert> getPostByEmployer(@PathVariable Long job_post_id){
		return jobAlertService.readByJPId(job_post_id);
	}
	
	@RequestMapping(value="/getAllAlertsByUsername/{username}/", method = RequestMethod.GET)
	public ResponseEntity<List<JobAlert>> getAllAlertsByUsername(@PathVariable String username){
		return jobAlertService.getAllAlertsByUsername(username);
	}

	@RequestMapping(value="/checkIfExists/{jsUsername}/{job_post_id}/", method = RequestMethod.GET)
	public ResponseEntity<JobAlert> checkIfExists(@PathVariable String jsUsername, @PathVariable Long job_post_id){
		return jobAlertService.checkIfExists(jsUsername, job_post_id);
	}
}
