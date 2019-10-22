package com.smarthire.xander.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.xander.dao.DocumentsDao;
import com.smarthire.xander.models.Documents;

@Service
public class DocumentsServiceImpl implements DocumentsService{

	@Autowired
	DocumentsDao documentsDao;

	@Override
	public ResponseEntity<Documents> create(Documents doc) {
		// TODO Auto-generated method stub
		ResponseEntity<Documents> flag = read(doc.getUsername(), doc.getFilename());

		if(flag!=null){
			try{
				documentsDao.create(doc);
				System.out.println("Success Documents service create");
				return new ResponseEntity<Documents>(doc, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in Documents service impl create: " + e);
				return new ResponseEntity<Documents>(HttpStatus.BAD_REQUEST);
			}
		}else{
			return update(doc);
		}
	}

	@Override
	public ResponseEntity<Documents> update(Documents doc) {
		try{
			documentsDao.update(doc);
			System.out.println("Success in Documents Service Impl update");
			return new ResponseEntity<Documents>(doc, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Documents Service Impl update: " + e);
			return new ResponseEntity<Documents>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Documents> delete(Long id) {
		try{
			Documents doc = documentsDao.delete(id);
			System.out.println("Success in Documents Service Impl delete");
			return new ResponseEntity<Documents>(doc, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in Documents Service Impl delete: " + e);
			return new ResponseEntity<Documents>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Documents>> getList() {
		try{
			System.out.println("Here getList Documents service impl");
			List<Documents> doc = documentsDao.getList();
			System.out.println("Success getList Documents service impl");
			return new ResponseEntity<List<Documents>>(doc, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList Documents service impl: " +e);
			return new ResponseEntity<List<Documents>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Documents> read(Long id) {
		try{
			Documents doc = documentsDao.read(id);
			System.out.println("Success read Documents service impl");
			return new ResponseEntity<Documents>(doc, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch read Documents service impl: " +e);
			return new ResponseEntity<Documents>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<Documents>> getAllDocumentsByUsername(String username) {
		try{
			System.out.println("here in getAllDocumentsByUsername username = "+username);
			List<Documents> doc = documentsDao.getAllDocumentsByUsername(username);
			System.out.println("lgp size = "+doc.size());
			System.out.println("Success getAllDocumentsByUsername service impl");
			return new ResponseEntity<List<Documents>>(doc, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in getAllDocumentsByUsername sma: " + e);
			return new ResponseEntity<List<Documents>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Documents> read(String username, String filename) {
		try{
			Documents doc = documentsDao.read(username, filename);
			System.out.println("Success read Documents service impl");
			return new ResponseEntity<Documents>(doc, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch read Documents service impl: " +e);
			return new ResponseEntity<Documents>(HttpStatus.BAD_REQUEST);
		}
	}

}
