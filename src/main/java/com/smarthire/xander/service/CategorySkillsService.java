package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.CategorySkills;

public interface CategorySkillsService {
	public ResponseEntity<CategorySkills> create(CategorySkills jp);
	public ResponseEntity<CategorySkills> update(CategorySkills jp);
	public ResponseEntity<CategorySkills> delete(Long id);
	public ResponseEntity<List<CategorySkills>> getList();
	public ResponseEntity<CategorySkills> read(Long id);
	public ResponseEntity<CategorySkills> check(String skillName, String categoryName);
	public ResponseEntity<List<CategorySkills>> getAllSkillsByCategory(String categoryName);
}
