package com.smarthire.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.main.dao.SocialMediaDataDao;
import com.smarthire.main.models.SocialMediaData;

@Service
public class SocialMediaDataServiceImpl implements SocialMediaDataService{

	@Autowired
	SocialMediaDataDao socialMediaDataDao;

	@Override
	public ResponseEntity<SocialMediaData> create(SocialMediaData smd) {
		try {
			socialMediaDataDao.create(smd);
			System.out.println("Success in create SocialMediaAccess");
			return new ResponseEntity<SocialMediaData>(smd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in create SocialMediaAccess:" + e);
			return new ResponseEntity<SocialMediaData>(HttpStatus.BAD_REQUEST);
		}
	}
	@Override
	public ResponseEntity<SocialMediaData> update(SocialMediaData smd) {
		try {
			socialMediaDataDao.update(smd);
			System.out.println("Success in update SocialMediaAccess");
			return new ResponseEntity<SocialMediaData>(smd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in update SocialMediaAccess:" + e);
			return new ResponseEntity<SocialMediaData>(HttpStatus.BAD_REQUEST);
		}
	}
	@Override
	public ResponseEntity<SocialMediaData> read(Long id) {
		try {
			SocialMediaData smd = socialMediaDataDao.read(id);
			System.out.println("Success in read SocialMediaData");
			return new ResponseEntity<SocialMediaData>(smd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in read SocialMediaAccess:" + e);
			return new ResponseEntity<SocialMediaData>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<SocialMediaData> delete(Long id) {
		try {
			SocialMediaData smd = socialMediaDataDao.delete(id);
			System.out.println("Success in delete SocialMediaData");
			return new ResponseEntity<SocialMediaData>(smd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in delete SocialMediaAccess:" + e);
			return new ResponseEntity<SocialMediaData>(HttpStatus.BAD_REQUEST);
		}
	}
	@Override
	public ResponseEntity<List<SocialMediaData>> getList() {
		try {
			List<SocialMediaData> lsmd = socialMediaDataDao.getList();
			System.out.println("Success smd getlist service impl");
			return new ResponseEntity<List<SocialMediaData>>(lsmd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in getList SocialMediaAccess:" + e);
			return new ResponseEntity<List<SocialMediaData>>(HttpStatus.BAD_REQUEST);
		}
	}
	@Override
	public ResponseEntity<List<SocialMediaData>> getSMDListOfUser(String username) {
		try {
			List<SocialMediaData> lsmd = socialMediaDataDao.getSMDListOfUser(username);
			System.out.println("Success smd getSMDListOfUser service impl");
			return new ResponseEntity<List<SocialMediaData>>(lsmd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in getSMDListOfUser SocialMediaAccess:" + e);
			return new ResponseEntity<List<SocialMediaData>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<SocialMediaData> readStringId(String data_id) {
		try {
			SocialMediaData smd = socialMediaDataDao.readStringId(data_id);
			System.out.println("Success in read SocialMediaData");
			return new ResponseEntity<SocialMediaData>(smd, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error in read SocialMediaAccess:" + e);
			return new ResponseEntity<SocialMediaData>(HttpStatus.BAD_REQUEST);
		}
	}
}
