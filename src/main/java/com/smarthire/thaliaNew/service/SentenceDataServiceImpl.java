package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.Model.SentenceData;
import com.smarthire.thaliaNew.dao.SentenceDataDao;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;


@Service
public class SentenceDataServiceImpl  implements SentenceDataService{

	@Autowired
	SentenceDataDao sentenceDataDao;

	@Override
	public ResponseEntity<SentenceData> create(SentenceData sd) {
		//check first if username and skill exist in table
		//ResponseEntity<SentenceData> flag = read(sd.getData_id());
		
		//if(flag!=null){
			try{
				sentenceDataDao.create(sd);
				System.out.println("Success SentenceData service create");
				return new ResponseEntity<SentenceData>(sd, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in SentenceData service impl create: " + e);
				return new ResponseEntity<SentenceData>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		//	return update(sd);
		//}
		
	}
	@Override
	public ResponseEntity<SentenceData> update(SentenceData sd) {
		try{
			sentenceDataDao.update(sd);
			System.out.println("Success in SentenceData Service Impl update");
			return new ResponseEntity<SentenceData>(sd, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in SentenceData Service Impl update: " + e);
			return new ResponseEntity<SentenceData>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<SentenceData> delete(Long id) {
		try{
			SentenceData sd = sentenceDataDao.delete(id);
			System.out.println("Success in SentenceData Service Impl delete");
			return new ResponseEntity<SentenceData>(sd, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in SentenceData Service Impl delete: " + e);
			return new ResponseEntity<SentenceData>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<SentenceData>> getList() {
		try{
			System.out.println("Here getList CHAR_CATEGORYSCORE service impl");
			List<SentenceData> lsd = sentenceDataDao.getList();
			System.out.println("Success getList CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<List<SentenceData>>(lsd, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<List<SentenceData>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<SentenceData> read(Long id) {
		try{
			SentenceData sd = sentenceDataDao.read(id);
			System.out.println("Success getSentenceDataByID service impl");
			return new ResponseEntity<SentenceData>(sd, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getSentenceDataByID sma: " + e);
			return new ResponseEntity<SentenceData>(HttpStatus.BAD_REQUEST);
		}
	}



	@Override
	public ResponseEntity<List<SentenceData>> readAllByfk(String fkId){
		try{
			System.out.println("Here getList SentenceData service impl");
			List<SentenceData> ljsk = sentenceDataDao.readAllByfk(fkId);
			System.out.println("Success getList SentenceData service impl");
			return new ResponseEntity<List<SentenceData>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList SentenceData service impl: " +e);
			return new ResponseEntity<List<SentenceData>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
