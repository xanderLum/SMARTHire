package com.smarthire.xander.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.TestDao;
import com.smarthire.xander.models.Test;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	TestDao testDao;
	
	@Override
	public ResponseEntity<Test> create(Test jp) {
		try {
			String text = jp.getDateTaken().substring(1,11);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = inputFormat.parse(text);
			jp.setDateTaken(dateFormat.format(date));
			testDao.create(jp);
			return new ResponseEntity<Test>(jp, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Test>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Test> update(Test jp) {
		try {
			String text = jp.getDateTaken().substring(1,11);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = inputFormat.parse(text);
			jp.setDateTaken(dateFormat.format(date));
			testDao.update(jp);
			return new ResponseEntity<Test>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Test>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Test> delete(Long id) {
		try {
			Test jp = testDao.delete(id);
			return new ResponseEntity<Test>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Test>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Test>> getList() {
		try{
			List<Test> ljp = testDao.getList();
			System.out.println("Success in Test Service Impl getList");
			return new ResponseEntity<List<Test>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Test Service Impl getList: " + e);
			return new ResponseEntity<List<Test>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Test> read(Long id) {
		try{
			Test jp = testDao.read(id);
			System.out.println("Success in Test Service Impl read");
			return new ResponseEntity<Test>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Test Service Impl read: " + e);
			return new ResponseEntity<Test>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Test> read(String username, String name) {
		try{
			Test ljp = testDao.read(username, name);
			System.out.println("Success Test service impl");
			return new ResponseEntity<Test>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<Test>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Test>> getAllTestByUser(String username) {
		try{
			List<Test> ljp = testDao.getAllTestByUser(username);
			System.out.println("Success CategorySkills service impl");
			return new ResponseEntity<List<Test>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<List<Test>>(HttpStatus.BAD_REQUEST);
		}
	}

}
