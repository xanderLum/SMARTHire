package com.smarthire.main.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.smarthire.main.models.Employer;
import com.smarthire.main.models.JobPost;
import com.smarthire.main.models.JobSeeker;
import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.main.models.SocialMediaData;
import com.smarthire.thaliaNew.Process.DataCleaner;
import com.smarthire.thaliaNew.Process.MainProcess;
import com.smarthire.xander.controller.Monitor;
import com.smarthire.xander.controller.SendMailSenderService;
import com.smarthire.xander.controller.SkillMonitor;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.JobAlert;
import com.smarthire.xander.models.JobPostSkills;

@Controller
public class HomeController {
	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";
	public static final String uriGetAllSMData = SMART_HIRE_URL + "/socialMediaData/";
	public static final String uriUpdateSMD = SMART_HIRE_URL + "/socialMediaData/update/";

	// doAsyncProcess uris
	public static final String uriGetSMDataOfUser = SMART_HIRE_URL + "/socialMediaData/getSMDListOfUser/";
	public static final String uriGetAllSeekers = SMART_HIRE_URL + "/jobSeeker/";
	public static final String uriReadChar_Category = SMART_HIRE_URL + "/char_catscoreRC/read/";
	public static final String uriCreateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/create/";
	public static final String uriUpdateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/update/";

	// jobAlerts
	public static final String uriGetAllEmployers = SMART_HIRE_URL + "/employerRC/";
	public static final String uriGetAllJobPosts = SMART_HIRE_URL + "/jobPostRC/";
	public static final String uriGetAllJobPostSkillsReqByID = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/";
	public static final String uriJobAlerts = SMART_HIRE_URL + "/jobAlertsRC/";
	public static final String uriGetAllJobSeekerSkillsByUsername = SMART_HIRE_URL
			+ "/jobSeekerSkills/getAllSkillsByUsername/";

	public static final String uriReadAllJobSeeker = SMART_HIRE_URL + "/jobSeeker/";
	public static final String uriReadJobSeekerSkills = SMART_HIRE_URL + "/jobSeekerSkills/";
	public static final String uriCreateJobAlert = SMART_HIRE_URL + "/jobAlertRC/create/";

	public static final String uriReadEmployerByUsername = SMART_HIRE_URL + "/employerRC/read/";
	public static final String uriReadJobPost = SMART_HIRE_URL + "/jobPostRC/read/";
	public static final String uriReadJobPostSkillsReq = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/";

	@RequestMapping(value = "/test")
	public String testPage() {
		System.out.println("testPage()");
		return "testPage";
	}

