package com.smarthire.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.JobSeekerSkillsDao;
import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

@Service
public class JobSeekerSkillsServiceImpl implements JobSeekerSkillsService {

	@Autowired
	JobSeekerSkillsDao jobSeekerSkillsDao;

	@Override
	public ResponseEntity<JobSeekerSkills> create(JobSeekerSkills js) {
		//check first if username and skill exist in table
		JobSeekerSkills flag = new JobSeekerSkills();
		String userFromdb;
		boolean exist = true;
		try{
			flag = jobSeekerSkillsDao.checkSkillUser(js.getUsername(), js.getSkill_name());
			userFromdb=flag.getUsername();
			System.out.println("Username and skill exist." + userFromdb);
		}
		catch(Exception e){
			exist = false;
			System.out.println("username and skill not found. need to create: " + e);
			flag=null;
		}
		System.out.println("Here create");
		if(exist){
			System.out.println("Returning bad request");
			return new ResponseEntity<JobSeekerSkills>(HttpStatus.BAD_REQUEST);
		}
		else{
			try{
				jobSeekerSkillsDao.create(js);
				System.out.println("Success job seeker skills service create");
				return new ResponseEntity<JobSeekerSkills>(js, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in jobseeker skills service impl create: " + e);
				return new ResponseEntity<JobSeekerSkills>(HttpStatus.BAD_REQUEST);
			}
		}
		
	}

	@Override
	public ResponseEntity<JobSeekerSkills> update(JobSeekerSkills js) {
		try{
			jobSeekerSkillsDao.update(js);
			System.out.println("Success in JobSeekerSkills Service Impl update");
			return new ResponseEntity<JobSeekerSkills>(js, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in JobSeekerSkills Service Impl update: " + e);
			return new ResponseEntity<JobSeekerSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeekerSkills> delete(Long id) {
		try{
			JobSeekerSkills jp = jobSeekerSkillsDao.delete(id);
			System.out.println("Success in JobSeekerSkills Service Impl delete");
			return new ResponseEntity<JobSeekerSkills>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in JobSeekerSkills Service Impl delete: " + e);
			return new ResponseEntity<JobSeekerSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobSeekerSkills>> getList() {
		try{
			System.out.println("Here getList Job Seekers Skills service impl");
			List<JobSeekerSkills> ljsk = jobSeekerSkillsDao.getList();
			System.out.println("Success getList Job Seekers Skills service impl");
			return new ResponseEntity<List<JobSeekerSkills>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList Job Seekers Skills service impl: " +e);
			return new ResponseEntity<List<JobSeekerSkills>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeekerSkills> read(Long id) {
		try{
			JobSeekerSkills jsk = jobSeekerSkillsDao.read(id);
			System.out.println("Success read Job Seekers Skills service impl");
			return new ResponseEntity<JobSeekerSkills>(jsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch read Job Seekers Skills service impl: " +e);
			return new ResponseEntity<JobSeekerSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<JobSeekerSkills> checkSkillUser(String username, String skill_name) {
		System.out.println("here getAllSkillsUser jobseekerskills service impl ");
		JobSeekerSkills flag = new JobSeekerSkills();
		try{
			flag = jobSeekerSkillsDao.checkSkillUser(username, skill_name);
			System.out.println("Success in getAllSkillsUser service impl");
			return new ResponseEntity<JobSeekerSkills>(flag, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in getAllSkillsUser service impl: " + e);
			return new ResponseEntity<JobSeekerSkills>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<JobSeekerSkills>> getAllSkillsByUsername(String username) {
		try{
			List<JobSeekerSkills> ljp = jobSeekerSkillsDao.getAllSkillsByUsername(username);
			System.out.println("lgp size = "+ljp.size());
			System.out.println("Success getAllSkillsByUsername service impl");
			return new ResponseEntity<List<JobSeekerSkills>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in getAllCharCatScoreByUser sma: " + e);
			return new ResponseEntity<List<JobSeekerSkills>>(HttpStatus.BAD_REQUEST);
		}
	}
}
