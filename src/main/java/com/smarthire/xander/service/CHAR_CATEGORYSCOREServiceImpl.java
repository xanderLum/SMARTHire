package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.CHAR_CATEGORYSCOREDao;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

@Service
public class CHAR_CATEGORYSCOREServiceImpl implements CHAR_CATEGORYSCOREService {

	@Autowired
	CHAR_CATEGORYSCOREDao charCategorySoreDao;

	@Override
	public ResponseEntity<CHAR_CATEGORYSCORE> create(CHAR_CATEGORYSCORE cs) {
		//check first if username and skill exist in table
		ResponseEntity<CHAR_CATEGORYSCORE> flag = read(cs.getUsername(), cs.getCategoryName());
		
		if(flag!=null){
			try{
				charCategorySoreDao.create(cs);
				System.out.println("Success CHAR_CATEGORYSCORE service create");
				return new ResponseEntity<CHAR_CATEGORYSCORE>(cs, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in CHAR_CATEGORYSCORE service impl create: " + e);
				return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
			}
		}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
			return update(cs);
		}
		
	}
	@Override
	public ResponseEntity<CHAR_CATEGORYSCORE> update(CHAR_CATEGORYSCORE js) {
		try{
			charCategorySoreDao.update(js);
			System.out.println("Success in CHAR_CATEGORYSCORE Service Impl update");
			return new ResponseEntity<CHAR_CATEGORYSCORE>(js, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in CHAR_CATEGORYSCORE Service Impl update: " + e);
			return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CHAR_CATEGORYSCORE> delete(Long id) {
		try{
			CHAR_CATEGORYSCORE jp = charCategorySoreDao.delete(id);
			System.out.println("Success in CHAR_CATEGORYSCORE Service Impl delete");
			return new ResponseEntity<CHAR_CATEGORYSCORE>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in CHAR_CATEGORYSCORE Service Impl delete: " + e);
			return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<CHAR_CATEGORYSCORE>> getList() {
		try{
			System.out.println("Here getList CHAR_CATEGORYSCORE service impl");
			List<CHAR_CATEGORYSCORE> ljsk = charCategorySoreDao.getList();
			System.out.println("Success getList CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<List<CHAR_CATEGORYSCORE>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<List<CHAR_CATEGORYSCORE>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CHAR_CATEGORYSCORE> read(String username, String categoryName) {
		try{
			CHAR_CATEGORYSCORE jsk = charCategorySoreDao.read(username,categoryName);
			System.out.println("Success read CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<CHAR_CATEGORYSCORE>(jsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch read CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<CHAR_CATEGORYSCORE>> getAllCharCatScoreByUser(String username) {
		try{
			System.out.println("here in getAllCharCatSCOREBYUSER username = "+username);
			List<CHAR_CATEGORYSCORE> ljp = charCategorySoreDao.getAllCharCatScoreByUser(username);
			System.out.println("lgp size = "+ljp.size());
			System.out.println("Success getAllCharCatScoreByUser service impl");
			return new ResponseEntity<List<CHAR_CATEGORYSCORE>>(ljp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in getAllCharCatScoreByUser sma: " + e);
			return new ResponseEntity<List<CHAR_CATEGORYSCORE>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CHAR_CATEGORYSCORE> read(Long id) {
		try{
			CHAR_CATEGORYSCORE c = charCategorySoreDao.read(id);
			System.out.println("Success getAllCharCatScoreByUser service impl");
			return new ResponseEntity<CHAR_CATEGORYSCORE>(c, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getAllCharCatScoreByUser sma: " + e);
			return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		}
	}

}