	//this function is triggered for pre population of data
	//this redirects to a test page
	//this enables testing of asynchronous process of retrieving data from social networking sites
	@Async
	public void doAsyncProcessCharacter(String username) {
		// start asynchronous process
		System.err.println(">>>creating new thread async...");
		System.err.println(">>>username doAsync = " + username);
		System.out.println("start category scoring...");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<SocialMediaData[]> allSMDataUser = restTemplate.getForEntity(uriGetSMDataOfUser + username + "/",
				SocialMediaData[].class);

		ArrayList<SocialMediaData> alsmd = new ArrayList<>();
		for (SocialMediaData smData : allSMDataUser.getBody()) {
			alsmd.add(smData);
		}

		MainProcess pc = new MainProcess();
		pc.setUsername(username);
		try {
			LinkedList<CHAR_CATEGORYSCORE> llcs = new LinkedList<>();
			pc.main(alsmd);
			llcs = pc.getLlcs();
			System.out.println(">>>Printing collected CHAR_CATEGORYSCORE");
			for (CHAR_CATEGORYSCORE c : llcs) {
				System.out.println(c.toString());
			}
			System.out.println();
			Monitor monitor = new Monitor();
			monitor.startRealMonitor(llcs);
			LinkedList<CHAR_CATEGORYSCORE> cscoreList = monitor.getCscoreList();
			System.out.println("\n======start print of category scores for username : " + username);
			System.out.println("CHAR_CATEGORYSCORE DATA LIST");
			for (CHAR_CATEGORYSCORE ccts : cscoreList) {
				System.out.println("" + ccts.toString());
				System.out.println("Ready to update char_categoryscore table");
				ResponseEntity<CHAR_CATEGORYSCORE> readCts = restTemplate.getForEntity(
						uriReadChar_Category + "/" + username + "/" + ccts.getCategoryName() + "/",
						CHAR_CATEGORYSCORE.class);
				System.out.println("read record char category = " + ccts.getCategoryName());
				// readCts.getStatusCode().equals(HttpStatus.OK)
				if (readCts.getBody() != null) {
					CHAR_CATEGORYSCORE addNewCCTS = new CHAR_CATEGORYSCORE();
					addNewCCTS.setCategoryName(readCts.getBody().getCategoryName());
					addNewCCTS.setId(readCts.getBody().getId());
					addNewCCTS.setNegative(readCts.getBody().getNegative() + ccts.getNegative());
					addNewCCTS.setPositive(readCts.getBody().getPositive() + ccts.getPositive());
					addNewCCTS.setNeutral(readCts.getBody().getNegative() + ccts.getNeutral());
					addNewCCTS.setTotalPost(
							addNewCCTS.getNegative() + addNewCCTS.getPositive() + addNewCCTS.getNeutral());
					addNewCCTS.setUsername(readCts.getBody().getUsername());
					addNewCCTS.setCategoryScore(readCts.getBody().getCategoryScore());
					restTemplate.put(uriUpdateChar_Category, addNewCCTS);
					System.out.println("updated record char category = " + ccts.getCategoryName());
				} else {
					System.out.println("No char_category score found for = " + username);
					System.out.println("creating row...");
					if (ccts.getCategoryScore() == Double.NaN) {
						System.err.println("detected NAN");
						ccts.setCategoryScore(0.0);
					}
					ResponseEntity<CHAR_CATEGORYSCORE> rctS = restTemplate.postForEntity(uriCreateChar_Category, ccts,
							CHAR_CATEGORYSCORE.class);
					if (rctS.getStatusCode().equals(HttpStatus.OK)) {
						System.out.println("Created row!");
					} else {
						System.out.println("Failed to create row char_categoryScore");
					}
				}
			}
			System.out.println("Updated char_categoryScore table for username! = " + username);

		} catch (Exception e) {
			System.out.println("EEEERRRRRRRORRRR");
			e.printStackTrace();
		}

		System.out.println("PROCESS ASYNC DONE! ");
	}
	//end asynchronous function
		
	
	// this function enables pre population of job alerts table
	// start function
	@RequestMapping(value = "/populateJobAlerts")
	public ModelAndView populateJobAlerts() {
		System.out.println("populate char score");
		ModelAndView model = new ModelAndView();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		LinkedList<JobSeeker> allSeekers = new LinkedList<>();
		ResponseEntity<JobSeeker[]> reGetAllJobSeeker = restTemplate.getForEntity(uriReadAllJobSeeker,
				JobSeeker[].class);
		if (reGetAllJobSeeker.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Success retrieved all seekers");
			for (JobSeeker s : reGetAllJobSeeker.getBody()) {
				allSeekers.add(s);
			}
		} else {
			System.err.println("Unable to load all seekers line 172;");
		}

		LinkedList<JobSeekerSkills> allSkillsList = new LinkedList<>();
		ResponseEntity<JobSeekerSkills[]> allSkills = restTemplate.getForEntity(uriReadJobSeekerSkills + "/",
				JobSeekerSkills[].class);

		for (JobSeekerSkills jSkills : allSkills.getBody()) {
			allSkillsList.add(jSkills);
		}

		ResponseEntity<JobPost[]> getAllJobPost = restTemplate.getForEntity(uriGetAllJobPosts, JobPost[].class);
		if (getAllJobPost.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println(">>Retrieved posts");
			for (JobPost jp : getAllJobPost.getBody()) {
				LinkedList<String> jobPostSkillsReq = new LinkedList<>();
				ResponseEntity<JobPostSkills[]> getAllJobPostSkillsReq = restTemplate
						.getForEntity(uriGetAllJobPostSkillsReqByID + jp.getJob_post_id() + "/", JobPostSkills[].class);
				if (getAllJobPostSkillsReq.getStatusCode().equals(HttpStatus.OK)) {
					System.out.println(">>>Retrieved JobPostSkills");
					for (JobPostSkills s : getAllJobPostSkillsReq.getBody()) {
						jobPostSkillsReq.add(s.getSkill_name());
					}
					if (jobPostSkillsReq.isEmpty()) {
						System.out.println("JOB POST ID has empty skills req.. skip..");
					} else {
						LinkedList<JobAlert> seekerNeedAlertList = new LinkedList<>();
						for (JobSeeker js : allSeekers) {
							LinkedList<String> seekerSkills = new LinkedList<>();
							for (JobSeekerSkills jSkills : allSkillsList) {
								if (js.getUsername().equals(jSkills.getUsername())) {
									seekerSkills.add(jSkills.getSkill_name());
								}
							}
							// check skills matching : jobseeker skills vs
							// jobPost
							// skills req
							System.out.println("seekerSkills size = " + seekerSkills.size());

							System.out.println("jobPostSkillsReq size = " + jobPostSkillsReq.size());

							SkillMonitor skillMonitor = new SkillMonitor(seekerSkills, jobPostSkillsReq);
							double skill_score = skillMonitor.computeSkillScore();
							LinkedList<String> matchedSkills = skillMonitor.getMatchedJsSkills();
							String keywords = "";
							for (String str : matchedSkills) {
								keywords = keywords.concat(str + ",");
							}
							if (skill_score <= 0.0) {
								// skip not recommended //no skills match
							} else {
								System.out.println(">>>Relevant Skills Matched");
								JobAlert j = new JobAlert();
								j.setAlertName(jp.getJob_title());
								j.setLocation(jp.getLocation());
								j.setJob_post_id(jp.getJob_post_id());
								j.setJsUsername(js.getUsername());
								j.setStatus("Not yet Applied");
								j.setDateCreated(jp.getDate_posted());
								j.setKeywords(keywords.substring(0, keywords.length() - 1));
								seekerNeedAlertList.add(j);
								ResponseEntity<JobAlert> reCreateJobAlert = restTemplate
										.postForEntity(uriCreateJobAlert, j, JobAlert.class);
								if (reCreateJobAlert.getStatusCode().equals(HttpStatus.OK)) {
									System.out.println(">>>Success create job Alert!");
								} else {
									System.err.println(">>>Failed to create job alert!");
								}
							}
						}
					}
				}
			}

			model.addObject("message", "Success");
		} else {
			System.err.println(">>Failed to retrieved posts");
			model.addObject("message", "Failed");
		}

		model.setViewName("cleanData");
		return model;
	}
	// end populate job alerts function

