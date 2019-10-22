package com.smarthire.xander.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.smarthire.main.models.JobPostApplicants;
import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.CategoryScore;
import com.smarthire.xander.models.FScore;


@Controller
//@RequestMapping("/test")
@RequestMapping("/employer/contentViewSpecificJobPost")

public class Scoring {
	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";
	public static final String uriCreateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/create/";
	public static final String uriReadChar_Category = SMART_HIRE_URL + "/char_catscoreRC/getAllCharCatScoreByUser/";
	public static final String uriUpdateChar_Category = SMART_HIRE_URL + "char_catscoreRC/update/";
	
	//@RequestMapping(value="/eachscore/{username}", method = RequestMethod.GET)
	@RequestMapping(value="/eachscoreHASBEENMOVED", method = RequestMethod.GET)
	public ModelAndView indivView(HttpServletRequest req) throws Exception{
		System.err.println("eachscore reading username");
		String username = req.getParameter("username");
		System.out.println("\neachscore reading uname = "+username);
		ModelAndView mav = new ModelAndView();
		LinkedList<CHAR_CATEGORYSCORE> list = getCharCatScore(username);
		System.out.println("list categoryscore size = "+list.size());
		LinkedList<CategoryScore> listCatScore = new LinkedList<>();
		CategoryScore cs;
		for(CHAR_CATEGORYSCORE ca: list){
			cs = new CategoryScore();
			cs.setCategoryName(ca.getCategoryName());
			cs.setNegative(ca.getNegative());
			cs.setPositive(ca.getPositive());
			cs.setTotalPost(ca.getTotalPost());
			listCatScore.add(cs);
		}
		
		LinkedList<FScore> listProfScore;
		Scorer scorer = new Scorer(listCatScore);
		listProfScore = scorer.getFinalCharScore();
		
		int finalScore = 0;
		for(FScore f: listProfScore){
			finalScore+= f.getFscore();
		}
		
		for(CategoryScore catscore : listCatScore){
			System.out.println(""+catscore.getCategoryName()+"\t"+catscore.getPositive()+"\t"+catscore.getNegative()+"\t"+catscore.getTotalPost());
		}
		
		//mav.addObject(list);
		mav.addObject("FINALSCORE",finalScore);
		mav.addObject("listCategory",listCatScore);
		mav.addObject("score",listProfScore);
		mav.setViewName("mainScoreBoard");
		return mav;
	}
	
//	@RequestMapping(value="/eachscore", method = RequestMethod.GET)
	public String in(Model m, HttpServletRequest req) throws Exception{
		System.out.println("Eachscore here");
		String username = String.valueOf(req.getAttribute("username"));
		System.err.println("eachscore reading username");
		ModelAndView mav = new ModelAndView();
		LinkedList<CHAR_CATEGORYSCORE> list = getCharCatScore(username);
		LinkedList<CategoryScore> listCatScore = new LinkedList<>();
		CategoryScore cs;
		for(CHAR_CATEGORYSCORE ca: list){
			cs = new CategoryScore();
			cs.setCategoryName(ca.getCategoryName());
			cs.setNegative(ca.getNegative());
			cs.setPositive(ca.getPositive());
			cs.setTotalPost(ca.getTotalPost());
			listCatScore.add(cs);
		}
		
		LinkedList<FScore> listProfScore;
		Scorer scorer = new Scorer(listCatScore);
		listProfScore = scorer.getFinalCharScore();
		
		int finalScore = 0;
		for(FScore f: listProfScore){
			finalScore+= f.getFscore();
		}
		
		//mav.addObject(list);
		m.addAttribute("FINALSCORE", finalScore);
		m.addAttribute("listCategoy", listCatScore);
		m.addAttribute("score", listProfScore);

//		mav.addObject("FINALSCORE",finalScore);
//		mav.addObject("listCategory",listCatScore);
//		mav.addObject("score",listProfScore);
//		mav.setViewName("mainScoreBoard");
		
		return "mainScoreBoard";
		
	//	return mav;
	}
	
