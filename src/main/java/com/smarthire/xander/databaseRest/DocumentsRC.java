package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Documents;
import com.smarthire.xander.service.DocumentsService;
@RestController
@RequestMapping(value="/documentsRC")
public class DocumentsRC {

	@Autowired
	DocumentsService docService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Documents> create(@RequestBody Documents doc){	
		return docService.create(doc);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Documents> update(@RequestBody Documents doc){
		return docService.update(doc);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Documents> read(@PathVariable Long id){
		return docService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Documents> delete(@PathVariable Long id){
		return docService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Documents>> getList(){
		return docService.getList();
	}
	
	@RequestMapping(value="/read/{username}/{filename}/", method = RequestMethod.GET)
	public ResponseEntity<Documents> read(@PathVariable String username, @PathVariable String filename){
		return docService.read(username, filename);
	}
	
	@RequestMapping(value="/getAllDocumentsByUsername/{username}/",method=RequestMethod.GET)
	public ResponseEntity<List<Documents>> getListById(@PathVariable String username){
		return docService.getAllDocumentsByUsername(username);
	}
}