	// this function pre populate character score table
	// start function
	@RequestMapping(value = "/populateCharScore")
	public ModelAndView populateCharScore() {
		System.out.println("Populating character score table...");
		ModelAndView model = new ModelAndView();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		ResponseEntity<JobSeeker[]> reGetAllSeekers = restTemplate.getForEntity(uriGetAllSeekers, JobSeeker[].class);
		if (reGetAllSeekers.getStatusCode().equals(HttpStatus.OK)) {
			for (JobSeeker js : reGetAllSeekers.getBody()) {
				doAsyncProcessCharacter(js.getUsername());
			}
			model.addObject("message", "Success! populate char score");
		} else {
			System.err.println("populateCharScore err getAllSeekers");
			model.addObject("message", "Unsuccessful populate char score");
		}
		model.setViewName("cleanData");
		return model;
	}
	// end populate character score table function

	// this function enables update of SMData table in the database
	// the DataCleaner module can be updated and tested through this function
	// start clead social media data table
	@RequestMapping(value = "/cleanSMData")
	public ModelAndView cleanSMData() {
		System.out.println("Cleaning SMData to usable format");
		ModelAndView model = new ModelAndView();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<SocialMediaData[]> readAllSMD = restTemplate.getForEntity(uriGetAllSMData,
				SocialMediaData[].class);
		if (readAllSMD != null) {
			System.out.println("Retrieved data! Start Cleaning...");
			DataCleaner dc = new DataCleaner();
			List<SocialMediaData> smdListNew = new LinkedList<>();
			SocialMediaData smdNew = new SocialMediaData();
			for (SocialMediaData smd : readAllSMD.getBody()) {
				smdNew = smd;
				smdNew.setUsable_data(dc.cleanText(smdNew.getData()));
				smdListNew.add(smdNew);
			}
			System.out.println("Updating smd records");
			for (SocialMediaData smd : smdListNew) {
				restTemplate.put(uriUpdateSMD, smd);
				System.out.println("Updated record no.. " + smdListNew.indexOf(smd));
			}

			model.addObject("message", "Sucess update!");
		} else {
			System.out.println("No SMData retrieved");

			model.addObject("message", "Unsucessful update!");
		}
		model.setViewName("cleanData");
		return model;
	}
	// end cleaning of social media data table

