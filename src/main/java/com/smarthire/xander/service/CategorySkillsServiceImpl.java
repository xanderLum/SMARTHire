package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.CategorySkillsDao;
import com.smarthire.xander.models.CategorySkills;

@Service
public class CategorySkillsServiceImpl implements CategorySkillsService{
	@Autowired
	CategorySkillsDao categorySkillsDao;

	@Override
	public ResponseEntity<CategorySkills> create(CategorySkills jp) {
		try {
			categorySkillsDao.create(jp);
			return new ResponseEntity<CategorySkills>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CategorySkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CategorySkills> update(CategorySkills jp) {
		try {
			categorySkillsDao.update(jp);
			return new ResponseEntity<CategorySkills>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<CategorySkills>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CategorySkills> delete(Long id) {
		try {
			CategorySkills jp = categorySkillsDao.delete(id);
			return new ResponseEntity<CategorySkills>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<CategorySkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<CategorySkills>> getList() {
		try{
			List<CategorySkills> ljp = categorySkillsDao.getList();
			System.out.println("Success in CategorySkills Service Impl getList");
			return new ResponseEntity<List<CategorySkills>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in CategorySkills Service Impl getList: " + e);
			return new ResponseEntity<List<CategorySkills>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CategorySkills> read(Long id) {
		try{
			CategorySkills jp = categorySkillsDao.read(id);
			System.out.println("Success in CategorySkills Service Impl read");
			return new ResponseEntity<CategorySkills>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in CategorySkills Service Impl read: " + e);
			return new ResponseEntity<CategorySkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CategorySkills> check(String skillName, String categoryName) {
		try{
			CategorySkills ljp = categorySkillsDao.checkC(skillName, categoryName);
			System.out.println("Success CategorySkills service impl");
			return new ResponseEntity<CategorySkills>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<CategorySkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<CategorySkills>> getAllSkillsByCategory(String categoryName) {
		try{
			List<CategorySkills> ljp = categorySkillsDao.getAllSkillsByCategory(categoryName);
			System.out.println("Success CategorySkills service impl");
			return new ResponseEntity<List<CategorySkills>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<List<CategorySkills>>(HttpStatus.BAD_REQUEST);
		}
	}
}