	/*
	@RequestMapping(value="/mainScorer", method= RequestMethod.POST)
	public ModelAndView scorerMain(LinkedList<CategoryScore> list, String username) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		
		//START Just for saving to database
		CHAR_CATEGORYSCORE ccts;
		for(CategoryScore ctS : list){
			ccts = new CHAR_CATEGORYSCORE(username, ctS.getCategoryName(), ctS.getPositive(), ctS.getNeutral(),
					ctS.getNegative(), ctS.getTotalPost(), 0d);
			
			
			ResponseEntity<CHAR_CATEGORYSCORE> rctS = restTemplate.postForEntity(uriCreateChar_Category,ccts, CHAR_CATEGORYSCORE.class);
			System.out.println("Category Score saved: " + ctS.getCategoryName());
		}
		//END Just for saving to database
		
		
		//START Computer final score
		ModelAndView mav = new ModelAndView();
				//LinkedList<CategoryScore> list = new LinkedList<>();
		
		LinkedList<FScore> listProfScore;
		Scorer scorer = new Scorer(list);
		listProfScore = scorer.getFinalCharScore();
		int finalScore = 0;
		for(FScore f: listProfScore){
			finalScore+= f.getFscore();
		}

		finalScore /= scorer.getOverallOver();
		System.out.println("FINALSCORE : "+finalScore);
		for(CategoryScore k: list){
			System.out.println(k.getCategoryName() + " =  "+k.getPositive() + " "+k.getNegative()+" "+k.getTotalPost());
		}
		
		for(FScore f: listProfScore){
			System.out.println(f.getCategoryName() + " = score = "+f.getFscore());
		}
		//END Computer final score
		
		
		System.out.println("TOTAL PROFILE CHARACTER SCORE : "+finalScore);
		mav.addObject("FINALSCORE",finalScore);
		mav.addObject("listCategory",list);
		mav.addObject("score",listProfScore);
		mav.setViewName("mainScoreBoard");
		
		return mav;
		
	}
	*/
	
	public LinkedList<CHAR_CATEGORYSCORE> getCharCatScore(String username) throws Exception{
		System.out.println("getCharCatScore function");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		ResponseEntity<CHAR_CATEGORYSCORE[]> rctS = restTemplate.getForEntity(uriReadChar_Category+"/"+username+"/", CHAR_CATEGORYSCORE[].class);
		LinkedList<CHAR_CATEGORYSCORE> listCategoryScore = new LinkedList<>();
		for(CHAR_CATEGORYSCORE c: rctS.getBody()){
			listCategoryScore.add(c);
			System.out.println("list category "+c.getCategoryName()+" added");
			System.out.println("yay "+c.getCategoryName()+" added");
			
		}
		
	  return listCategoryScore;
	
	}
	
	public LinkedList<JobSeekerSkills> getSkills(String username, LinkedList<JobSeekerSkills> allSkills) throws Exception{
		System.out.println("getSkills");
		LinkedList<JobSeekerSkills> list = new LinkedList<>();
		for(JobSeekerSkills js: allSkills){
			if(js.getUsername().equals(username) && !js.getSkill_name().startsWith("See")){
				list.add(js);
			}
		}
		
		return list;
	}
	
//	@RequestMapping(value = "/getFinalScore")
	public double getFinalScore(LinkedList<CategoryScore> list, String username) throws Exception {
		LinkedList<FScore> listProfScore;
		Scorer scorer = new Scorer(list);
		System.out.println("getting FinalCharSCORE");
		System.out.println("USERNAME = "+username);
		listProfScore = scorer.getFinalCharScore();
		
		for(FScore f : listProfScore){
			saveScoresToDb(list, username, f.getFscore());
		}
		
		double finalScore = 0;
		for(FScore f: listProfScore){
			finalScore+= f.getFscore();
		}
		finalScore /=scorer.getOverallOver();
		
		if(finalScore == -0.0f){
			finalScore = 0.0f;
		}
		
		for(CategoryScore k: list){
			System.out.println(k.getCategoryName() + " =  "+k.getPositive() + " "+k.getNegative()+" "+k.getTotalPost());
		}
		
		for(FScore f: listProfScore){
			System.out.println(f.getCategoryName() + " = score = "+f.getFscore());
		}
		finalScore = Double.parseDouble(String.format("%.2f", finalScore));
		System.out.println("TOTAL PROFILE CHARACTER SCORE : "+finalScore+" /overall");
		
		return finalScore;
	}
	
//	@RequestMapping(value = "/saveScoresToDb")
	public boolean saveScoresToDb(LinkedList<CategoryScore> list, String username, double f_char_score) throws Exception {
		try{
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
				protected boolean hasError(HttpStatus statusCode) {
					return false;
				}
			});
			
