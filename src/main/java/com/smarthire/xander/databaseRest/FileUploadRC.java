package com.smarthire.xander.databaseRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.xander.models.Image;
import com.smarthire.xander.service.FileUploadService;

@RestController
@RequestMapping(value = "/fileUploadRC")
public class FileUploadRC {
	@Autowired
	FileUploadService fileUploadService;

	@RequestMapping(value = "/create/", method = RequestMethod.POST)
	public ResponseEntity<Image> create(@RequestBody Image jps) {
		return fileUploadService.create(jps);
	}

	@RequestMapping(value = "/update/", method = RequestMethod.PUT)
	public ResponseEntity<Image> update(@RequestBody Image jps) {
		return fileUploadService.update(jps);
	}

	@RequestMapping(value = "/read/{id}/", method = RequestMethod.GET)
	public ResponseEntity<Image> read(@PathVariable Long id) {
		return fileUploadService.read(id);
	}

	@RequestMapping(value = "/delete/{id}/", method = RequestMethod.DELETE)
	public ResponseEntity<Image> delete(@PathVariable Long id) {
		return fileUploadService.delete(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Image>> getList() {
		return fileUploadService.getList();
	}
	
	@RequestMapping(value = "/readByUsername/{username}/", method = RequestMethod.GET)
	public ResponseEntity<Image> getImgeByUsername(@PathVariable String username) {
		return fileUploadService.getImgeByUsername(username);
	}
	
	@RequestMapping(value = "/save/", method = RequestMethod.POST)
	public ResponseEntity<Image> save(@RequestBody Image jps) {
		return fileUploadService.save(jps);
	}
}
