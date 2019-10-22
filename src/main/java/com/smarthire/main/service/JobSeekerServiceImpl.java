package com.smarthire.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.JobSeekerDao;
import com.smarthire.main.models.JobSeeker;

@Service
public class JobSeekerServiceImpl implements JobSeekerService{

	@Autowired
	JobSeekerDao jobSeekerDao;

	@Override
	public ResponseEntity<JobSeeker> create(JobSeeker js) {
		try {
			jobSeekerDao.create(js);
			System.out.println("Success in create Job Seeker");
			return new ResponseEntity<JobSeeker>(js, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in create Job Seeker " + e);
			e.printStackTrace();
			return new ResponseEntity<JobSeeker>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeeker> update(JobSeeker js) {
		try{
			jobSeekerDao.update(js);
			System.out.println("Success in update Job Seeker");
			return new ResponseEntity<JobSeeker>(js, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in update Job Seeker");
			e.printStackTrace();
			return new ResponseEntity<JobSeeker>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeeker> read(String username) {
		try{
			JobSeeker js = jobSeekerDao.read(username);;
			System.out.println("Success in read Job Seeker");
			return new ResponseEntity<JobSeeker>(js, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in read Job Seeker");
			e.printStackTrace();
			return new ResponseEntity<JobSeeker>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeeker> delete(Long id) {
		try{
			JobSeeker js = jobSeekerDao.delete(id);
			System.out.println("Success in delete Job Seeker");
			return new ResponseEntity<JobSeeker>(js, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in delete Job Seeker");
			e.printStackTrace();
			return new ResponseEntity<JobSeeker>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobSeeker>> getList() {
		try{
			List<JobSeeker> aljs = jobSeekerDao.getList();
			System.out.println("Success in getList Job Seeker");
			return new ResponseEntity<List<JobSeeker>>(aljs, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in getList Job Seeker");
			e.printStackTrace();
			return new ResponseEntity<List<JobSeeker>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeeker> authenticate(String username, String password) {
		JobSeeker flag = new JobSeeker();
		try{
			flag = jobSeekerDao.authenticate(username, password);
			System.out.println("Success in authenticate Job Seeker: ");
			if(flag != null){
				System.out.println("There is a record JobSeeker authenticate");
				return new ResponseEntity<JobSeeker>(flag, HttpStatus.OK);
			}
			else{
				System.out.println("There is NO record JobSeeker authenticate");
				return new ResponseEntity<JobSeeker>(flag, HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e){
			System.out.println("Error in authenticate Job Seeker");
			e.printStackTrace();
			return new ResponseEntity<JobSeeker>(HttpStatus.BAD_REQUEST);
		}
	}

}
