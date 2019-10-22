package com.smarthire.main.controller.api.upwork;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/getSMData/upworkData")
public class UpworkAPIController {
	
	private final static String up_key = "bbdd28b9c08ee568911b71c424178382"; //request token
	private final static String up_secret = "76afb9d4dd0bc0b8"; //secret 
	
	@RequestMapping(value = "/testForm")
	public String testForm(Model m) {
		return "testURLInputUpwork";
	}
	
	@RequestMapping(value = "/getData")
	public String getData(Model m, @RequestParam("url") String url) {
		System.out.println(url);
		m.addAttribute("url", url);
		
		String data = "data11";
		m.addAttribute("data", data);
		return "testDataFromUpwork";
	}
	
	//original here -----//
	@RequestMapping(value = "/testForm/getData")
	public String getDatas(Model m, @RequestParam("url") String url) {
		System.out.println(url);
		m.addAttribute("url", url);
		
		String data = "data11";
		m.addAttribute("data", data);
		return "testDataFromUpwork";
	}

}
