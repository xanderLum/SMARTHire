package com.smarthire.xander.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.JobAlertDao;
import com.smarthire.xander.models.CategorySkills;
import com.smarthire.xander.models.JobAlert;

@Service
public class JobAlertServiceImpl implements JobAlertService {
	@Autowired
	JobAlertDao jobAlertDao;

	@Override
	public ResponseEntity<JobAlert> create(JobAlert jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			jp.setDateCreated(dateFormat.format(date));
			System.out.println("dateCreated = " + jp.getDateCreated());
		
			
			jobAlertDao.create(jp);
			return new ResponseEntity<JobAlert>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<JobAlert>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobAlert> update(JobAlert jp) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			jp.setDateCreated(dateFormat.format(date));
			System.out.println("dateCreated = " + jp.getDateCreated());
				
			jobAlertDao.update(jp);
			return new ResponseEntity<JobAlert>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<JobAlert>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobAlert> delete(Long id) {
		try {
			JobAlert jp = jobAlertDao.delete(id);
			return new ResponseEntity<JobAlert>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<JobAlert>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobAlert>> getList() {
		try {
			List<JobAlert> ljp = jobAlertDao.getList();
			System.out.println("Success in JobAlert Service Impl getList");
			return new ResponseEntity<List<JobAlert>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in Test Service Impl getList: " + e);
			return new ResponseEntity<List<JobAlert>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobAlert> read(Long id) {
		try {
			JobAlert jp = jobAlertDao.read(id);
			System.out.println("Success in Test Service Impl read");
			return new ResponseEntity<JobAlert>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobAlert Service Impl read: " + e);
			return new ResponseEntity<JobAlert>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobAlert> readByJPId(Long job_post_id) {
		try {
			JobAlert ljp = jobAlertDao.readByJPId(job_post_id);
			System.out.println("Success readByJPId service impl");
			return new ResponseEntity<JobAlert>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<JobAlert>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobAlert>> getAllAlertsByUsername(String username) {
		try {
			List<JobAlert> ljp = jobAlertDao.getAllAlertsByUsername(username);
			System.out.println("Success CategorySkills service impl");
			return new ResponseEntity<List<JobAlert>>(ljp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<JobAlert>>(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<JobAlert> checkIfExists(String jsUsername, Long job_post_id) {
		try {
			JobAlert jp = jobAlertDao.checkIfExists(jsUsername, job_post_id);
			System.out.println("Success in Test Service Impl read");
			return new ResponseEntity<JobAlert>(jp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Catch in JobAlert Service Impl read: " + e);
			return new ResponseEntity<JobAlert>(HttpStatus.BAD_REQUEST);
		}
	}

}