	// start home page
	@RequestMapping(value = "/")
	public ModelAndView blankpage1() {
		System.out.println("Welcome! This is HOME.");
		ModelAndView model = new ModelAndView();

		// prepare recent jobs first
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		// jobSpotlight are jobs created 3 months recent to this query date
		ResponseEntity<JobPost[]> readAllJobPosts = restTemplate.getForEntity(uriGetAllJobPosts, JobPost[].class);
		LinkedList<JobPost> availableJobs = new LinkedList<>();
		if (readAllJobPosts.getBody() != null) {
			JobPost[] jposts = readAllJobPosts.getBody();
			for (JobPost j : jposts) {
				if (j.getJob_status().equals("available")) {
					availableJobs.add(j);
				}
			}
			// sort available jobs according to date
			Collections.sort(availableJobs, new Comparator<JobPost>() {
				public int compare(JobPost o1, JobPost o2) {
					String x1 = String.valueOf(o1.getDate_posted());
					String x2 = String.valueOf(o2.getDate_posted());
					int sComp = x2.compareTo(x1);
					if (sComp != 0) {
						return sComp;
					} else {
						String s1 = String.valueOf(o1.getClosing_date());
						String s2 = String.valueOf(o2.getClosing_date());
						return s2.compareTo(s1);
					}
				}
			});
			model.addObject("jp", availableJobs);
		}
		model.setViewName("index");
		return model;
	}
	//end viewing home page function
	
	// when user clicks the job --this function is trigger
	// start viewing of job function
	@RequestMapping(value = "/viewJob", method = RequestMethod.GET)
	public ModelAndView viewJob(Model m, @RequestParam(value = "jobID") Long job_post_id, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<JobPost> readJobPost = restTemplate.getForEntity(uriReadJobPost + "/" + job_post_id + "/",
				JobPost.class);
		ResponseEntity<JobPostSkills[]> readSkillsReq = restTemplate
				.getForEntity(uriReadJobPostSkillsReq + "/" + job_post_id + "/", JobPostSkills[].class);
		if (readSkillsReq.getBody() != null) {
			mv.addObject("skillsReq", readSkillsReq.getBody());
		}
		JobPost jp = null;
		if (readJobPost.getBody() != null) {
			// System.out.println("JOBPOST TO VIEW : ");
			jp = readJobPost.getBody();
			// System.out.println(jp.toString());
			ResponseEntity<Employer> readEmployer = restTemplate
					.getForEntity(uriReadEmployerByUsername + "/" + jp.getUsername() + "/", Employer.class);
			if (readEmployer.getBody() != null) {
				mv.addObject("emp", readEmployer.getBody());
			}
		}
		mv.addObject("jp", jp);
		mv.setViewName("job-page-alt");
		return mv;
	}
	// end viewing of job function

	// this function is triggered when the user clicks the -"Hire" or "Work"
	// function in the HOME page
	// start proceed to login function
	@RequestMapping(value = "/proceedLoginPage", method = RequestMethod.GET)
	public String login(Model m, HttpServletRequest req) {
		System.out.println("proceed to log in()");
		return "my-account";
	}
	// end login function

	// this function is triggered when the user clicks the "Get Started"
	// function in the HOME page
	// start proceed to signing up function
	@RequestMapping(value = "/proceedSignUpPage", method = RequestMethod.GET)
	public String signUp(Model m, HttpServletRequest req) {
		System.out.println("proceed to sign up()");
		return "my-account";
	}
	// end signing up function

	// this function is triggered when the user clicks the "BLOG" button in the
	// view page
	// start blog function
	@RequestMapping(value = "/blog")
	public ModelAndView blogPage() {
		System.out.println("printHome()");
		ModelAndView model = new ModelAndView();
		model.setViewName("blog");
		return model;
	}
	// end blog function

	// this autowired object will be used in the mailing services of the system
	@Autowired
	SendMailSenderService sendMailService;

