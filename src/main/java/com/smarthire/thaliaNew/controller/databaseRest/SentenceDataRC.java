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

import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.PhraseData;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.service.SentenceDataService;


@RestController
@RequestMapping(value="/sentenceDataRC")
public class SentenceDataRC {
	
	@Autowired
	SentenceDataService sentenceDataService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<SentenceData> create(@RequestBody SentenceData sd){	
		return sentenceDataService.create(sd);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<SentenceData> update(@RequestBody SentenceData sd){
		return sentenceDataService.update(sd);
	}
	
	@RequestMapping(value="/readAll/{socialMediaDataId}/",method=RequestMethod.GET)
	public ResponseEntity<List<SentenceData>> readAll(@PathVariable String socialMediaDataId){
		return sentenceDataService.readAllByfk(socialMediaDataId);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<SentenceData> delete(@PathVariable Long id){
		return sentenceDataService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<SentenceData>> getList(){
		return sentenceDataService.getList();
	}
	
	
}