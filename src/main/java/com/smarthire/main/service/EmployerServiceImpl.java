package com.smarthire.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.EmployerDao;
import com.smarthire.main.models.Employer;

@Service
public class EmployerServiceImpl implements EmployerService{
	
	@Autowired
	EmployerDao employerDao;

	@Override
	public ResponseEntity<Employer> create(Employer emp) {
		try{
			employerDao.create(emp);
			System.out.println("Success in Employer Service Impl create");
			return new ResponseEntity<Employer>(emp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Employer Service Impl create");
			return new ResponseEntity<Employer>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Employer> update(Employer emp) {
		try{
			employerDao.update(emp);
			System.out.println("Success in Employer Service Impl update");
			return new ResponseEntity<Employer>(emp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Employer Service Impl update");
			return new ResponseEntity<Employer>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Employer> delete(Long id) {
		try{
			Employer emp = employerDao.delete(id);
			System.out.println("Success in Employer Service Impl delete");
			return new ResponseEntity<Employer>(emp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Employer Service Impl delete");
			return new ResponseEntity<Employer>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Employer>> getList() {
		try{
			List<Employer> lemp = employerDao.getList();
			System.out.println("Success in Employer Service Impl getList");
			return new ResponseEntity<List<Employer>>(lemp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Employer Service Impl getList");
			return new ResponseEntity<List<Employer>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Employer> authenticate(String username, String password) {
		Employer flag = new Employer();
		try{
			flag = employerDao.authenticate(username, password);
			System.out.println("Success in Employer Service Impl ");
			if(flag != null){
				System.out.println("There is a record Employer authenticate");
				return new ResponseEntity<Employer>(flag, HttpStatus.OK);
			}
			else{
				System.out.println("There is NO record Employer authenticate");
				return new ResponseEntity<Employer>(HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e){
			System.out.println("Catch in Employer Service Impl authenticate");
			return new ResponseEntity<Employer>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Employer> read(String username) {
		try{
			Employer emp = employerDao.read(username);
			System.out.println("Success in Employer Service Impl read");
			return new ResponseEntity<Employer>(emp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Employer Service Impl read");
			return new ResponseEntity<Employer>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
