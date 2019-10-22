package com.smarthire.thaliaNew.controller.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.thaliaNew.Model.BackendProcessStatus;
import com.smarthire.thaliaNew.service.BackendProcessStatusService;

@RestController
@RequestMapping(value="/backendProcessStatusRC")
public class BackendProcessStatusRC {
	
	@Autowired
	BackendProcessStatusService backendProcessStatusService;
	
	@RequestMapping(value="/create/",method=RequestMethod.POST)
	public ResponseEntity<BackendProcessStatus> create(@RequestBody BackendProcessStatus bps){	
		return backendProcessStatusService.create(bps);
	}
	
	@RequestMapping(value="/update/",method=RequestMethod.PUT)
	public ResponseEntity<BackendProcessStatus> update(@RequestBody BackendProcessStatus bps){
		return backendProcessStatusService.update(bps);
	}
	
	@RequestMapping(value="/read/{socialMediaDataId}/",method=RequestMethod.GET)
	public ResponseEntity<BackendProcessStatus> read(@PathVariable String socialMediaDataId){
		return backendProcessStatusService.read(socialMediaDataId);
	}
	
	@RequestMapping(value="/delete/{id}/",method=RequestMethod.DELETE)
	public ResponseEntity<BackendProcessStatus> delete(@PathVariable Long id){
		return backendProcessStatusService.delete(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ResponseEntity<List<BackendProcessStatus>> getList(){
		return backendProcessStatusService.getList();
	}
	
	
}
