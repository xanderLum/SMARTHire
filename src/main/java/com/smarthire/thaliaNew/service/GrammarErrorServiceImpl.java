package com.smarthire.thaliaNew.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.Model.GrammarError;
import com.smarthire.thaliaNew.dao.GrammarErrorDao;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;

@Service
public class GrammarErrorServiceImpl  implements GrammarErrorService{

	@Autowired
	GrammarErrorDao grammarErrorDao;

	@Override
	public ResponseEntity<GrammarError> create(GrammarError er) {
		//check first if username and skill exist in table
		//ResponseEntity<GrammarError> flag = read(er.getSentenceDataId());
		
		//if(flag!=null){
			try{
				grammarErrorDao.create(er);
				System.out.println("Success GrammarError service create");
				return new ResponseEntity<GrammarError>(er, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in GrammarError service impl create: " + e);
				return new ResponseEntity<GrammarError>(HttpStatus.BAD_REQUEST);
			}
		//}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
		//	return update(er);
		//}
		
	}
	@Override
	public ResponseEntity<GrammarError> update(GrammarError er) {
		try{
			grammarErrorDao.update(er);
			System.out.println("Success in GrammarError Service Impl update");
			return new ResponseEntity<GrammarError>(er, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in GrammarError Service Impl update: " + e);
			return new ResponseEntity<GrammarError>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<GrammarError> delete(Long id) {
		try{
			GrammarError er = grammarErrorDao.delete(id);
			System.out.println("Success in GrammarError Service Impl delete");
			return new ResponseEntity<GrammarError>(er, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in GrammarError Service Impl delete: " + e);
			return new ResponseEntity<GrammarError>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<GrammarError>> getList() {
		try{
			System.out.println("Here getList CHAR_CATEGORYSCORE service impl");
			List<GrammarError> ler = grammarErrorDao.getList();
			System.out.println("Success getList CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<List<GrammarError>>(ler, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<List<GrammarError>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<GrammarError> read(Long id) {
		try{
			GrammarError er = grammarErrorDao.read(id);
			System.out.println("Success getGrammarErrorByID service impl");
			return new ResponseEntity<GrammarError>(er, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getGrammarErrorByID sma: " + e);
			return new ResponseEntity<GrammarError>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Override
	public ResponseEntity<List<GrammarError>> readAllByfk(Long fkId){
		try{
			System.out.println("Here getList GrammarError service impl");
			List<GrammarError> ljsk = grammarErrorDao.readAllByfk(fkId);
			System.out.println("Success getList GrammarError service impl");
			return new ResponseEntity<List<GrammarError>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList GrammarError service impl: " +e);
			return new ResponseEntity<List<GrammarError>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}