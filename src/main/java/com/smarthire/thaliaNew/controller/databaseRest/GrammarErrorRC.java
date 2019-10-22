package com.smarthire.thaliaNew.controller.databaseRest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.service.GrammarErrorService;


@RestController
@RequestMapping(value="/grammarErrorRC")
public class GrammarErrorRC {
	
	@Autowired
	GrammarErrorService grammarErrorService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<GrammarError> create(@RequestBody GrammarError ge){	
		return grammarErrorService.create(ge);
	}
	
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<GrammarError> update(@RequestBody GrammarError ge){
		return grammarErrorService.update(ge);
	}
	
//	@RequestMapping(value="/read/{socialMediaDataId}/",method=RequestMethod.GET)
//	public ResponseEntity<GrammarError> read(@PathVariable String socialMediaDataId){
//		return grammarErrorService.read(socialMediaDataId);
//	}
	
	@RequestMapping(value="/readAll/{sentenceDataId}/",method=RequestMethod.GET)
	public ResponseEntity<List<GrammarError>> readAll(@PathVariable Long sentenceDataId){
		return grammarErrorService.readAllByfk(sentenceDataId);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<GrammarError> delete(@PathVariable Long id){
		return grammarErrorService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<GrammarError>> getList(){
		return grammarErrorService.getList();
	}
	
	
}