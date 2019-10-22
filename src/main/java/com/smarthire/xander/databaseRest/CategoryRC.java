package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Category;
import com.smarthire.xander.service.CategoryService;

@RestController
@RequestMapping(value="/categoryRC")
public class CategoryRC {

	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<Category> create(@RequestBody Category catSkills){	
		return categoryService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<Category> update(@RequestBody Category catSkills){
		return categoryService.update(catSkills);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Category> read(@PathVariable Long id){
		return categoryService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<Category> delete(@PathVariable Long id){
		return categoryService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<Category>> getList(){
		return categoryService.getList();
	}
	
	@RequestMapping(value="/getListById/{id}/",method=RequestMethod.GET)
	public ResponseEntity<List<Category>> getPostByEmployer(@PathVariable Long id){
		return categoryService.getListById(id);
	}
}