	// this function is triggered when the "EMPLOYER" actor of the system
	// "OFFERS JOB" to the jobseeker
	// the former clicks the "OFFER JOB" button in the employer's view page
	// a pop up model follows with the mailing content --> employer clicks the
	// "SEND APPLICATION" button
	// start mailing services
	@RequestMapping(value = "/sendMessage")
	public String sendMessage(Model m, @RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email, @RequestParam(value = "comment") String comment,
			HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("Sending message...");
		String fromAddress = "ai.smarthire@gmail.com";
		String subject = "User Feedback";
		String msgBody = "User " + name + " and email = " + email + " feedback : ".concat("\n" + comment);
		try {
			sendMailService.sendMail(fromAddress, fromAddress, subject, msgBody);
			System.out.println("Message Sent! Check smarthire mail.");
			System.out.println("Sending reply message to user.");
			String replyMsg = "Hello " + name + "," + "" + ""
					+ "Thank you for your message. I appreciate you sharing your thoughts.  We would like to review your feedback and"
					+ "\nwill get back to you in no time." + "" + "\n\nSincerely," + "" + "\nXander"
					+ "\nSMARTHire Global Inc. " + "\nai.smarthire@gmail.com" + "2016";
			sendMailService.sendMail(email, fromAddress, "THANK YOU FOR YOUR FEEDBACK", replyMsg);
			System.out.println("Appreciation Message Sent to user! Let them check their mail.");
			session.setAttribute("message", "Message sent!");

		} catch (Exception e) {
			System.out.println("Message not sent.");
			System.out.println("Catched error = " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", "Message not sent.");
		}

		return "contact";
	}
	// end mailing services

	// this function is triggered when CONTACT button is clicked in the home
	// page
	// feedbacks are welcomed here
	// start viewing contact page
	@RequestMapping(value = "/contact")
	public ModelAndView contactPage() {
		System.out.println("printHome()");
		ModelAndView model = new ModelAndView();
		model.setViewName("contact");
		System.out.println("connecting to contact..");
		return model;
	}
	// end viewing contact page

	// this function is triggered when the "Hire" or "Work" button is pressed
	// from the HOME page
	// start to login function
	@RequestMapping(value = "/mainLogin", method = RequestMethod.POST)
	public String login(Model m, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, @RequestParam(value = "loginAs") String loginAs,
			HttpServletRequest req) {
		System.out.println("logging in()");
		// getting the Http Session
		HttpSession session = req.getSession();
		loginAs = (String) req.getParameter("loginAs");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		if (loginAs.equalsIgnoreCase("to Hire".trim())) {
			Employer e = new Employer();
			e.setUsername(username);
			e.setPassword(password);
			String uriCheckEmp = SMART_HIRE_URL + "/employerRC/authenticate/";
			ResponseEntity<Employer> reemp = restTemplate.getForEntity(uriCheckEmp + username + "/" + password,
					Employer.class);
			if (reemp.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println("Authenticate EMPLOYER Http Status Ok.");
				session.setAttribute("employer", reemp.getBody());
				session.setAttribute("username", username);
				return "redirect:employer/home";
				// return "employer/home";
				// return "emp.home";
			} else {
				System.out.println("Authenticate EMPLOYER Http Status ERROR. Returning Index");
				return "redirect:/proceedLoginPage";
			}
		} else {
			String myURI = SMART_HIRE_URL + "/jobSeeker/authenticate/";
			ResponseEntity<JobSeeker> rejs = restTemplate.getForEntity(myURI + username + "/" + password,
					JobSeeker.class);
			if (rejs.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println("Authenticate Job Seeker Http Status Ok.");
				session.setAttribute("username", username);
				session.setAttribute("auth", "authenticated");
				return "redirect:jobseeker/home";
			} else {
				System.out.println("Authenticate Job Seeker Http Status ERROR. Returning Index");
				return "redirect:/proceedLoginPage";
			}
		}
	}
	// end to login function

	// this functino is triggered when the REGISTER tab is active in the
	// my-account.jsp
	// this redirects to registration page
	// start to sign up function
	@RequestMapping(value = "/mainSignUp")
	public String signUp(Model m, @RequestParam(value = "username2") String username,
			@RequestParam(value = "password1") String password, @RequestParam(value = "signUpAs") String signUpAs,
			@RequestParam(value = "email2") String email, HttpServletRequest req) {
		System.out.println("signing up()");
		// req.setAttribute("signUpAs", signUpAs);
		HttpSession session = req.getSession();
		signUpAs = (String) req.getParameter("signUpAs");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		String u2 = (String) username;
		String p1 = (String) password;
		String e2 = (String) email;

		System.out.println("PRINT Req Param : " + "u2 = " + u2 + "p1 = " + p1 + "e2 = " + e2);
		System.out.println("signUpAs = " + signUpAs);
		if (signUpAs.equalsIgnoreCase("to Hire".trim())) {
			// employer sign up
			Employer e = new Employer();
			e.setUsername(username);
			e.setPassword(password);
			e.setEmail(email);
			e.setRole("employer");
			String uriCheckEmployer = SMART_HIRE_URL + "/employerRC/read/";
			ResponseEntity<Employer> readEmp = restTemplate.getForEntity(uriCheckEmployer + username + "/",
					Employer.class);
			if (readEmp.getBody() == null) {
				String uriSaveEmp = SMART_HIRE_URL + "/employerRC/create/";
				ResponseEntity<Employer> ree = restTemplate.postForEntity(uriSaveEmp, e, Employer.class);
				Employer emp = ree.getBody();
				System.out.println("ree CREATE STATUS CODE: " + ree.getStatusCode());
				if (ree.getStatusCode().equals(HttpStatus.OK)) {
					System.out.println("Create Account Employer Http Status Ok.");
					session = req.getSession();
					session.setAttribute("username", emp.getUsername());
					// model.setViewName("emp.home");
					return "redirect:employer/home";
				} else {
					System.out.println("Create Account Employer Http Status ERROR. Returning Index");
					return "redirect:/proceedSignUpPage";
					// model.setViewName("/");
				}
			} else {
				m.addAttribute("msg", "There exists an account with that username.  Please enter unique username.");
				return "my-account";
			}
			// return model;
		} else {
			// js sign up
			JobSeeker js = new JobSeeker();
			js.setUsername(username);
			js.setPassword(password);
			js.setEmail(email);
			js.setRole("jobseeker");

			System.out.println("jobseeker signing up");
			String checkJobseeker = SMART_HIRE_URL + "/jobSeeker/read/";
			System.out.println("current username = " + username);
			ResponseEntity<JobSeeker> readJobseeker = restTemplate.getForEntity(checkJobseeker + username + "/",
					JobSeeker.class);
			System.out.println("after checking jobseeker existence");
			if (readJobseeker.getBody() == null) {
				System.out.println("no jobseeker record found with username = " + username);
				String myURI = SMART_HIRE_URL + "/jobSeeker/create/";

				System.out.println("Creating jobseeker account posting ready");
				ResponseEntity<JobSeeker> rejs = restTemplate.postForEntity(myURI, js, JobSeeker.class);
				js = rejs.getBody();
				System.out.println("Here3 " + rejs.getBody().getFirstname());
				System.out.println("REJS CREATE STATUS CODE: " + rejs.getStatusCode());
				if (rejs.getStatusCode().equals(HttpStatus.OK)) {
					System.out.println("Create Account Job Seeker Http Status Ok.");
					m.addAttribute("js", js);
					m.addAttribute("username", rejs.getBody().getUsername());
					System.out.println("Create Account Job Seeker Http Status Ok. 1");
					session = req.getSession();
					session.setAttribute("username", rejs.getBody().getUsername());
					session.setAttribute("email", js.getEmail());
					session.setAttribute("auth", "toComplete");
					// model.setViewName("js.home");
					System.out.println("Going to register page");
					// return "redirect:jobseeker/home";
					// return "redirect:jsRegisterPage";
					// m.addAttribute("state", "completeProfile");
					// return "js.register";
					return "redirect:jobseeker/home";
				} else {
					System.out.println("Create Account Job Seeker Http Status ERROR. Returning Index");
					// model.setViewName("/");
					return "redirect:/proceedSignUpPage";
				}
				// return "redirect:jsRegisterPage";
			} else {
				System.out.println("There exists a jobseeker record account for this username");
				m.addAttribute("msg", "There exists an account with that username.  Please enter a unique username. ");
				return "my-account";
			}
			// return model;
		}
	}
	// end to sign up function

	// this function is triggered when the LOGIN button is hit by the user
	// authentication proceeds here
	// start authentication user function
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model m, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpServletRequest req) {

		// getting the Http Session
		HttpSession session = req.getSession();

		String myURI = SMART_HIRE_URL + "/jobSeeker/authenticate/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		System.out.println("Login here2");

		ResponseEntity<JobSeeker> rejs = restTemplate.getForEntity(myURI + username + "/" + password, JobSeeker.class);

		System.out.println("Login here3");
		System.out.println("REJS LOGIN STATUS CODE: " + rejs.getStatusCode());
		if (rejs.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Authenticate Job Seeker Http Status Ok.");
			// m.addAttribute("username", rejs.getBody().getUsername());
			session.setAttribute("username", username);
			return "redirect:jobseeker/home";
		} else {
			System.out.println("Authenticate Job Seeker Http Status ERROR. Returning Index");
			return "redirect:/";
		}
	}
	// end authentication user function

