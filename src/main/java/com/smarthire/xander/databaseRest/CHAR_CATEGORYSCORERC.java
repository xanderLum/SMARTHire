package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.service.CHAR_CATEGORYSCOREService;

@RestController
@RequestMapping(value="/char_catscoreRC")
public class CHAR_CATEGORYSCORERC {

	@Autowired
	CHAR_CATEGORYSCOREService charcatservice;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<CHAR_CATEGORYSCORE> create(@RequestBody CHAR_CATEGORYSCORE charcatscore){	
		return charcatservice.create(charcatscore);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<CHAR_CATEGORYSCORE> update(@RequestBody CHAR_CATEGORYSCORE charcatscore){
		return charcatservice.update(charcatscore);
	}
	
	@RequestMapping(value="/read/{username}/{categoryName}/", method = RequestMethod.GET)
	public ResponseEntity<CHAR_CATEGORYSCORE> read(@PathVariable String username, @PathVariable String categoryName){
		return charcatservice.read(username, categoryName);
	}
	
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<CHAR_CATEGORYSCORE> read(@PathVariable Long id){
		return charcatservice.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<CHAR_CATEGORYSCORE> delete(@PathVariable Long id){
		return charcatservice.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<CHAR_CATEGORYSCORE>> getList(){
		return charcatservice.getList();
	}
	
	@RequestMapping(value="/getAllCharCatScoreByUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<CHAR_CATEGORYSCORE>> getPostByEmployer(@PathVariable String username){
		return charcatservice.getAllCharCatScoreByUser(username);
	}
	
	
}
