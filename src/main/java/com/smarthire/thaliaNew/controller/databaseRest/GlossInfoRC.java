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

import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.service.GlossInfoService;

@RestController
@RequestMapping(value="/glossInfoRC")
public class GlossInfoRC {
	
	@Autowired
	GlossInfoService glossInfoService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<GlossInfo> create(@RequestBody GlossInfo gi){	
		return glossInfoService.create(gi);
	}
	
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<GlossInfo> update(@RequestBody GlossInfo gi){
		return glossInfoService.update(gi);
	}
	
//	@RequestMapping(value="/read/{socialMediaDataId}/",method=RequestMethod.GET)
//	public ResponseEntity<GlossInfo> read(@PathVariable String socialMediaDataId){
//		return glossInfoService.read(socialMediaDataId);
//	}
	
	@RequestMapping(value="/readAll/{wordInfoId}/",method=RequestMethod.GET)
	public ResponseEntity<List<GlossInfo>> readAll(@PathVariable Long wordInfoId){
		return glossInfoService.readAllByfk(wordInfoId);
	}
	
	@RequestMapping(value="/readByfkWSID/{wordInfoId}/{wordsenseId}/",method=RequestMethod.GET)
	public ResponseEntity<GlossInfo> readByfkWSID(@PathVariable Long wordInfoId, @PathVariable String wordsenseId){
		System.out.println("GlossInfoRC reading yay");
		return glossInfoService.readByfkWSId(wordInfoId, wordsenseId);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<GlossInfo> delete(@PathVariable Long id){
		return glossInfoService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<GlossInfo>> getList(){
		return glossInfoService.getList();
	}
	
}