	// this function is triggered when the LOGIN button is hit by the EMPLOYER
	// authentication proceeds here
	// start authentication EMPLOYER function
	@RequestMapping(value = "/loginemp", method = RequestMethod.POST)
	public String loginemp(Model m, @RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, HttpServletRequest req) {

		// getting the Http Session
		HttpSession session = req.getSession();
		String myURI = SMART_HIRE_URL + "/employerRC/authenticate/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		System.out.println("Login here2");
		ResponseEntity<Employer> reemp = restTemplate.getForEntity(myURI + username + "/" + password, Employer.class);

		System.out.println("reemp LOGIN STATUS CODE: " + reemp.getStatusCode());
		if (reemp.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Authenticate EMPLOYER Http Status Ok.");
			session.setAttribute("username", username);
			return "redirect:employer/home";
		} else {
			System.out.println("Authenticate EMPLOYER Http Status ERROR. Returning Index");
			return "redirect:/";
		}
	}
	// end authentication EMPLOYER function
	
	// this function is triggered when the user registers and selected "to Work" as userType attribute 
	// this redirects to the registration page for jobseeker
	// start jobseeker registration 
	@RequestMapping(value = "/jsRegisterPage")
	public ModelAndView jsRegister(Model m) {
		System.out.println("jsRegister()");
		ModelAndView model = new ModelAndView();
		model.setViewName("js.register");
		return model;
	}
	//end jobseeker registration

