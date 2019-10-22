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
import com.smarthire.main.service.SocialMediaAccessService;

@RestController
@RequestMapping(value="/socialMediaAccess")
public class SocialMediaAccessRC {

	@Autowired
	SocialMediaAccessService socialMediaAccessService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<SocialMediaAccess> create(@RequestBody SocialMediaAccess socialMediaAccess){		
		System.out.println("+++++++++++++++++++Here in creates social media accessRC");
		return socialMediaAccessService.create(socialMediaAccess);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<SocialMediaAccess> update(@RequestBody SocialMediaAccess socialMediaAccess){
		return socialMediaAccessService.update(socialMediaAccess);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<SocialMediaAccess> read(@PathVariable Long id){
		return socialMediaAccessService.read(id);
	}
	
	@RequestMapping(value="/readUserSM/{username}/{social_media}/",method=RequestMethod.GET)
	public ResponseEntity<SocialMediaAccess> readUserSM(@PathVariable String username, @PathVariable String social_media){
		return socialMediaAccessService.readUserSM(username, social_media);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<SocialMediaAccess> delete(@PathVariable Long id){
		return socialMediaAccessService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<SocialMediaAccess>> getList(){
		return socialMediaAccessService.getList();
	}
	
	@RequestMapping(value="/getSMAListOfUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<SocialMediaAccess>> getSMAListOfUser(@PathVariable String username){
		return socialMediaAccessService.getSMAListOfUser(username);
	}
	
	
}
