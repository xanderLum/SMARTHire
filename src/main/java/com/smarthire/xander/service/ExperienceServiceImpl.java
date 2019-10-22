package com.smarthire.xander.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.ExperienceDao;
import com.smarthire.xander.models.Experience;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	@Autowired
	ExperienceDao experienceDao;

	@Override
	public ResponseEntity<Experience> create(Experience jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = inputFormat.parse(jp.getStartDate());
			Date eDate = inputFormat.parse(jp.getEndDate()); 
			
			jp.setStartDate(dateFormat.format(sdate));
			jp.setEndDate(dateFormat.format(eDate));
			
			experienceDao.create(jp);
			return new ResponseEntity<Experience>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Experience>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Experience> update(Experience jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date sdate = inputFormat.parse(jp.getStartDate());
			Date eDate = inputFormat.parse(jp.getEndDate()); 
			
			jp.setStartDate(dateFormat.format(sdate));
			jp.setEndDate(dateFormat.format(eDate));
			experienceDao.update(jp);
			return new ResponseEntity<Experience>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Experience>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Experience> delete(Long id) {
		try {
			Experience jp = experienceDao.delete(id);
			return new ResponseEntity<Experience>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Experience>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Experience>> getList() {
		try {
			List<Experience> ljp = experienceDao.getList();
			System.out.println("Success in Experience Service Impl getList");
			return new ResponseEntity<List<Experience>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in Experience Service Impl getList: " + e);
			return new ResponseEntity<List<Experience>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Experience> read(Long id) {
		try {
			Experience jp = experienceDao.read(id);
			System.out.println("Success in Experience Service Impl read");
			return new ResponseEntity<Experience>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in Experience Service Impl read: " + e);
			return new ResponseEntity<Experience>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Experience>> getAllExperienceByUser(String username) {
		try {
			List<Experience> ljp = experienceDao.getAllExperienceByUser(username);
			System.out.println("Success Experience service impl");
			return new ResponseEntity<List<Experience>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in getPostByEmployer sma: " + e);
			return new ResponseEntity<List<Experience>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Experience> read(String username, String employer) {
		try {
			Experience ljp = experienceDao.read(username, employer);
			System.out.println("Success Experience service impl");
			return new ResponseEntity<Experience>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Experience>(HttpStatus.BAD_REQUEST);
		}
	}

}
