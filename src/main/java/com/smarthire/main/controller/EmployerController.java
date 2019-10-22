package com.smarthire.main.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.smarthire.main.dto.CreateJobPostDTO;
import com.smarthire.main.models.Employer;
import com.smarthire.main.models.JobPost;
import com.smarthire.main.models.JobSeeker;
import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.xander.controller.Monitor;
import com.smarthire.xander.controller.SendMailSenderService;
import com.smarthire.xander.controller.SkillMonitor;
import com.smarthire.xander.dto.RecommendedDTO;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.Certificate;
import com.smarthire.xander.models.Education;
import com.smarthire.xander.models.Experience;
import com.smarthire.xander.models.Image;
import com.smarthire.xander.models.JobAlert;
import com.smarthire.xander.models.JobPostSkills;
import com.smarthire.xander.models.Test;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";
	public static final String uriReadEmployer = SMART_HIRE_URL + "/employerRC/read/";
	public static final String uriCreateJobPost = SMART_HIRE_URL + "/jobPostRC/create/";
	public static final String uriCreateJobPostSkills = SMART_HIRE_URL + "/jobPostSkillsRC/create/";
	public static final String uriGetAllJobPostSkillsById = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/";
	public static final String uriGetSpecificJobPosts = SMART_HIRE_URL + "/jobPostRC/read/";
	public static final String uriUpdateSpecificJobPosts = SMART_HIRE_URL + "/jobPostRC/update/";
	public static final String uriDeleteAllJobPostSkillsById = SMART_HIRE_URL
			+ "/jobPostSkillsRC/deleteAllByJobPostId/";
	public static final String uriGetJobPosts = SMART_HIRE_URL + "/jobPostRC/getPostByEmployer/";
	public static final String uriGetListJobPostApplicants = SMART_HIRE_URL + "/jobPostApplicantsRC/getAllApplicants/";
	public static final String uriGetSMDataOfUser = SMART_HIRE_URL + "/socialMediaData/getSMDListOfUser/";
	public static final String uriCreateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/create/";
	public static final String uriSocialMediaDataCreate = SMART_HIRE_URL + "/socialMediaData/create/";
	public static final String uriGetFbDataWeb = "http://localhost:8080/SMARTHire/fbREST/getFbData";
	public static final String uriReadSM = SMART_HIRE_URL + "/socialMediaAccess/readUserSM/";
	public static final String uriGetTwitterDataWeb = SMART_HIRE_URL + "/twitterREST/getTwitterData";
	public static final String uriGetLinkedInSkillsWeb = SMART_HIRE_URL + "/linkedInRest/getSkills";
	public static final String uriCreateJobSeekerSkills = SMART_HIRE_URL + "/jobSeekerSkills/create/";
	public static final String uriReadJobPostSkillsById = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/";
	public static final String uriReadJobSeekerSkills = SMART_HIRE_URL + "/jobSeekerSkills/";
	public static final String uriReadProfileJobSeekerSkills = SMART_HIRE_URL
			+ "/jobSeekerSkills/getAllSkillsByUsername/";
	public static final String uriReadAllJobSeeker = SMART_HIRE_URL + "/jobSeeker/";
	public static final String uriUpdateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/update/";
	public static final String uriReadChar_Category = SMART_HIRE_URL + "/char_catscoreRC/read/";
	public static final String uriReadAllChar_Category = SMART_HIRE_URL + "/char_catscoreRC/getAllCharCatScoreByUser/";
	public static final String uriCreateJobAlert = SMART_HIRE_URL + "/jobAlertRC/create/";
	public static final String uriGetAllExperienceByUser = SMART_HIRE_URL + "/experienceRC/getAllExperienceByUser/";
	public static final String uriGetAllCertificateByUser = SMART_HIRE_URL + "/certificateRC/getAllCertificatesByUser/";
	public static final String uriGetAllTestByUser = SMART_HIRE_URL + "/testRC/getAllTestByUser/";
	public static final String uriGetAllEducationByUser = SMART_HIRE_URL + "/educationRC/getAllEducationByUser/";
	public static final String uriGetProfilePhotoByUsername = SMART_HIRE_URL + "/fileUploadRC/readByUsername/";
	public static final String uriDeleteRecordJoPost = SMART_HIRE_URL + "/jobPostRC/delete/";

	LinkedList<JobSeeker> applicantList;

	// logout function of the EMPLOYER actor
	// start logging out function
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/?logout";
	}
	// end logging out function

	// this function is triggered when the EMPLOYER has valid authentication
	// displays emp.home.jsp
	// start home page viewing
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String employerHome(Model m, HttpServletRequest req) {
		System.out.println("I'm home");
		m.addAttribute("state", "contentHome");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		// System.out.println("Username1: " + username);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<Employer> ree = restTemplate.getForEntity(uriReadEmployer + "/" + username + "/",
				Employer.class);
		Employer employer = ree.getBody();
		session.setAttribute("firstname", employer.getFirstname());

		return "redirect:contentViewJobPosts";
	}
	// end homepage viewing

	// this funcition is triggered when the EMPLOYER actor hits the "SHOW
	// DETAILS" button
	// in his MANAGE-APPLICATIONS.jsp view
	// start viewing js profile function
	@RequestMapping(value = "/viewJSprofile/{jsUsername}", method = RequestMethod.GET)
	public String viewJSprofile(Model m, @PathVariable String jsUsername, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String empUsername = (String) session.getAttribute("username");
		System.out.println("I'm view JS Profile! >>username = " + jsUsername);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		ResponseEntity<JobSeeker> readJobSeeker = restTemplate
				.getForEntity(uriReadAllJobSeeker + "/read/" + jsUsername + "/", JobSeeker.class);
		JobSeeker jSeeker;
		if (readJobSeeker.getBody() != null) {
			jSeeker = readJobSeeker.getBody();
			m.addAttribute("js", readJobSeeker.getBody());
			System.out.println("SUCCESS READ SEEKER! Proceed to viewing seeker's profile");

			Employer emp = null;
			ResponseEntity<Employer> readEmployer = restTemplate.getForEntity(uriReadEmployer + "/" + empUsername + "/",
					Employer.class);
			if (readEmployer.getBody() != null) {
				emp = readEmployer.getBody();
			}
			System.out.println("Preparing jobseeker CV");
			// get All Skills
			String uriGetAllSkillsByUsername = SMART_HIRE_URL + "/jobSeekerSkills/getAllSkillsByUsername/";
			ResponseEntity<JobSeekerSkills[]> reGetAllSkills = restTemplate
					.getForEntity(uriGetAllSkillsByUsername + jsUsername + "/", JobSeekerSkills[].class);
			JobSeekerSkills[] sList = null;
			if (reGetAllSkills.getBody() != null) {
				sList = reGetAllSkills.getBody();
			}
			/*
			 * List<String> skillsList = new LinkedList<>(); for(JobSeekerSkills
			 * s: sList){ skillsList.add(s.getSkill_name()); }
			 */

			m.addAttribute("skills", sList);

			String uriGetAllEducation = SMART_HIRE_URL + "/educationRC/getAllEducationByUser/";
			ResponseEntity<Education[]> reGetAllEdu = restTemplate.getForEntity(uriGetAllEducation + jsUsername + "/",
					Education[].class);
			Education[] eduList = null;
			if (reGetAllEdu.getBody() != null) {
				eduList = reGetAllEdu.getBody();
			}

			m.addAttribute("educ", eduList);

			String uriGetAllExp = SMART_HIRE_URL + "/experienceRC/getAllExperienceByUser/";
			ResponseEntity<Experience[]> reGetAllExp = restTemplate.getForEntity(uriGetAllExp + jsUsername + "/",
					Experience[].class);
			Experience[] expList = null;
			if (reGetAllExp.getBody() != null) {
				expList = reGetAllExp.getBody();
			}

			m.addAttribute("exp", expList);

			String uriGetAllCertificates = SMART_HIRE_URL + "/certificateRC/getAllCertificatesByUser/";
			ResponseEntity<Certificate[]> reGetAllCert = restTemplate
					.getForEntity(uriGetAllCertificates + jsUsername + "/", Certificate[].class);
			Certificate[] certList = null;
			if (reGetAllCert.getBody() != null) {
				certList = reGetAllCert.getBody();
			}

			m.addAttribute("cert", certList);

			String uriGetAllTests = SMART_HIRE_URL + "/testRC/getAllTestByUser/";
			ResponseEntity<Test[]> reGetAllTests = restTemplate.getForEntity(uriGetAllTests + jsUsername + "/",
					Test[].class);
			Test[] testList = null;
			if (reGetAllTests.getBody() != null) {
				testList = reGetAllTests.getBody();
			}

			m.addAttribute("cert", certList);
			m.addAttribute("test", testList);
			m.addAttribute("username", jsUsername);
			m.addAttribute("js", jSeeker);
			m.addAttribute("state", "viewJSprofile");
			m.addAttribute("emp", emp);
			return "emp.home";
		} else {
			System.err.println("Failed to load seeker profile");
			m.addAttribute("state", "ranking");
		}
		return "emp.home";
	}
	// end viewing js profile function

	// this autowired object will be used in the mailing services of the system
	@Autowired
	SendMailSenderService sendMailService;

	// this function is triggered when the "EMPLOYER" actor of the system
	// "OFFERS JOB" to the jobseeker
	// the former clicks the "OFFER JOB" button in the employer's view page
	// a pop up model follows with the mailing content --> employer clicks the
	// "SEND APPLICATION" button
	// start mailing services
	@RequestMapping(value = "/sendMessage/", method = RequestMethod.POST)
	public String offerJob(Model m, @RequestParam(value = "compName") String compName,
			@RequestParam(value = "employerMail") String employerMail,
			@RequestParam(value = "applicantMail") String applicantMail,
			@RequestParam(value = "message") String message, HttpServletRequest req) {
		System.out.println("I'm sendMessage offer job");
		HttpSession session = req.getSession();

		// admin added his own email for presentation purposes
		// applicantMail = "lee.lumapac@gmail.com";

		String fromAddress = "ai.smarthire@gmail.com";
		String subject = "Employer " + compName + "offers you a JOB";
		message = message.concat("   " + ">>> NO NEED FOR REPLY <<<< To CONTACT Employer, mail to : " + employerMail);
		String empMsgBody = "Greetings! Your job offer has been sent to " + applicantMail;
		String empSubject = "Job Offer Sent!";
		try {
			// send message to applicant
			sendMailService.sendMail(applicantMail, fromAddress, subject, message);
			// send message to employer
			sendMailService.sendMail(employerMail, fromAddress, empSubject, empMsgBody);

			System.out.println("Message Sent! Check smarthire mail.");
			System.out.println("Sending reply message to user.");
			String toAi = "Sent offer job to applicant! Sent employer notification";

			sendMailService.sendMail(fromAddress, fromAddress,
					"The employer " + compName + " offers a job to the jobseeker " + applicantMail, toAi);
			System.out.println("Appreciation Message Sent to user! Let them check their mail.");
			session.setAttribute("message", "Message sent!");

		} catch (Exception e) {
			System.out.println("Message not sent.");
			System.out.println("Catched error = " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", "Message not sent.");
		}
		return "emp.home";
	}
	// end mailing services

	// start employer's dashboard function
	@RequestMapping(value = "/contentDashboard", method = RequestMethod.GET)
	public String dashBoardContent(Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("username", username);
		m.addAttribute("firstname", firstname);
		m.addAttribute("state", "contentHome");
		return "emp.home";
	}
	// end employer's dashboard function

	// this function is triggered when the employer clicks the "Post a Job"or
	// "Add Job" buttons in his viewing page
	// start creating job function
	@RequestMapping(value = "/contentCreateJobPosts", method = RequestMethod.GET)
	public String createJobPostsContent(Model m, HttpServletRequest req) {
		System.out.println("I'm contentCreateJobPosts");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("username", username);
		m.addAttribute("firstname", firstname);
		m.addAttribute("state", "contentCreateJobPosts");
		return "emp.home";
	}
	// end creating job function

	// this function is triggered when the employer clicks the "DELETE" button
	// in "Manage-Applications Page" of the EMPLOYER
	// start delete job posting function
	@RequestMapping(value = "/deleteJobPost/{job_post_id}", method = RequestMethod.GET)
	public String deleteJobPost(Model m, HttpServletRequest req, @PathVariable Long job_post_id) {
		System.out.println("I'm deleteJobPost");

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String getAlertMessage = "";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		try {
			restTemplate.delete(uriDeleteRecordJoPost + "/" + job_post_id + "/");
			getAlertMessage = "success";
		} catch (Exception e) {
			System.err.println("Exception Failed to delete job post id = " + job_post_id);
			getAlertMessage = "failed";
		}

		session.setAttribute("username", username);
		session.setAttribute("getAlert", getAlertMessage);
		return "redirect:contentViewJobPosts";
	}
	// end delete job posting function

	// this function is triggered after the EMPLOYER hits the "LOGIN" button in
	// the login page
	// this is his fresh view or his default view --> MANAGE-JOBS.jsp
	// start viewing of jobs function
	@RequestMapping(value = "/contentViewJobPosts", method = RequestMethod.GET)
	public String viewJobPostContent(Model m, HttpServletRequest req) {
		System.out.println("I'm Content View JobPosts");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String getAlertMessage = (String) session.getAttribute("getAlert");

		m.addAttribute("username", username);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		ResponseEntity<JobPost[]> rejpArray = restTemplate.getForEntity(uriGetJobPosts + "/" + username + "/",
				JobPost[].class);

		ArrayList<Integer> applicantCount = new ArrayList<>();
		ArrayList<JobPost> aJP = new ArrayList<>();
		for (JobPost jjp : rejpArray.getBody()) {
			System.out.println("Job Post added ----------------------------");
			System.out.println("Date posted : " + jjp.getDate_posted());
			System.out.println("Date close: " + jjp.getClosing_date());
			aJP.add(jjp);
			ResponseEntity<JobSeeker[]> rejs = restTemplate
					.getForEntity(uriGetListJobPostApplicants + "/" + jjp.getJob_post_id() + "/", JobSeeker[].class);
			int count = rejs.getBody().length;
			applicantCount.add(count);
		}
		session.setAttribute("getAlert", getAlertMessage);
		m.addAttribute("aJP", aJP);
		m.addAttribute("applicantCount", applicantCount);
		m.addAttribute("state", "contentViewJobPosts");
		String contextPath = req.getContextPath().toString();

		System.out.println("contextPath = " + contextPath);
		return "emp.home";
	}
	// end viewing of job function

	// this function is triggered when the EMPLOYER hits the "EDIT" button in
	// his MANAGE-JOBs.jsp viewing page
	// start edit job posting function
	@RequestMapping(value = "/editJobPost/{job_post_id}", method = RequestMethod.GET)
	public String editJobPost(Model m, HttpServletRequest req, @PathVariable Long job_post_id) {
		System.out.println("I'm editJobPost");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("username", username);
		m.addAttribute("firstname", firstname);
		m.addAttribute("state", "contentCreateJobPosts");
		JobPost jobPost = new JobPost();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		// getting job postings
		ResponseEntity<JobPost> rejpById = restTemplate.getForEntity(uriGetSpecificJobPosts + "/" + job_post_id + "/",
				JobPost.class);
		if (rejpById.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println(">Success reading specific job post");
			jobPost = rejpById.getBody();
			String jobPostSkills = "";
			System.out.println("toString read Jobpost = " + jobPost.toString());
			ResponseEntity<JobPostSkills[]> rejpSkillsReqById = restTemplate.getForEntity(
					uriReadJobPostSkillsById + "/" + rejpById.getBody().getJob_post_id(), JobPostSkills[].class);
			if (rejpSkillsReqById.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println("getting job post skills requirements");
				for (JobPostSkills jpskills : rejpSkillsReqById.getBody()) {
					jobPostSkills.concat(jpskills.getSkill_name() + ",");
				}
				// jobPostSkills = jobPostSkills.substring(0,
				// jobPostSkills.length()-1);
			}
			m.addAttribute("jpSkills", jobPostSkills);
		}

		m.addAttribute("jp", jobPost);
		m.addAttribute("state", "contentEditJobPosts");
		return "emp.home";
	}
	// end editing job posting function

	// this function is triggered when the EMPLOYER hits the "SHOW ()" button in
	// MANAGE-Jobs.jsp viewing page
	// this function redirects to MANAGE-APPLICATIONS.jsp
	// start viewing of specific job postings
	@RequestMapping(value = "/contentViewSpecificJobPost/{job_post_id}", method = RequestMethod.GET)
	public String viewJobVacancy(Model m, HttpServletRequest req, @PathVariable Long job_post_id) {
		System.out.println("Viewing specific job post where id = " + job_post_id);
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("username", username);
		m.addAttribute("firstname", firstname);
		System.out.println("Job Post id: " + job_post_id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<JobPost> rejp = restTemplate.getForEntity(uriGetSpecificJobPosts + "/" + job_post_id + "/",
				JobPost.class);
		System.out.println("Title: " + rejp.getBody().getJob_title());
		System.out.println("Description: " + rejp.getBody().getJob_description());
		System.out.println("Date: " + rejp.getBody().getDate_posted());
		m.addAttribute("jp", rejp.getBody());

		LinkedList<JobSeekerSkills> allSkillsList = new LinkedList<>();
		ResponseEntity<JobSeekerSkills[]> allSkills = restTemplate.getForEntity(uriReadJobSeekerSkills + "/",
				JobSeekerSkills[].class);

		for (JobSeekerSkills jSkills : allSkills.getBody()) {
			allSkillsList.add(jSkills);
		}

		// HashMap<String, LinkedList> jsSkillsList = new HashMap<>();
		RecommendedDTO rDTO;
		LinkedList<RecommendedDTO> toBeRankList = new LinkedList<>();

		// all who applied
		ResponseEntity<JobSeeker[]> rejs = restTemplate
				.getForEntity(uriGetListJobPostApplicants + "/" + job_post_id + "/", JobSeeker[].class);

		// get all required skills in this job post
		ResponseEntity<JobPostSkills[]> requiredSkills = restTemplate
				.getForEntity(uriGetAllJobPostSkillsById + "/" + job_post_id + "/", JobPostSkills[].class);
		LinkedList<String> requiredSkillsList = new LinkedList<>();
		for (JobPostSkills jps : requiredSkills.getBody()) {
			requiredSkillsList.add(jps.getSkill_name());
		}
		applicantList = new LinkedList<>();
		int xx = 1;
		LinkedList<String> seekerSkills;
		for (JobSeeker js : rejs.getBody()) {
			seekerSkills = new LinkedList<>();
			System.out.println("Reading applicant ..." + xx++);
			applicantList.add(js);
			try {
				ResponseEntity<CHAR_CATEGORYSCORE[]> rctS = restTemplate.getForEntity(
						uriReadAllChar_Category + "/" + js.getUsername() + "/", CHAR_CATEGORYSCORE[].class);
				Monitor monitor = new Monitor();
				double finalCharScore = monitor.getFinalCharScore(rctS.getBody());
				System.out.println("Applicant " + js.getUsername() + " character score: " + finalCharScore);
				// skills scoring process
				if (requiredSkills.getStatusCode().equals(HttpStatus.OK)) {
					// scoring skills
					for (JobSeekerSkills jSkills : allSkillsList) {
						if (js.getUsername().equals(jSkills.getUsername())) {
							seekerSkills.add(jSkills.getSkill_name());
						}
					}
					SkillMonitor skill_monitor = new SkillMonitor(seekerSkills, requiredSkillsList);
					double skill_score = skill_monitor.computeSkillScore();
					System.out.println(">>>Relevant Skills Matched");
					rDTO = new RecommendedDTO(finalCharScore, skill_score, username, "applicant", js,
							skill_monitor.getMatchedJsSkills());
					System.out.println("Skill Score of " + js.getUsername() + " = " + skill_score);
					toBeRankList.add(rDTO);

				} else {
					System.out.println("No required skills in this jobpost!");
				}
			} catch (Exception e) {
				System.out.println("EEEERRRRRRRORRRR");
				e.printStackTrace();
			}
		}

		// Recommended profiles-----------------------------
		ResponseEntity<JobSeeker[]> listJobSeekers = restTemplate.getForEntity(uriReadAllJobSeeker + "/",
				JobSeeker[].class);
		System.out.println("All jobseeker list size =  " + listJobSeekers.getBody().length);
		LinkedList<JobSeeker> jsList = new LinkedList<>();

		// all who did not applied
		for (JobSeeker js : listJobSeekers.getBody()) {
			if (!isFoundInJobSeekerList(js)) {
				jsList.add(js);
			}
		}

		int x = 1;
		for (JobSeeker js : jsList) {
			seekerSkills = new LinkedList<>();
			System.out.println("Reading prospect .... " + x++ + " -> " + js.getUsername());
			try {
				ResponseEntity<CHAR_CATEGORYSCORE[]> rctS = restTemplate.getForEntity(
						uriReadAllChar_Category + "/" + js.getUsername() + "/", CHAR_CATEGORYSCORE[].class);
				Monitor monitor = new Monitor();
				double finalCharScore = monitor.getFinalCharScore(rctS.getBody());
				System.out.println("Recommended " + js.getUsername() + " character score: " + finalCharScore);
				// skills scoring process
				if (requiredSkills.getStatusCode().equals(HttpStatus.OK)) {
					// scoring skills
					for (JobSeekerSkills jSkills : allSkillsList) {
						if (js.getUsername().equals(jSkills.getUsername())) {
							seekerSkills.add(jSkills.getSkill_name());
						}
					}
					SkillMonitor skill_monitor = new SkillMonitor(seekerSkills, requiredSkillsList);
					double skill_score = skill_monitor.computeSkillScore();
					if (skill_score > 0.0) {
						System.out.println(">>>Relevant Skills Matched");
						System.out.println("Skill score for USER = " + js.getUsername() + " is = " + skill_score);
						rDTO = new RecommendedDTO(finalCharScore, skill_score, username, "recommended", js,
								skill_monitor.getMatchedJsSkills());
						System.out.println(
								"Skill Score of recommended profile : " + js.getUsername() + " = " + skill_score);
						toBeRankList.add(rDTO);
					} else {

					}

				} else {
					System.out.println("No required skills in this jobpost!");
					System.err.println("Employer Controller line 439!");
				}

			} catch (Exception e) {
				System.out.println("EEEERRRRRRRORRRR  ====  " + e.getMessage());
				System.err.println("Employer controller 452!");
				e.printStackTrace();
			}

		}

		System.out.println("-----------For recommendation profiles list = " + jsList.size());
		System.out.println("-----------Applicant list = " + applicantList.size());
		System.out.println("-----------To be ranked list size = " + toBeRankList.size());

		Collections.sort(toBeRankList, new Comparator<RecommendedDTO>() {
			public int compare(RecommendedDTO o1, RecommendedDTO o2) {
				String x1 = String.valueOf(o1.getCharScore());
				String x2 = String.valueOf(o2.getCharScore());

				int sComp = x2.compareTo(x1);

				if (sComp != 0) {
					return sComp;
				} else {
					String s1 = String.valueOf(o1.getSkillScore());
					String s2 = String.valueOf(o2.getSkillScore());
					return s2.compareTo(s1);
				}
			}
		});

		m.addAttribute("jobID", job_post_id);
		m.addAttribute("state", "ranking");
		m.addAttribute("rankList", toBeRankList);

		// return ranking;
		return "emp.home";
	}
	// end viewing of specific job postings

	// this function is privately called by a head function
	// to search for a specific jobseeker
	// start check if existing record function
	public boolean isFoundInJobSeekerList(JobSeeker js) {
		for (JobSeeker j : applicantList) {
			if (j.getUsername().equals(js.getUsername())) {
				return true;
			}
		}
		return false;
	}
	// end check if existing record function

	// this function is triggered when the employer clicks the "Post a Job"or
	// "Add Job" buttons in his viewing page
	// start creating job function
	@RequestMapping(value = "/empCreateJobPost", method = RequestMethod.POST)
	public String createJobPost(Model m, HttpServletRequest req, @ModelAttribute CreateJobPostDTO jobPostDto) {

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("username", username);
		m.addAttribute("firstname", firstname);

		JobPost jobPost = new JobPost();
		jobPost.setJob_title(jobPostDto.getJob_title());
		jobPost.setJob_description(jobPostDto.getJob_description());
		jobPost.setUsername(username);
		jobPost.setCategory(jobPostDto.getJob_category());
		jobPost.setClosing_date(jobPostDto.getClosing_date());
		jobPost.setJob_status("available");
		jobPost.setJob_type(jobPostDto.getJob_type());
		jobPost.setLocation(jobPostDto.getLocation());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		LinkedList<JobSeekerSkills> allSkillsList = new LinkedList<>();
		ResponseEntity<JobSeekerSkills[]> allSkills = restTemplate.getForEntity(uriReadJobSeekerSkills + "/",
				JobSeekerSkills[].class);

		for (JobSeekerSkills jSkills : allSkills.getBody()) {
			allSkillsList.add(jSkills);
		}
		System.out.println(">>>jobPostDTO ToString");
		System.out.println(jobPostDto.toString());
		// check if job post exists already

		String skills = jobPostDto.getSkills().trim();
		String[] skillsArr = skills.split(",");
		LinkedList<String> listSkills = new LinkedList<>();
		LinkedList<JobSeeker> allSeekers = new LinkedList<>();
		for (String skill : skillsArr) {
			System.out.println("Skills are: " + skill);
			listSkills.add(skill.trim());
		}

		ResponseEntity<JobSeeker[]> reGetAllJobSeeker = restTemplate.getForEntity(uriReadAllJobSeeker,
				JobSeeker[].class);
		if (reGetAllJobSeeker.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Success retrieved all seekers");
			for (JobSeeker s : reGetAllJobSeeker.getBody()) {
				allSeekers.add(s);
			}
		} else {
			System.err.println("Unable to load all seekers line 542;");
		}

		ResponseEntity<JobPost> rejp = restTemplate.postForEntity(uriCreateJobPost, jobPost, JobPost.class);
		if (rejp.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Create Job POST Http Status Ok.");
			// create job_post_skill
			Long id = rejp.getBody().getJob_post_id();
			jobPost = rejp.getBody();
			boolean proceed = false;
			for (String s : listSkills) {
				JobPostSkills jpSkills = new JobPostSkills();
				jpSkills.setJob_post_id(id);
				System.out.println("JobPostID = " + id);
				jpSkills.setSkill_name(s);
				System.out.println("Skill_name = " + s);

				ResponseEntity<JobPostSkills> rejps = restTemplate.postForEntity(uriCreateJobPostSkills, jpSkills,
						JobPostSkills.class);
				proceed = true;
			}

			System.out.println("CREATE JOB POST SKILLS for Job Post id sucess!");
			// start storing alerts for seekers
			LinkedList<JobAlert> seekerNeedAlertList = new LinkedList<>();
			for (JobSeeker js : allSeekers) {
				LinkedList<String> seekerSkills = new LinkedList<>();
				for (JobSeekerSkills jSkills : allSkillsList) {
					if (js.getUsername().equals(jSkills.getUsername())) {
						seekerSkills.add(jSkills.getSkill_name());
					}
				}
				System.out.println("seekerSkills size = " + seekerSkills.size());
				System.out.println("listSkills size = " + listSkills.size());

				// check skills matching : jobseeker skills vs jobPost
				// skills req
				SkillMonitor skillMonitor = new SkillMonitor(seekerSkills, listSkills);

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
					j.setAlertName(jobPost.getJob_title());
					j.setLocation(jobPost.getLocation());
					j.setJob_post_id(id);
					j.setJsUsername(js.getUsername());
					j.setStatus("Not yet Applied");
					j.setDateCreated(jobPost.getDate_posted());
					j.setKeywords(keywords.substring(0, keywords.length() - 1));
					seekerNeedAlertList.add(j);
					ResponseEntity<JobAlert> reCreateJobAlert = restTemplate.postForEntity(uriCreateJobAlert, j,
							JobAlert.class);
					if (reCreateJobAlert.getStatusCode().equals(HttpStatus.OK)) {
						System.out.println(">>>Success create job Alert!");
					} else {
						System.err.println(">>>Failed to create job alert!");
					}
				}
			}
			return "redirect:../home";
			// return "redirect:home:
		} else

		{
			System.out.println("Create Account Job Seeker Http Status ERROR. Returning Index");
			return "redirect:../contentCreateJobPosts";
		}

	}
	// end employer create job posting function

	// this function is triggered when the EMPLOYER hits the "EDIT" button in
	// his MANAGE-JOBs.jsp viewing page
	// start edit job posting function
	@RequestMapping(value = "/empEditJobPost", method = RequestMethod.POST)
	public static final String empEditJobPost(Model m, HttpServletRequest req,
			@ModelAttribute CreateJobPostDTO jobPostDto) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("username", username);
		m.addAttribute("firstname", firstname);

		JobPost jobPost = new JobPost();
		jobPost.setJob_title(jobPostDto.getJob_title());
		jobPost.setJob_description(jobPostDto.getJob_description());
		jobPost.setUsername(username);
		jobPost.setCategory(jobPostDto.getJob_category());
		jobPost.setClosing_date(jobPostDto.getClosing_date());
		jobPost.setJob_status("available");
		jobPost.setJob_type(jobPostDto.getJob_type());
		jobPost.setLocation(jobPostDto.getLocation());

		jobPost.setJob_post_id(jobPostDto.getJob_post_id());
		// update current record
		System.out.println("Updating job post data");
		restTemplate.put(uriUpdateSpecificJobPosts, jobPost);
		System.out.println("Updated job post!");
		// delete jobpost skills req
		System.out.println("Ready to delete all job post skills by jp id...");
		restTemplate.delete(uriDeleteAllJobPostSkillsById + "/" + jobPostDto.getJob_post_id());
		System.out.println("after main deleted!");
		Long job_post_id = jobPostDto.getJob_post_id();
		String skills = jobPostDto.getSkills().trim();
		String[] skillsArr = skills.split(",");
		LinkedList<String> listSkills = new LinkedList<>();
		for (String skill : skillsArr) {
			System.out.println("Skills are: " + skill);
			listSkills.add(skill.trim());
		}

		for (String s : listSkills) {
			JobPostSkills jpSkills = new JobPostSkills();
			jpSkills.setJob_post_id(job_post_id);
			System.out.println("JobPostID = " + job_post_id);
			jpSkills.setSkill_name(s);
			System.out.println("Skill_name = " + s);
			ResponseEntity<JobPostSkills> rejps = restTemplate.postForEntity(uriCreateJobPostSkills, jpSkills,
					JobPostSkills.class);
			if (!rejps.getStatusCode().equals(HttpStatus.OK)) {
				System.err.println("ERROR CREATING JOB POST SKILLS for Job Post id: " + job_post_id);
			} else {
				System.out.println("CREATE JOB POST SKILLS for Job Post id sucess!");
			}
		}

		return "redirect:../home";

	}
	// end editing job posting function

	// this function is triggered when EMPLOYER actor updates his personal
	// account
	// start update employer account
	@RequestMapping(value = "/empUpdatePersonalAccount", method = RequestMethod.POST)
	public String empUpdateAccount(Model m, @ModelAttribute Employer emp, HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("Registering employer... ");
		String myURI = SMART_HIRE_URL + "/employerRC/update/";

		// reading attributes
		Employer e = (Employer) emp;
		System.out.println("emp toString = " + emp.toString());

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		System.out.println("Printing modelAttribute jobseeker properties");
		System.out.println("" + e.toString());
		// read JobSeeker first
		ResponseEntity<Employer> reEmployerRead = restTemplate.getForEntity(uriReadEmployer + e.getUsername() + "/",
				Employer.class);
		if (reEmployerRead.getStatusCode().equals(HttpStatus.OK)) {
			// update personal profile
			e.setPassword(reEmployerRead.getBody().getPassword());
			e.setRole(reEmployerRead.getBody().getRole());
			restTemplate.put(myURI, e);
			System.out.println("Updated Employer Profile!");
		} else {
			// catch The User can't changed his username
			System.out.println("Username has been changed!  Security breached!");
		}

		session.setAttribute("username", e.getUsername());
		m.addAttribute("emp", e);
		m.addAttribute("username", e.getUsername());
		return "redirect:../home";
	}
	// end update employer account

	// this function is triggered when EMPLOYER updates their company logo
	// start update logo
	@RequestMapping(value = "/employerUpdateLogo", method = RequestMethod.POST)
	public String empUpdateLogo(Model m, @RequestParam(value = "image") CommonsMultipartFile image,
			@RequestParam(value = "username") String uname, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String uriUpdateImage = SMART_HIRE_URL + "/fileUploadRC/update/";
		String uriCreateImage = SMART_HIRE_URL + "/fileUploadRC/create/";
		String username = (String) uname;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		// read image table if username has image
		Image img;
		if (image != null) {
			img = new Image();
			img.setFileName(image.getOriginalFilename());
			img.setData(image.getBytes());
			img.setUsername(username);
			ResponseEntity<Image> reImageRead = restTemplate.getForEntity(uriGetProfilePhotoByUsername + username + "/",
					Image.class);
			if (reImageRead.getStatusCode().equals(HttpStatus.OK)) {
				// update image
				System.out.println("There is an image saved for this username.  Updating image...");
				if (reImageRead.getBody() != null) {
					img.setId(reImageRead.getBody().getId());
					restTemplate.put(uriUpdateImage, img);
					System.out.println("Profile Photo updated!");
				} else {
					System.out.println("There is no current photo saved by this username.  Creating photo...");
					ResponseEntity<Image> reImageCreate = restTemplate.postForEntity(uriCreateImage, img, Image.class);
					if (reImageCreate.getStatusCode().equals(HttpStatus.OK))
						System.out.println("Created Profile Photo for username = " + username);
					else
						System.out.println("Failed to create profile photo for username = " + username);
				}
			} else {
				System.out.println("There is no current photo saved by this username.  Creating photo...Outside else");
				ResponseEntity<Image> reImageCreate = restTemplate.postForEntity(uriCreateImage, img, Image.class);
				if (reImageCreate.getStatusCode().equals(HttpStatus.OK))
					System.out.println("Created Profile Photo for username = " + username);
				else
					System.out.println("Failed to create profile photo for username = " + username);
			}
		}

		session.setAttribute("username", username);
		m.addAttribute("username", username);
		return "redirect:../home";
	}
	// end EMPLOYER update logo

	// this function is triggered when EMPLOYER actor updates his personal
	// account
	// start update employer account
	@RequestMapping(value = "/editAccountPage", method = RequestMethod.GET)
	public String employeeEditAccount(Model m, HttpServletRequest req) {
		System.out.println(">>>I'm editAccountPage");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("firstname", firstname);
		m.addAttribute("username", username);
		m.addAttribute("state", "editAccount");
		// return "emp.home";
		return "emp.home";
	}
	// end update employer account

}
