package com.smarthire.xander.controller;

import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smarthire.xander.models.CategoryScore;

@Controller
//@RequestMapping("/test")
public class RankingController {
	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";
	public static final String uriGetSMDListOfUser = SMART_HIRE_URL + "/socialMediaData/getSMDListOfUser/";
	
	@RequestMapping(value = "/ranking")
	public ModelAndView rankMain(LinkedList<CategoryScore> list) throws Exception {
		ModelAndView mav = new ModelAndView();
				//LinkedList<CategoryScore> list = new LinkedList<>();
		mav.addObject("0");
		mav.setViewName("ranking");
		return mav;
	}
	public ModelAndView viewDetails(){
		ModelAndView mav = new ModelAndView();
		//LinkedList<CategoryScore> list = new LinkedList<>();
		mav.setViewName("output");
		return mav;
	}
}
