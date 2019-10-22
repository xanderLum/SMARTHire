package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.CategoryDao;
import com.smarthire.xander.models.Category;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao catDao;
	
	@Override
	public ResponseEntity<Category> create(Category jp) {
		try {
			catDao.create(jp);
			return new ResponseEntity<Category>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Category> update(Category jp) {
		try {
			catDao.update(jp);
			return new ResponseEntity<Category>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Category>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Category> delete(Long id) {
		try {
			Category jp = catDao.delete(id);
			return new ResponseEntity<Category>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Category>> getList() {
		try{
			List<Category> ljp = catDao.getList();
			System.out.println("Success in Category Service Impl getList");
			return new ResponseEntity<List<Category>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Category Service Impl getList: " + e);
			return new ResponseEntity<List<Category>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Category> read(Long id) {
		try{
			Category jp = catDao.read(id);
			System.out.println("Success in Category Service Impl read");
			return new ResponseEntity<Category>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Category Service Impl read: " + e);
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Category>> getListById(Long id) {
		try{
			List<Category> ljp = catDao.getListById(id);
			System.out.println("Success Category service impl");
			return new ResponseEntity<List<Category>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<List<Category>>(HttpStatus.BAD_REQUEST);
		}
	}

}
