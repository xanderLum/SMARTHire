package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Certificate;
import com.smarthire.xander.service.CertificateService;

@RestController
@RequestMapping(value="/certificateRC")
public class CertificateRC {
	@Autowired
	CertificateService certificateService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Certificate> create(@RequestBody Certificate catSkills){	
		return certificateService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Certificate> update(@RequestBody Certificate catSkills){
		return certificateService.update(catSkills);
	}
	
	@RequestMapping(value="/read/{username}/{name}/", method = RequestMethod.GET)
	public ResponseEntity<Certificate> read(@PathVariable String username, @PathVariable String name){
		return certificateService.read(username, name);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Certificate> read(@PathVariable Long id){
		return certificateService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Certificate> delete(@PathVariable Long id){
		return certificateService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Certificate>> getList(){
		return certificateService.getList();
	}
	
	@RequestMapping(value="/getAllCertificatesByUser/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<Certificate>> getAllCertificatesByUser(@PathVariable String username){
		return certificateService.getAllCertificatesByUser(username);
	}
}
