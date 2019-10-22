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

import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.service.WordInfoService;


@RestController
@RequestMapping(value="/wordInfoRC")
public class WordInfoRC {
	
	@Autowired
	WordInfoService wordInfoService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<WordInfo> create(@RequestBody WordInfo wi){	
		return wordInfoService.create(wi);
	}
	
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<WordInfo> update(@RequestBody WordInfo wi){
		return wordInfoService.update(wi);
	}
	
	@RequestMapping(value="/read/{socialMediaDataId}/{word}/{tag}/",method=RequestMethod.GET)
	public ResponseEntity<WordInfo> read(@PathVariable String socialMediaDataId, @PathVariable String word, @PathVariable String tag){
		return wordInfoService.readByfkWordTag(socialMediaDataId, word, tag);
	}
	
	@RequestMapping(value="/readAll/{socialMediaDataId}/",method=RequestMethod.GET)
	public ResponseEntity<List<WordInfo>> readAll(@PathVariable String socialMediaDataId){
		return wordInfoService.readAllByfk(socialMediaDataId);
	}
	
	@RequestMapping(value="/readAllwt/{word}/{tag}",method=RequestMethod.GET)
	public ResponseEntity<List<WordInfo>> readAllwt(@PathVariable String word, @PathVariable String tag){
		return wordInfoService.readAllByWordTag(word, tag);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<WordInfo> delete(@PathVariable Long id){
		return wordInfoService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<WordInfo>> getList(){
		return wordInfoService.getList();
	}
	
	
}