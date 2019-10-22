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

import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.service.CategoryPointService;


@RestController
@RequestMapping(value="/categoryPointRC")
public class CategoryPointRC {
	
	@Autowired
	CategoryPointService categoryPointService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<CategoryPoint> create(@RequestBody CategoryPoint bps){	
		return categoryPointService.create(bps);
	}
	
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<CategoryPoint> update(@RequestBody CategoryPoint bps){
		return categoryPointService.update(bps);
	}
	
	@RequestMapping(value="/readAll/{partPointerId}/",method=RequestMethod.GET)
	public ResponseEntity<List<CategoryPoint>> readAll(@PathVariable Long partPointerId){
		return categoryPointService.readAllByfk(partPointerId);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<CategoryPoint> delete(@PathVariable Long id){
		return categoryPointService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<CategoryPoint>> getList(){
		return categoryPointService.getList();
	}
	
	
}
