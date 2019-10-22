package com.smarthire.main.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.SocialMediaAccess;
import com.smarthire.main.models.SocialMediaData;
import com.smarthire.main.service.SocialMediaDataService;

@RestController
@RequestMapping(value="/socialMediaData")
public class SocialMediaDataRC {

	@Autowired
	SocialMediaDataService socialMediaDataService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<SocialMediaData> create(@RequestBody SocialMediaData socialMediaData){		
		System.out.println("Here socialMediaData create");
		return socialMediaDataService.create(socialMediaData);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<SocialMediaData> update(@RequestBody SocialMediaData socialMediaData){
		return socialMediaDataService.update(socialMediaData);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<SocialMediaData> read(@PathVariable Long id){
		return socialMediaDataService.read(id);
	}
	
	@RequestMapping(value="/readStringId/{stringId}/",method=RequestMethod.GET)
	public ResponseEntity<SocialMediaData> readStringId(@PathVariable String stringId){
		return socialMediaDataService.readStringId(stringId);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<SocialMediaData> delete(@PathVariable Long id){
		return socialMediaDataService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<SocialMediaData>> getList(){
		return socialMediaDataService.getList();
	}
	
	@RequestMapping(value="/getSMDListOfUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<SocialMediaData>> getSMAListOfUser(@PathVariable String username){
		return socialMediaDataService.getSMDListOfUser(username);
	}
}
