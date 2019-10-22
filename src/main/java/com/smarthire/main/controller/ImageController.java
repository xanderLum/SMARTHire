package com.smarthire.main.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.smarthire.xander.databaseRest.FileUploadRC;
import com.smarthire.xander.models.Image;

@Controller
@RequestMapping("/myImage")
public class ImageController {
	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("username") String username, HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String imgUri = SMART_HIRE_URL + "/fileUploadRC/readByUsername/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		ResponseEntity<Image> reemp = restTemplate.getForEntity(imgUri + username+"/", Image.class);
		if (reemp.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Image retrieved Http Status Ok.");
			if (reemp.getBody() != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(reemp.getBody().getData());
			} else {
				System.out.println("No image for username = " + username);
			}
		} else {
			System.out.println("Image retrieved Http Status ERROR. Returning Index");
		}
		response.getOutputStream().close();
	}
	
}
