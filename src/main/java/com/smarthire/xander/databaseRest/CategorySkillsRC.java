package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.CategorySkills;
import com.smarthire.xander.service.CategorySkillsService;

@RestController
@RequestMapping(value="/categorySkillsRC")
public class CategorySkillsRC {

	@Autowired
	CategorySkillsService categorySkillsService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<CategorySkills> create(@RequestBody CategorySkills catSkills){	
		return categorySkillsService.create(catSkills);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<CategorySkills> update(@RequestBody CategorySkills catSkills){
		return categorySkillsService.update(catSkills);
	}
	
	@RequestMapping(value="/check/{skillName}/{categoryName}/", method = RequestMethod.GET)
	public ResponseEntity<CategorySkills> read(@PathVariable String skillName, @PathVariable String categoryName){
		return categorySkillsService.check(skillName, categoryName);
	}
	
	@RequestMapping(value="/read/{id}/",method=RequestMethod.GET)
	public ResponseEntity<CategorySkills> read(@PathVariable Long id){
		return categorySkillsService.read(id);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<CategorySkills> delete(@PathVariable Long id){
		return categorySkillsService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<CategorySkills>> getList(){
		return categorySkillsService.getList();
	}
	
	@RequestMapping(value="/getAllSkillsByCategory/{categoryName}/",method=RequestMethod.GET)
	public ResponseEntity<List<CategorySkills>> getAllSkillsByCategory(@PathVariable String categoryName){
		return categorySkillsService.getAllSkillsByCategory(categoryName);
	}
}
