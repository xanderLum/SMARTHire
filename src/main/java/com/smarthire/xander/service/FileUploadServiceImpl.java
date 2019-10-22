package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.FileUploadDao;
import com.smarthire.xander.models.Image;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	FileUploadDao fileUploadDao;
	
	@Override
	public ResponseEntity<Image> create(Image jp) {
		try {
			fileUploadDao.create(jp);
			return new ResponseEntity<Image>(jp, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Image> update(Image jp) {
		try {
			fileUploadDao.update(jp);
			return new ResponseEntity<Image>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Image>(jp, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Image> delete(Long id) {
		try {
			Image jp = fileUploadDao.delete(id);
			return new ResponseEntity<Image>(jp, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Image>> getList() {
		try{
			List<Image> ljp = fileUploadDao.getList();
			System.out.println("Success in Image Service Impl getList");
			return new ResponseEntity<List<Image>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Image Service Impl getList: " + e);
			return new ResponseEntity<List<Image>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Image> read(Long id) {
		try{
			Image jp = fileUploadDao.read(id);
			System.out.println("Success in Image Service Impl read");
			return new ResponseEntity<Image>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Image Service Impl read: " + e);
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Image> getImgeByUsername(String username) {
		try{
			System.out.println("Image Service Impl getImageByUsername before");
			Image jp = fileUploadDao.getImgeByUsername(username);
			System.out.println("Success in Image Service Impl getImgeByUsername");
			return new ResponseEntity<Image>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Image Service Impl getImgeByUsername: " + e);
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Image> save(Image img) {
		try {
			fileUploadDao.save(img);
			return new ResponseEntity<Image>(img, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