			//START Just for saving to database
			CHAR_CATEGORYSCORE ccts;
			for(CategoryScore ctS : list){
				ccts = new CHAR_CATEGORYSCORE(username, ctS.getCategoryName(), ctS.getPositive(), ctS.getNeutral(),
						ctS.getNegative(), ctS.getTotalPost(), String.valueOf(f_char_score));
				
				ResponseEntity<CHAR_CATEGORYSCORE> rctS = restTemplate.postForEntity(uriUpdateChar_Category,ccts, CHAR_CATEGORYSCORE.class);
				System.out.println("Category Score saved: " + ctS.getCategoryName());
			}
			//END Just for saving to database
		
		}
		catch(Exception e){
			System.out.println("Error in saving db in category score");
			return false;
		}
		return true;
	}
	
	
	/*
	@RequestMapping(value = "/scoreBoard")
	public ModelAndView score() throws Exception {
		ModelAndView mav = new ModelAndView();
		String username = "kent";
		
		LinkedList<CategoryScore> list = new LinkedList<>();
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		
		
		
		CategoryScore cs = new CategoryScore();
		cs.setCategoryName("sex");
		cs.setPositive(70);
		cs.setNegative(-30);
		cs.setNeutral(5);
		cs.setTotalPost(100);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("charity");
		cs.setPositive(50);
		cs.setNegative(-50);
		cs.setNeutral(0);
		cs.setTotalPost(100);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("gun");
		cs.setPositive(60);
		cs.setNegative(-40);
		cs.setNeutral(15);
		cs.setTotalPost(100);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("drug");
		cs.setPositive(15);
		cs.setNegative(-5);
		cs.setNeutral(0);
		cs.setTotalPost(20);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("alcohol");
		cs.setPositive(45);
		cs.setNegative(-55);
		cs.setNeutral(10);
		cs.setTotalPost(100);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("profane");
		cs.setPositive(55);
		cs.setNegative(-45);
		cs.setNeutral(20);
		cs.setTotalPost(100);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("politics");
		cs.setPositive(70);
		cs.setNegative(-30);
		cs.setNeutral(0);
		cs.setTotalPost(100);
		list.add(cs);
		
		cs = new CategoryScore();
		cs.setCategoryName("grammar_spelling");
		cs.setPositive(80);
		cs.setNegative(-20);
		cs.setNeutral(0);
		cs.setTotalPost(100);
		list.add(cs);
		
		
		CHAR_CATEGORYSCORE ccts;
		for(CategoryScore ctS : list){
			ccts = new CHAR_CATEGORYSCORE();
			ccts = new CHAR_CATEGORYSCORE(username, ctS.getCategoryName(), ctS.getPositive(), ctS.getNeutral(),
					ctS.getNegative(), ctS.getTotalPost(), 0d);
			
			ResponseEntity<CHAR_CATEGORYSCORE> rctS = restTemplate.postForEntity(uriCreateChar_Category,ccts, CHAR_CATEGORYSCORE.class);
			System.out.println("Category Score saved: " + ctS.getCategoryName());
		}
		
		LinkedList<FScore> listProfScore;
		Scorer scorer = new Scorer(list);
		
		listProfScore = scorer.getFinalCharScore();
		
		int finalScore = 0;
		for(FScore f: listProfScore){
			finalScore+= f.getFscore();
		}
		
		
		
		for(CategoryScore k: list){
			System.out.println(k.getCategoryName() + " =  "+k.getPositive() + " "+k.getNeutral()+" "+k.getNegative()+" "+k.getTotalPost() );
		}
		
		for(FScore f: listProfScore){
			System.out.println(f.getCategoryName() + " = score = "+f.getFscore());
		}
		
		
		System.out.println("TOTAL PROFILE CHARACTER SCORE : "+finalScore+" / 800");
		mav.addObject("FINALSCORE",finalScore);
		mav.addObject("listCategory",list);
		mav.addObject("score",listProfScore);
		mav.setViewName("scoreBoard");
		
		System.out.println("Scoring Controller");
		
		
		
		return mav;
	}
	*/
	
}
