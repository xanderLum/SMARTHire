package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.Model.CategoryPoint;
import com.smarthire.thaliaNew.dao.CategoryPointDao;

@Service
public class CategoryPointServiceImpl  implements CategoryPointService{

	@Autowired
	CategoryPointDao categoryPointDao;

	@Override
	public ResponseEntity<CategoryPoint> create(CategoryPoint cp) {
		//check first if username and skill exist in table
		//ResponseEntity<CategoryPoint> flag = read(cp.getPartPointerId());
		
		//if(flag!=null){
			try{
				categoryPointDao.create(cp);
				System.out.println("Success CategoryPoint service create");
				return new ResponseEntity<CategoryPoint>(cp, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in CategoryPoint service impl create: " + e);
				return new ResponseEntity<CategoryPoint>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CategoryPoint>(HttpStatus.BAD_REQUEST);
		//	return update(cp);
		//}
		
	}
	@Override
	public ResponseEntity<CategoryPoint> update(CategoryPoint cp) {
		try{
			categoryPointDao.update(cp);
			System.out.println("Success in CategoryPoint Service Impl update");
			return new ResponseEntity<CategoryPoint>(cp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in CategoryPoint Service Impl update: " + e);
			return new ResponseEntity<CategoryPoint>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CategoryPoint> delete(Long id) {
		try{
			CategoryPoint cp = categoryPointDao.delete(id);
			System.out.println("Success in CategoryPoint Service Impl delete");
			return new ResponseEntity<CategoryPoint>(cp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in CategoryPoint Service Impl delete: " + e);
			return new ResponseEntity<CategoryPoint>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<CategoryPoint> read(Long id) {
		try{
			CategoryPoint cp = categoryPointDao.read(id);
			System.out.println("Success getCategoryPointByID service impl");
			return new ResponseEntity<CategoryPoint>(cp, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getCategoryPointByID sma: " + e);
			return new ResponseEntity<CategoryPoint>(HttpStatus.BAD_REQUEST);
		}
	}


	@Override
	public ResponseEntity<List<CategoryPoint>> getList() {
		try{
			System.out.println("Here getList CategoryPoint service impl");
			List<CategoryPoint> ljsk = categoryPointDao.getList();
			System.out.println("Success getList CategoryPoint service impl");
			return new ResponseEntity<List<CategoryPoint>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CategoryPoint service impl: " +e);
			return new ResponseEntity<List<CategoryPoint>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<List<CategoryPoint>> readAllByfk(Long fkId){
		try{
			System.out.println("Here getList CategoryPoint service impl");
			List<CategoryPoint> ljsk = categoryPointDao.readAllByfk(fkId);
			System.out.println("Success getList CategoryPoint service impl");
			return new ResponseEntity<List<CategoryPoint>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CategoryPoint service impl: " +e);
			return new ResponseEntity<List<CategoryPoint>>(HttpStatus.BAD_REQUEST);
		}
	}
/*	
	@Override
	public ResponseEntity<List<CategoryPoint>> createList(List<CategoryPoint> c) {
		//check first if username and skill exist in table
		//ResponseEntity<CategoryPoint> flag = read(c.getSentenceDataId());
		
		//if(flag!=null){
			try{
				categoryPointDao.createList(c);
				System.out.println("Success CategoryPoint service create");
				return new ResponseEntity<List<CategoryPoint>>(c, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in CategoryPoint service impl create: " + e);
				return new ResponseEntity<List<CategoryPoint>>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CategoryPoint>(HttpStatus.BAD_REQUEST);
		//	return update(c);
		//}
		
	}
	
*/

}
