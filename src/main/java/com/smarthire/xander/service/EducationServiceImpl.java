package com.smarthire.xander.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.EducationDao;
import com.smarthire.xander.models.Education;

@Service
public class EducationServiceImpl implements EducationService{
	@Autowired
	EducationDao educationDao;

	@Override
	public ResponseEntity<Education> create(Education jp) {
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = inputFormat.parse(jp.getStartDate());
			Date eDate = inputFormat.parse(jp.getEndDate()); 
			

			jp.setStartDate(inputFormat.format(sdate));
			jp.setEndDate(inputFormat.format(eDate));
			educationDao.create(jp);
			return new ResponseEntity<Education>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Education> update(Education jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = inputFormat.parse(jp.getStartDate());
			Date eDate = inputFormat.parse(jp.getEndDate()); 
			
			jp.setStartDate(dateFormat.format(sdate));
			jp.setEndDate(dateFormat.format(eDate));
			educationDao.update(jp);
			return new ResponseEntity<Education>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Education>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Education> delete(Long id) {
		try {
			Education jp = educationDao.delete(id);
			return new ResponseEntity<Education>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Education>> getList() {
		try{
			List<Education> ljp = educationDao.getList();
			System.out.println("Success in Education Service Impl getList");
			return new ResponseEntity<List<Education>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Education Service Impl getList: " + e);
			return new ResponseEntity<List<Education>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Education> read(Long id) {
		try{
			Education jp = educationDao.read(id);
			System.out.println("Success in Education Service Impl read");
			return new ResponseEntity<Education>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Education Service Impl read: " + e);
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Education> read(String username, String schoolName) {
		try{
			Education ljp = educationDao.read(username, schoolName);
			System.out.println("Success Education service impl");
			return new ResponseEntity<Education>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<Education>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Education>> getAllEducationByUser(String username) {
		try{
			List<Education> ljp = educationDao.getAllEducationByUser(username);
			System.out.println("Success getAllEducationByUser service impl");
			return new ResponseEntity<List<Education>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<List<Education>>(HttpStatus.BAD_REQUEST);
		}
	}

}
