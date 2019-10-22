package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Experience;
import com.smarthire.xander.service.ExperienceService;

@RestController
@RequestMapping(value="/experienceRC")
public class ExperienceRC {
	@Autowired
	ExperienceService experienceService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Experience> create(@RequestBody Experience catSkills){	
		return experienceService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Experience> update(@RequestBody Experience catSkills){
		return experienceService.update(catSkills);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Experience> read(@PathVariable Long id){
		return experienceService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Experience> delete(@PathVariable Long id){
		return experienceService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Experience>> getList(){
		return experienceService.getList();
	}
	
	@RequestMapping(value="/read/{username}/{employer}/", method=RequestMethod.GET)
	public ResponseEntity<Experience> read(@PathVariable String username, @PathVariable String employer){
		return experienceService.read(username, employer);
	}
	
	@RequestMapping(value="/getAllExperienceByUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<Experience>> getAllExperienceByUser(@PathVariable String username){
		return experienceService.getAllExperienceByUser(username);
	}
}
