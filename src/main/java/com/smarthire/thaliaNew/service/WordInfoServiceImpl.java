package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.Model.WordInfo;
import com.smarthire.thaliaNew.dao.WordInfoDao;

@Service
public class WordInfoServiceImpl  implements WordInfoService{

	@Autowired
	WordInfoDao wordInfoDao;

	@Override
	public ResponseEntity<WordInfo> create(WordInfo wi) {
		//check first if username and skill exist in table
		//ResponseEntity<WordInfo> flag = read(wi.getData_id());
		
		//if(flag!=null){
			try{
				wordInfoDao.create(wi);
				System.out.println("Success WordInfo service create");
				return new ResponseEntity<WordInfo>(wi, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in WordInfo service impl create: " + e);
				return new ResponseEntity<WordInfo>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		//	return update(wi);
		//}
		
	}
	@Override
	public ResponseEntity<WordInfo> update(WordInfo wi) {
		try{
			wordInfoDao.update(wi);
			System.out.println("Success in WordInfo Service Impl update");
			return new ResponseEntity<WordInfo>(wi, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in WordInfo Service Impl update: " + e);
			return new ResponseEntity<WordInfo>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<WordInfo> delete(Long id) {
		try{
			WordInfo wi = wordInfoDao.delete(id);
			System.out.println("Success in WordInfo Service Impl delete");
			return new ResponseEntity<WordInfo>(wi, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in WordInfo Service Impl delete: " + e);
			return new ResponseEntity<WordInfo>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<WordInfo>> getList() {
		try{
			System.out.println("Here getList CHAR_CATEGORYSCORE service impl");
			List<WordInfo> lwi =wordInfoDao.getList();
			System.out.println("Success getList CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<List<WordInfo>>(lwi, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<List<WordInfo>>(HttpStatus.BAD_REQUEST);
		}
	}
	public ResponseEntity<WordInfo> readByfkWordTag(String socialMediaDataId, String word, String tag)
	{
		try{
			WordInfo syn = wordInfoDao.readByfkWordTag(socialMediaDataId, word, tag);
			System.out.println("Success getWordInfoByID service impl");
			return new ResponseEntity<WordInfo>(syn, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getWordInfoByID sma: " + e);
			return new ResponseEntity<WordInfo>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<WordInfo> read(Long id) {
		try{
			WordInfo syn = wordInfoDao.read(id);
			System.out.println("Success getWordInfoByID service impl");
			return new ResponseEntity<WordInfo>(syn, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getWordInfoByID sma: " + e);
			return new ResponseEntity<WordInfo>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@Override
	public ResponseEntity<List<WordInfo>> readAllByfk(String fkId){
		try{
			System.out.println("Here getList WordInfo service impl");
			List<WordInfo> ljsk = wordInfoDao.readAllByfk(fkId);
			System.out.println("Success getList WordInfo service impl");
			return new ResponseEntity<List<WordInfo>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList WordInfo service impl: " +e);
			return new ResponseEntity<List<WordInfo>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@Override
	public ResponseEntity<List<WordInfo>> readAllByWordTag(String word, String tag){
		try{
			System.out.println("Here getList WordInfo service impl");
			List<WordInfo> ljsk = wordInfoDao.readByWordTag(word, tag);
			System.out.println("Success getList WordInfo service impl");
			return new ResponseEntity<List<WordInfo>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList WordInfo service impl: " +e);
			return new ResponseEntity<List<WordInfo>>(HttpStatus.BAD_REQUEST);
		}
	}
}
