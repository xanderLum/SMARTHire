package com.smarthire.thaliaNew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.thaliaNew.dao.BackendProcessStatusDao;

@Service
public class BackendProcessStatusServiceImpl implements BackendProcessStatusService{

	@Autowired
	BackendProcessStatusDao backendProcessStatusDao;

	@Override
	public ResponseEntity<BackendProcessStatus> create(BackendProcessStatus bps) {
		//check first if username and skill exist in table
		ResponseEntity<BackendProcessStatus> flag = read(bps.getData_id());
		
		if(flag!=null){
			try{
				backendProcessStatusDao.create(bps);
				System.out.println("Success BackendProcessStatus service create");
				return new ResponseEntity<BackendProcessStatus>(bps, HttpStatus.OK);
			}
			catch(Exception e){
				System.out.println("catch in BackendProcessStatus service impl create: " + e);
				return new ResponseEntity<BackendProcessStatus>(HttpStatus.BAD_REQUEST);
			}
		}else{
			//System.out.println("Returning bad request");
			//return new ResponseEntity<CHAR_CATEGORYSCORE>(HttpStatus.BAD_REQUEST);
			return update(bps);
		}
		
	}
	@Override
	public ResponseEntity<BackendProcessStatus> update(BackendProcessStatus bps) {
		try{
			backendProcessStatusDao.update(bps);
			System.out.println("Success in BackendProcessStatus Service Impl update");
			return new ResponseEntity<BackendProcessStatus>(bps, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in BackendProcessStatus Service Impl update: " + e);
			return new ResponseEntity<BackendProcessStatus>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<BackendProcessStatus> delete(Long id) {
		try{
			BackendProcessStatus jp = backendProcessStatusDao.delete(id);
			System.out.println("Success in BackendProcessStatus Service Impl delete");
			return new ResponseEntity<BackendProcessStatus>(jp, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch in BackendProcessStatus Service Impl delete: " + e);
			return new ResponseEntity<BackendProcessStatus>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<List<BackendProcessStatus>> getList() {
		try{
			System.out.println("Here getList CHAR_CATEGORYSCORE service impl");
			List<BackendProcessStatus> ljsk = backendProcessStatusDao.getList();
			System.out.println("Success getList CHAR_CATEGORYSCORE service impl");
			return new ResponseEntity<List<BackendProcessStatus>>(ljsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch getList CHAR_CATEGORYSCORE service impl: " +e);
			return new ResponseEntity<List<BackendProcessStatus>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<BackendProcessStatus> read(String socialMediaDataId) {
		try{
			BackendProcessStatus jsk = backendProcessStatusDao.read(socialMediaDataId);
			System.out.println("Success read BackendProcessStatus service impl");
			return new ResponseEntity<BackendProcessStatus>(jsk, HttpStatus.OK);
		}
		catch(Exception e){
			System.out.println("Catch read BackendProcessStatus service impl: " +e);
			return new ResponseEntity<BackendProcessStatus>(HttpStatus.BAD_REQUEST);
		}
	}
	@Override
	public ResponseEntity<BackendProcessStatus> read(Long id) {
		try{
			BackendProcessStatus bps = backendProcessStatusDao.read(id);
			System.out.println("Success getBackendProcessStatusByID service impl");
			return new ResponseEntity<BackendProcessStatus>(bps, HttpStatus.OK);
		}catch(Exception e){
			System.out.println("Catch in getBackendProcessStatusByID sma: " + e);
			return new ResponseEntity<BackendProcessStatus>(HttpStatus.BAD_REQUEST);
		}
	}



}
