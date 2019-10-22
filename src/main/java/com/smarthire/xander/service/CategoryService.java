package com.smarthire.xander.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.xander.models.Category;

public interface CategoryService {
	public ResponseEntity<Category> create(Category jp);
	public ResponseEntity<Category> update(Category jp);
	public ResponseEntity<Category> delete(Long id);
	public ResponseEntity<List<Category>> getList();
	public ResponseEntity<Category> read(Long id);
	public ResponseEntity<List<Category>> getListById(Long id);
}