	//this function is triggered when the user registers and selected "to Hire" as userType attribute
	// this redirects to the registration page for employer
	//start employer registration
	@RequestMapping(value = "/empRegisterPage")
	public ModelAndView empRegisterPage() {
		ModelAndView model = new ModelAndView();
		System.out.println("entering /empRegisterPage");
		model.setViewName("emp.register");
		return model;
	}
	//end employer registration

	
	//this function is triggered when the jobseeker has entered all the necessary data in his account registration form
	//start creating jobseeker account function
	@RequestMapping(value = "/jsCreateAccount", method = RequestMethod.POST)
	public String createAccountSeeker(Model m, @ModelAttribute JobSeeker jobSeeker, HttpServletRequest req) {
		System.out.println("Here1 ");
		String myURI = SMART_HIRE_URL + "/jobSeeker/create/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<JobSeeker> rejs = restTemplate.postForEntity(myURI, jobSeeker, JobSeeker.class);
		JobSeeker js = rejs.getBody();
		System.out.println("Here3 " + rejs.getBody().getFirstname());
		System.out.println("REJS CREATE STATUS CODE: " + rejs.getStatusCode());
		if (rejs.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Create Account Job Seeker Http Status Ok.");
			m.addAttribute("js", js);
			m.addAttribute("username", rejs.getBody().getUsername());
			System.out.println("Create Account Job Seeker Http Status Ok.");
			HttpSession session = req.getSession();
			session.setAttribute("username", rejs.getBody().getUsername());
			return "redirect:../jobseeker/home";
		} else {
			System.out.println("Create Account Job Seeker Http Status ERROR. Returning Index");
			return "redirect:../";
		}
	}
	//end creating jobseeker account function
	
	//this function is triggered when the employer has entered all the necessary data in his account registration form
	//start creating employer account function
	@RequestMapping(value = "/empCreateAccount", method = RequestMethod.POST)
	public String createAccountEmployer(@ModelAttribute Employer employer, HttpServletRequest req) {
		System.out.println("Create Account Employer");
		String myURI = SMART_HIRE_URL + "/employerRC/create/";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<Employer> ree = restTemplate.postForEntity(myURI, employer, Employer.class);
		Employer emp = ree.getBody();
		System.out.println("ree CREATE STATUS CODE: " + ree.getStatusCode());
		if (ree.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Create Account Employer Http Status Ok.");
			HttpSession session = req.getSession();
			session.setAttribute("username", emp.getUsername());
			return "redirect:../employer/home";
		} else {
			System.out.println("Create Account Employer Http Status ERROR. Returning Index");
			return "redirect:../";
		}
	}
	//end creating employer account function

}
