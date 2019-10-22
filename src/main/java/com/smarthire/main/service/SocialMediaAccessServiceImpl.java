package com.smarthire.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.SocialMediaAccessDao;
import com.smarthire.main.models.SocialMediaAccess;

@Service
public class SocialMediaAccessServiceImpl implements SocialMediaAccessService{

	@Autowired
	SocialMediaAccessDao socialMediaAccessDao;

	@Override
	public ResponseEntity<SocialMediaAccess> create(SocialMediaAccess sma) {
		//check first if user name and social media is unique
		System.out.println("++++++++++++++++++here in sma create social media access service impl");
		SocialMediaAccess flag = new SocialMediaAccess();
		String userFromDb;
		boolean exist = true;
		System.out.println("++++++++++++++++++here 1 in sma create social media access service impl");
		try {
			System.out.println("++++++++++++++++++here 2 1 in sma create social media access service impl");
			flag = socialMediaAccessDao.readUserSM(sma.getUsername(), sma.getSocial_media());
			System.out.println("++++++++++++++++++here 2 2 in sma create social media access service impl");
			userFromDb = flag.getUsername();
			System.out.println("++++++++++++++++++here 2 3 in sma create social media access service impl");
			System.out.println("Username: " + flag.getUsername() + " --> SociaL Media: " + sma.getSocial_media() + " ALREADY EXIST. just update");			
		} catch (Exception e) {
			System.out.println("++++++++++++++++++here 3 1 in sma create social media access service impl");
			exist = false;
			System.out.println("++++++++++++++++++here 3 2 in sma create social media access service impl");
			System.out.println("SMA NOT EXIST. CREATING");			
		}
		
		try {
		if(exist){
			System.out.println("UPDATING SMA");
			socialMediaAccessDao.update(sma);
			return new ResponseEntity<SocialMediaAccess>(sma, HttpStatus.OK);
		}
		else{
			System.out.println("CREATING SMA");
			socialMediaAccessDao.create(sma);
			return new ResponseEntity<SocialMediaAccess>(sma, HttpStatus.OK);
		}
		} catch (Exception e) {
			System.out.println("Returning bad request");	
			return new ResponseEntity<SocialMediaAccess>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<SocialMediaAccess> update(SocialMediaAccess sma) {
		try {
			System.out.println("Here in update SMAServiceImpl. username: " + sma.getUsername());
			socialMediaAccessDao.update(sma);
			System.out.println("Success in update SocialMediaAccess");
			return new ResponseEntity<SocialMediaAccess>(sma, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in update SocialMediaAccess");
			e.printStackTrace();
			return new ResponseEntity<SocialMediaAccess>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<SocialMediaAccess> read(Long id) {
		try{
			SocialMediaAccess sma = socialMediaAccessDao.read(id);;
			System.out.println("Success in read SocialMediaAccess");
			return new ResponseEntity<SocialMediaAccess>(sma, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in read SocialMediaAccess");
			e.printStackTrace();
			return new ResponseEntity<SocialMediaAccess>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<SocialMediaAccess> readUserSM(String username, String socia_media) {
		SocialMediaAccess flag = new SocialMediaAccess();
		try{
			flag = socialMediaAccessDao.readUserSM(username, socia_media);
			System.out.println("Success in readUserSM SocialMediaAccess");
			if(flag != null){
				System.out.println("There is a record SocialMediaAccess readUserSM");
				return new ResponseEntity<SocialMediaAccess>(flag, HttpStatus.OK);
			}
			else{
				System.out.println("There is a NO record SocialMediaAccess readUserSM");
				return new ResponseEntity<SocialMediaAccess>(HttpStatus.NOT_FOUND);
			}
		}
		catch(Exception e){
			System.out.println("Error in read SocialMediaAccess");
			e.printStackTrace();
			return new ResponseEntity<SocialMediaAccess>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<SocialMediaAccess> delete(Long id) {
		try{
			SocialMediaAccess sma = socialMediaAccessDao.delete(id);;
			System.out.println("Success in delete SocialMediaAccess");
			return new ResponseEntity<SocialMediaAccess>(sma, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Error in delete SocialMediaAccess");
			e.printStackTrace();
			return new ResponseEntity<SocialMediaAccess>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<SocialMediaAccess>> getList() {
		try{
			List<SocialMediaAccess> lsma = socialMediaAccessDao.getList();
			System.out.println("Success sma getlist service impl");
			return new ResponseEntity<List<SocialMediaAccess>>(lsma, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in getlist sma: " + e);
			return new ResponseEntity<List<SocialMediaAccess>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<SocialMediaAccess>> getSMAListOfUser(String username) {
		try{
			List<SocialMediaAccess> lsma = socialMediaAccessDao.getSMAListOfUser(username);
			System.out.println("Success sma getSMAListOfUser service impl");
			return new ResponseEntity<List<SocialMediaAccess>>(lsma, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in getSMAListOfUser sma: " + e);
			return new ResponseEntity<List<SocialMediaAccess>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
