package com.smarthire.main.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.Employer;
import com.smarthire.main.service.EmployerService;

@RestController
@RequestMapping(value="/employerRC")
public class EmployerRC {
	
	@Autowired
	EmployerService employerService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Employer> create(@RequestBody Employer employer){	
		return employerService.create(employer);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Employer> update(@RequestBody Employer employer){
		return employerService.update(employer);
	}
	
	@RequestMapping(value="/read/{username}/",method=RequestMethod.GET)
	public ResponseEntity<Employer> read(@PathVariable String username){
		return employerService.read(username);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Employer> delete(@PathVariable Long id){
		return employerService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Employer>> getList(){
		return employerService.getList();
	}
	
	@RequestMapping(value="/authenticate/{username}/{password}",method=RequestMethod.GET)
	public ResponseEntity<Employer> authenticate(@PathVariable String username, @PathVariable String password){
		System.out.println("Here <Employer> authenticate");
		return employerService.authenticate(username, password);
	}
	
}
