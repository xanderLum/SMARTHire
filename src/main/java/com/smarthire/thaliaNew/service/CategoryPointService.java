package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.smarthire.thaliaNew.Model.CategoryPoint;


public interface CategoryPointService {

	public ResponseEntity<CategoryPoint> create(CategoryPoint cp);
	public ResponseEntity<CategoryPoint> update(CategoryPoint cp);
	public ResponseEntity<CategoryPoint> delete(Long id);
	public ResponseEntity<List<CategoryPoint>> getList();
	public ResponseEntity<CategoryPoint> read(Long id);
	public ResponseEntity<List<CategoryPoint>> readAllByfk(Long fkId);
	//public ResponseEntity<List<CategoryPoint>> createList(List<CategoryPoint> c);

}
