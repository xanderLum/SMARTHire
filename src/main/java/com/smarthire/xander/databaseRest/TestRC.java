package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Test;
import com.smarthire.xander.service.TestService;

@RestController
@RequestMapping(value="/testRC")
public class TestRC {
	@Autowired
	TestService testService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Test> create(@RequestBody Test catSkills){	
		return testService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Test> update(@RequestBody Test catSkills){
		return testService.update(catSkills);
	}
	
	@RequestMapping(value="/read/{username}/{name}/", method = RequestMethod.GET)
	public ResponseEntity<Test> read(@PathVariable String username, @PathVariable String name){
		return testService.read(username, name);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Test> read(@PathVariable Long id){
		return testService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Test> delete(@PathVariable Long id){
		return testService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Test>> getList(){
		return testService.getList();
	}
	
	@RequestMapping(value="/getAllTestByUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<Test>> getAllTestsByUser(@PathVariable String username){
		return testService.getAllTestByUser(username);
	}
}
