package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.Model.GlossInfo;
import com.smarthire.thaliaNew.dao.GlossInfoDao;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

@Service
public class GlossInfoServiceImpl  implements GlossInfoService{

	@Autowired
	GlossInfoDao glossInfoDao;

	@Override
	public ResponseEntity<GlossInfo> create(GlossInfo gi) {
		//check first if username and skill exist in table
		//ResponseEntity<GlossInfo> flag = read(gi.getWordInfoId());
		
		//if(flag!=null){
			try{
				glossInfoDao.create(gi);
				System.out.println("Success GlossInfo service create");
				return new ResponseEntity<GlossInfo>(gi, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in GlossInfo service impl create: " + e);
				return new ResponseEntity<GlossInfo>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		//	return update(gi);
		//}
		
	}
	@Override
	public ResponseEntity<GlossInfo> update(GlossInfo gi) {
		try{
			glossInfoDao.update(gi);
			System.out.println("Success in GlossInfo Service Impl update");
			return new ResponseEntity<GlossInfo>(gi, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in GlossInfo Service Impl update: " + e);
			return new ResponseEntity<GlossInfo>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<GlossInfo> delete(Long id) {
		try{
			GlossInfo gi = glossInfoDao.delete(id);
			System.out.println("Success in GlossInfo Service Impl delete");
			return new ResponseEntity<GlossInfo>(gi, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in GlossInfo Service Impl delete: " + e);
			return new ResponseEntity<GlossInfo>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<GlossInfo>> getList() {
		try{
			System.out.println("Here getList CHAR_CATEGORYSCORE service impl");
			List<GlossInfo> lgi = glossInfoDao.getList();
			System.out.println("Success getList CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<List<GlossInfo>>(lgi, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<List<GlossInfo>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<GlossInfo> read(Long id) {
		try{
			GlossInfo gi = glossInfoDao.read(id);
			System.out.println("Success getGlossInfoByID service impl");
			return new ResponseEntity<GlossInfo>(gi, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getGlossInfoByID sma: " + e);
			return new ResponseEntity<GlossInfo>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<GlossInfo> readByfkWSId(Long fkId, String wsId) {
		try{
			GlossInfo gi = glossInfoDao.readByfkWSID(fkId, wsId);
			System.out.println("Success getGlossInfoByID service impl");
			return new ResponseEntity<GlossInfo>(gi, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getGlossInfoByID sma: " + e);
			return new ResponseEntity<GlossInfo>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<List<GlossInfo>> readAllByfk(Long fkId){
		try{
			System.out.println("Here getList GlossInfo service impl");
			List<GlossInfo> ljsk = glossInfoDao.readAllByfk(fkId);
			System.out.println("Success getList GlossInfo service impl");
			return new ResponseEntity<List<GlossInfo>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList GlossInfo service impl: " +e);
			return new ResponseEntity<List<GlossInfo>>(HttpStatus.BAD_REQUEST);
		}
	}
/*	
	@Override
	public ResponseEntity<List<GlossInfo>> createList(List<GlossInfo> c) {
		//check first if username and skill exist in table
		//ResponseEntity<GlossInfo> flag = read(c.getSentenceDataId());
		
		//if(flag!=null){
			try{
				glossInfoDao.createList(c);
				System.out.println("Success GlossInfo service create");
				return new ResponseEntity<List<GlossInfo>>(c, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in GlossInfo service impl create: " + e);
				return new ResponseEntity<List<GlossInfo>>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<GlossInfo>(HttpStatus.BAD_REQUEST);
		//	return update(c);
		//}
		
	}
	*/
}
