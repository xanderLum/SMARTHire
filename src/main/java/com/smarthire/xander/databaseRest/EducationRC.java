package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Education;
import com.smarthire.xander.service.EducationService;

@RestController
@RequestMapping(value="/educationRC")
public class EducationRC {
	@Autowired
	EducationService educationService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Education> create(@RequestBody Education catSkills){	
		return educationService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Education> update(@RequestBody Education catSkills){
		return educationService.update(catSkills);
	}
	
	@RequestMapping(value="/read/{username}/{schoolName}/", method = RequestMethod.GET)
	public ResponseEntity<Education> read(@PathVariable String username, @PathVariable String schoolName){
		return educationService.read(username, schoolName);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Education> read(@PathVariable Long id){
		return educationService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Education> delete(@PathVariable Long id){
		return educationService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Education>> getList(){
		return educationService.getList();
	}
	
	@RequestMapping(value="/getAllEducationByUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<Education>> getAllEducationByUser(@PathVariable String username){
		return educationService.getAllEducationByUser(username);
	}
}
