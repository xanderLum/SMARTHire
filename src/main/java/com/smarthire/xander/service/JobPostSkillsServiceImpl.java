package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.JobPostSkillsDao;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.JobPostSkills;

@Service
public class JobPostSkillsServiceImpl implements JobPostSkillsService{
 
	@Autowired
	JobPostSkillsDao jpskillsdao;
	
	@Override
	public ResponseEntity<JobPostSkills> create(JobPostSkills jp) {
		try {
			jpskillsdao.create(jp);
			return new ResponseEntity<JobPostSkills>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<JobPostSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPostSkills> update(JobPostSkills jp) {
		try {
			jpskillsdao.update(jp);
			return new ResponseEntity<JobPostSkills>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<JobPostSkills>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPostSkills> delete(Long id) {
		try {
			JobPostSkills jp = jpskillsdao.delete(id);
			return new ResponseEntity<JobPostSkills>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<JobPostSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPostSkills>> getList() {
		try{
			List<JobPostSkills> ljp = jpskillsdao.getList();
			System.out.println("Success in JobPostSkills Service Impl getList");
			return new ResponseEntity<List<JobPostSkills>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in JobPostSkills Service Impl getList: " + e);
			return new ResponseEntity<List<JobPostSkills>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobPostSkills> read(Long id) {
		try{
			JobPostSkills jp = jpskillsdao.read(id);
			System.out.println("Success in JobPostSkills Service Impl read");
			return new ResponseEntity<JobPostSkills>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in JobPostSkills Service Impl read: " + e);
			return new ResponseEntity<JobPostSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPostSkills>> getListById(Long id) {
		try{
			List<JobPostSkills> ljp = jpskillsdao.getListById(id);
			System.out.println("Success JobPostSkills service impl");
			return new ResponseEntity<List<JobPostSkills>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<List<JobPostSkills>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobPostSkills>> deleteAllByJobPostId(Long id) {
		try{
			List<JobPostSkills> ljp = jpskillsdao.deleteAllByJobPostId(id);
			System.out.println("Success JobPostSkills service impl");
			return new ResponseEntity<List<JobPostSkills>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<List<JobPostSkills>>(HttpStatus.BAD_REQUEST);
		}
	}
}
