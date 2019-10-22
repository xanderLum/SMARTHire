package com.smarthire.main.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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

import com.smarthire.main.dto.JobsAppliedContent;
import com.smarthire.main.models.Employer;
import com.smarthire.main.models.JobPost;
import com.smarthire.main.models.JobPostApplicants;
import com.smarthire.main.models.JobSeeker;
import com.smarthire.main.models.JobSeekerSkills;
import com.smarthire.main.models.SocialMediaAccess;
import com.smarthire.main.models.SocialMediaData;
import com.smarthire.thaliaNew.Process.MainProcess;
import com.smarthire.xander.controller.Monitor;
import com.smarthire.xander.controller.SendMailSenderService;
import com.smarthire.xander.controller.SkillMonitor;
import com.smarthire.xander.models.CHAR_CATEGORYSCORE;
import com.smarthire.xander.models.Certificate;
import com.smarthire.xander.models.Education;
import com.smarthire.xander.models.Experience;
import com.smarthire.xander.models.Image;
import com.smarthire.xander.models.JobAlert;
import com.smarthire.xander.models.JobPostSkills;
import com.smarthire.xander.models.Test;

@Controller
@RequestMapping(value = "/jobseeker")
public class JobSeekerController {
	public static final String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";
	public static final String uriReadSM = SMART_HIRE_URL + "/socialMediaAccess/readUserSM/";
	public static final String uriCreateSMA = SMART_HIRE_URL + "/socialMediaAccess/create/";
	public static final String uriUpdateSMA = SMART_HIRE_URL + "/socialMediaAccess/update/";
	public static final String uriReadJobSeeker = SMART_HIRE_URL + "/jobSeeker/read/";
	public static final String uriSocialMediaDataCreate = SMART_HIRE_URL + "/socialMediaData/create/";
	public static final String uriGetFbDataWeb = "http://localhost:8080/SMARTHire/fbREST/getFbData";
	public static final String uriGetTwitterDataWeb = SMART_HIRE_URL + "/twitterREST/getTwitterData";
	public static final String uriGetLinkedInSkillsWeb = SMART_HIRE_URL + "/linkedInRest/getSkills";
	public static final String uriGetUpworkSkillsWeb = SMART_HIRE_URL + "/getSMData/upWorkAPI/getUpworkSkills";
	public static final String uriCreateJobSeekerSkills = SMART_HIRE_URL + "/jobSeekerSkills/create/";
	public static final String uriGetListJobPosts = SMART_HIRE_URL + "/jobPostRC/";
	public static final String uriGetListAppliedJobPosts = SMART_HIRE_URL + "/jobPostApplicantsRC/getAllJobPosts/";
	public static final String uriCreateJobPostApplicants = SMART_HIRE_URL + "/jobPostApplicantsRC/create/";
	public static final String uriCheckExistApplication = SMART_HIRE_URL
			+ "/jobPostApplicantsRC/checkExistApplication/";

	// profile create
	public static final String uriCreateTest = SMART_HIRE_URL + "/testRC/create/";
	public static final String uriCreateEducation = SMART_HIRE_URL + "/educationRC/create/";
	public static final String uriCreateExperience = SMART_HIRE_URL + "/experienceRC/create/";
	public static final String uriCreateCertificate = SMART_HIRE_URL + "/certificateRC/create/";

	// profile retrieve
	public static final String uriGetUpworkTests = SMART_HIRE_URL + "/getSMData/upWorkAPI/getUpworkTests/";
	public static final String uriGetUpworkProfile = SMART_HIRE_URL + "/getSMData/upWorkAPI/getProfile/";
	public static final String uriGetAllEducationByUser = SMART_HIRE_URL + "/educationRC/getAllEducationByUser/";
	public static final String uriGetAllCertificateByUser = SMART_HIRE_URL + "/certificateRC/getAllCertificatesByUser/";
	public static final String uriGetAllTestByUser = SMART_HIRE_URL + "/testRC/getAllTestByUser/";
	public static final String uriReadJobSeekerSkills = SMART_HIRE_URL + "/jobSeekerSkills/getAllSkillsByUsername/";
	public static final String uriReadAllAlerts = SMART_HIRE_URL + "/jobAlertRC/getAllAlertsByUsername/";

	public static final String uriGetAllExperienceByUser = SMART_HIRE_URL + "/experienceRC/getAllExperienceByUser/";

	// character category scores rest controllers
	public static final String uriCreateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/create/";
	public static final String uriUpdateChar_Category = SMART_HIRE_URL + "/char_catscoreRC/update/";
	public static final String uriReadChar_Category = SMART_HIRE_URL + "/char_catscoreRC/read/";
	public static final String uriReadAllChar_Category = SMART_HIRE_URL + "/char_catscoreRC/getAllCharCatScoreByUser/";
	public static final String uriGetSMDataOfUser = SMART_HIRE_URL + "/socialMediaData/getSMDListOfUser/";
	public static final String uriGetProfilePhotoByUsername = SMART_HIRE_URL + "/fileUploadRC/readByUsername/";
	public static final String uriGetSMDataByID = SMART_HIRE_URL + "/socialMediaData/readStringId/";

	// alerts path
	public static final String uriGetAllJobPostSkillsReqByID = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/";
	public static final String uriCheckExistingAlert = SMART_HIRE_URL + "/jobAlertRC/checkIfExists/";
	public static final String uriUpdateAlert = SMART_HIRE_URL + "/jobAlertRC/update/";
	public static final String uriCreateJobAlert = SMART_HIRE_URL + "/jobAlertRC/create/";

	// logout function of the EMPLOYER actor
	// start logging out function
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/?logout";
	}

	// end logging out function

	// this autowired object will be used in the mailing services of the system
	@Autowired
	SendMailSenderService sendMailService;

	@RequestMapping(value = "/sendMessageJs")
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
			sendMailService.sendMail(email, fromAddress, subject, msgBody);
			System.out.println("Message Sent! Check smarthire mail.");
			System.out.println("Sending reply message to user.");
			String replyMsg = "Hello " + name + "," + "" + ""
					+ "Thank you for your message. I appreciate you sharing your thoughts.  We would like to review your feedback and"
					+ "will get back to you in no time." + "" + "Sincerely," + "" + "Xander" + "SMARTHire Global Inc. "
					+ "ai.smarthire@gmail.com" + "2016";
			sendMailService.sendMail(email, fromAddress, "THANK YOU FOR YOUR FEEDBACK", replyMsg);
			System.out.println("Appreciation Message Sent to user! Let them check their mail.");
			session.setAttribute("message", "Message sent!");

		} catch (Exception e) {
			System.out.println("Message not sent.");
			System.out.println("Catched error = " + e.getMessage());
			e.printStackTrace();
			session.setAttribute("message", "Message not sent.");
		}

		return "js.contact";
	}

	// this function is triggered when the EMPLOYER has valid authentication
	// displays emp.home.jsp
	// start home page viewing
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String dataCollect(Model m, HttpServletRequest req) {
		System.out.println("I'm jobseeker home");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String auth = (String) session.getAttribute("auth");

		String facebookImport = "", twitterImport = "", linkedInImport = "", upWorkImport = "";
		boolean charImport = false;
		boolean skillImport = false;

		// check social media access
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<JobSeeker> rejs = restTemplate.getForEntity(uriReadJobSeeker + username + "/", JobSeeker.class);
		JobSeeker jSeeker = rejs.getBody();
		m.addAttribute("firstname", jSeeker.getFirstname());
		session.setAttribute("firstname", jSeeker.getFirstname());

		System.out.println("Checking social account imports");
		ResponseEntity<SocialMediaAccess> smaFb = restTemplate.getForEntity(uriReadSM + username + "/facebook/",
				SocialMediaAccess.class);

		ResponseEntity<SocialMediaAccess> smaTwitter = restTemplate.getForEntity(uriReadSM + username + "/twitter/",
				SocialMediaAccess.class);

		ResponseEntity<SocialMediaAccess> smaLinkedIn = restTemplate.getForEntity(uriReadSM + username + "/linkedIn/",
				SocialMediaAccess.class);

		ResponseEntity<SocialMediaAccess> smaUpwork = restTemplate.getForEntity(uriReadSM + username + "/upwork/",
				SocialMediaAccess.class);

		System.out.println("--------------------------START check in smaFB");
		if (smaFb.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("--------------------------HttpStatus.OK");
			if (smaFb.getBody().equals(null)) {
				System.out.println("No SMA facebook for (username):" + username);
				m.addAttribute("facebook", "Not Yet Added.");
				m.addAttribute("facebookImport", "Import from Facebook");
				facebookImport = "";
			} else {
				System.out.println("There is SMA facebook for (username):" + username);
				// display the date of last_update of access_token
				m.addAttribute("facebook", "Updated Last: " + smaFb.getBody().getLast_update());
				m.addAttribute("facebookImport", "Update Facebook data");
				facebookImport = "authenticated";

			}
		} else {
			System.out.println("Facebook Not Yet Added");
			m.addAttribute("facebook", "Not Yet Added");
			m.addAttribute("facebookImport", "Import from Facebook");
			facebookImport = "";
		}
		System.out.println("--------------------------END check in smaFB");
		System.out.println("--------------------------START check in smaTwitter");
		if (smaTwitter.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("--------------------------HttpStatus.OK");
			if (smaTwitter.getBody().equals(null)) {
				System.out.println("No SMA twitter for (username):" + username);
				m.addAttribute("twitter", "Not Yet Added.");
				m.addAttribute("twitterImport", "Import from Twitter");
				twitterImport = "";
			} else {
				System.out.println("There is SMA twitter for (username):" + username);
				// display the date of last_update of twitter user_name
				m.addAttribute("twitter", smaTwitter.getBody().getToken_data());
				m.addAttribute("t_last_update", smaTwitter.getBody().getLast_update());
				m.addAttribute("twitterImport", "Update Twitter Data");
				twitterImport = "authenticated";
			}
		} else {
			System.out.println("Twitter Not Yet Added.");
			m.addAttribute("twitter", "Not Yet Added.");
			m.addAttribute("twitterImport", "Import from Twitter");
			twitterImport = "";
		}
		System.out.println("--------------------------END check in smaTwitter");
		System.out.println("--------------------------START check in smaLinkedIn");
		if (smaLinkedIn.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("--------------------------HttpStatus.OK");
			if (smaLinkedIn.getBody().equals(null)) {
				System.out.println("No SMA linkedIn for (username):" + username);
				m.addAttribute("linkedIn", "Not Yet Added.");
				m.addAttribute("linkedInImport", "Import from LinkedIn");
				linkedInImport = "";
			} else {
				System.out.println("There is SMA linkedIn for (username):" + username);
				// display the date of last_update of twitter user_name
				m.addAttribute("linkedIn", smaLinkedIn.getBody().getToken_data());
				m.addAttribute("l_last_update", smaLinkedIn.getBody().getLast_update());
				m.addAttribute("linkedInImport", "Update LinkedIn Data");
				linkedInImport = "authenticated";

			}
		} else {
			System.out.println("linkedIn Not Yet Added.");
			m.addAttribute("linkedIn", "Not Yet Added.");
			m.addAttribute("linkedInImport", "Import from LinkedIn");
			linkedInImport = "";
		}
		System.out.println("--------------------------END check in smaLinkedIn");
		System.out.println("--------------------------START check in smaUpWork");
		if (smaUpwork.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("--------------------------HttpStatus.OK");
			if (smaUpwork.getBody().equals(null)) {
				System.out.println("No SMA UpWork for (username):" + username);
				m.addAttribute("upWorkImport", "Import from Upwork");
				m.addAttribute("UpWork", "Not Yet Added.");
				upWorkImport = "";
			} else {
				System.out.println("There is SMA UpWork for (username):" + username);
				// display the date of last_update of UpWork user_name
				m.addAttribute("UpWork", smaUpwork.getBody().getToken_data());
				m.addAttribute("u_last_update", smaUpwork.getBody().getLast_update());
				m.addAttribute("upWorkImport", "Update Upwork Data");
				upWorkImport = "authenticated";
			}
		} else {
			System.out.println("UpWork Not Yet Added.");
			m.addAttribute("upWorkImport", "Import from Upwork");
			m.addAttribute("UpWork", "Not Yet Added.");
			upWorkImport = "";
		}
		System.out.println("--------------------------END check in smaUpwork");

		// read profile ready to display CV
		restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}

		});

		System.out.println("Preparing jobseeker CV");
		// get All Skills
		String uriGetAllSkillsByUsername = SMART_HIRE_URL + "/jobSeekerSkills/getAllSkillsByUsername/";
		ResponseEntity<JobSeekerSkills[]> reGetAllSkills = restTemplate
				.getForEntity(uriGetAllSkillsByUsername + username + "/", JobSeekerSkills[].class);
		JobSeekerSkills[] sList = null;
		if (reGetAllSkills.getBody() != null) {
			sList = reGetAllSkills.getBody();
		}
		/*
		 * List<String> skillsList = new LinkedList<>(); for(JobSeekerSkills s:
		 * sList){ skillsList.add(s.getSkill_name()); }
		 */

		m.addAttribute("skills", sList);

		String uriGetAllEducation = SMART_HIRE_URL + "/educationRC/getAllEducationByUser/";
		ResponseEntity<Education[]> reGetAllEdu = restTemplate.getForEntity(uriGetAllEducation + username + "/",
				Education[].class);
		Education[] eduList = null;
		if (reGetAllEdu.getBody() != null) {
			eduList = reGetAllEdu.getBody();
		}

		m.addAttribute("educ", eduList);

		String uriGetAllExp = SMART_HIRE_URL + "/experienceRC/getAllExperienceByUser/";
		ResponseEntity<Experience[]> reGetAllExp = restTemplate.getForEntity(uriGetAllExp + username + "/",
				Experience[].class);
		Experience[] expList = null;
		if (reGetAllExp.getBody() != null) {
			expList = reGetAllExp.getBody();
		}

		m.addAttribute("exp", expList);

		String uriGetAllCertificates = SMART_HIRE_URL + "/certificateRC/getAllCertificatesByUser/";
		ResponseEntity<Certificate[]> reGetAllCert = restTemplate.getForEntity(uriGetAllCertificates + username + "/",
				Certificate[].class);
		Certificate[] certList = null;
		if (reGetAllCert.getBody() != null) {
			certList = reGetAllCert.getBody();
		}

		m.addAttribute("cert", certList);

		String uriGetAllTests = SMART_HIRE_URL + "/testRC/getAllTestByUser/";
		ResponseEntity<Test[]> reGetAllTests = restTemplate.getForEntity(uriGetAllTests + username + "/", Test[].class);
		Test[] testList = null;
		if (reGetAllTests.getBody() != null) {
			testList = reGetAllTests.getBody();
		}

		m.addAttribute("cert", certList);
		m.addAttribute("test", testList);
		m.addAttribute("username", username);
		m.addAttribute("js", jSeeker);

		if (!facebookImport.isEmpty() || !twitterImport.isEmpty()) {
			charImport = true;
		}
		if (!linkedInImport.isEmpty() || !upWorkImport.isEmpty()) {
			skillImport = true;
		}
		if (charImport && skillImport) {
			System.out.println("User " + username + " is authenticated proceed view profile");
			session.setAttribute("auth", "authenticated");
			m.addAttribute("state", "authenticated");
		} else {
			session.setAttribute("auth", "toComplete");
			m.addAttribute("state", "toComplete");
		}

		return "js.home";
	}
	// end homepage viewing

	// this function is triggered when the jobseeker updates his image
	// start update profile photo
	@RequestMapping(value = "/jsUpdateImage", method = RequestMethod.POST)
	public String jsUpdateImage(Model m, @RequestParam(value = "image") CommonsMultipartFile image,
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
				// System.out.println("img 1 toString = "+img.toString());
				// System.out.println("the one to update =
				// "+reImageRead.getBody().toString());
				// if (reImageRead.getBody() != null) {
				if (reImageRead.getBody() != null) {
					System.out.println("There is an image saved for this username.  Updating image...");
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
				// }
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
	// end update profile photo

	// this function is triggered when jobseeker edits his account
	// start edit jobseeker account
	@RequestMapping(value = "/jsEditAccounts", method = RequestMethod.GET)
	public String jsEdit(Model m, @RequestParam(value = "username") String username, HttpServletRequest req) {
		System.out.println("Proceed to editAccountJobSeeker Page");
		HttpSession session = req.getSession();
		session.setAttribute("username", username);
		m.addAttribute("state", "editAccount");
		m.addAttribute("username", username);
		return "js.home";
	}
	// end edit jobseeker account

	// this function is triggered when jobseeker hits edit personal account
	// start update personal account function
	@RequestMapping(value = "/jsUpdatePersonalAccount", method = RequestMethod.POST)
	public String jsRegister(Model m, @ModelAttribute JobSeeker jobSeeker, HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("Registering jobseeker ");
		String myURI = SMART_HIRE_URL + "/jobSeeker/update/";

		// reading attributes
		JobSeeker js = (JobSeeker) jobSeeker;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		System.out.println("Printing modelAttribute jobseeker properties");
		System.out.println("" + js.toString());
		// read JobSeeker first
		ResponseEntity<JobSeeker> reJobseekerRead = restTemplate.getForEntity(uriReadJobSeeker + js.getUsername() + "/",
				JobSeeker.class);
		if (reJobseekerRead.getStatusCode().equals(HttpStatus.OK)) {
			// update personal profile
			js.setPassword(reJobseekerRead.getBody().getPassword());
			js.setRole(reJobseekerRead.getBody().getRole());
			restTemplate.put(myURI, js);
		} else {
			// catch The User can't changed his username
			System.out.println("Username has been changed!  Security breached!");
		}

		session.setAttribute("username", js.getUsername());
		m.addAttribute("js", js);
		m.addAttribute("username", js.getUsername());
		return "redirect:../home";
	}
	// end update personal account function

	// this function is triggered when jobseeker hits ADD EDUCATION button in
	// his viewing page
	// start add education function
	@RequestMapping(value = "/addEducation", method = RequestMethod.POST)
	public String jsAddEducation(Model m, @ModelAttribute Education education,
			@RequestParam(value = "username") String uname, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String username = (String) uname;
		System.out.println("Adding Education");
		String addEduURI = SMART_HIRE_URL + "/educationRC/create/";
		String checkEducation = SMART_HIRE_URL + "/educationRC/read/";
		String updateEdu = SMART_HIRE_URL + "/educationRC/update/";

		education.setUsername(username);
		session.setAttribute("username", username);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		System.out.println("Printing Education before adding");
		System.out.println("" + education.toString());
		ResponseEntity<Education> reEduc = restTemplate.getForEntity(
				checkEducation + education.getUsername() + "/" + education.getSchoolName() + "/", Education.class);
		if (reEduc.getBody() != null) {
			education.setId(reEduc.getBody().getId());
			restTemplate.put(updateEdu, education);
			// there exists a school with same qualifications
			m.addAttribute("eduMsg", "Already existing educational background");
			return "redirect:../home";
		} else {
			// no record need to update first - if no data then create;
			// restTemplate.put(updateEdu, education);
			System.out.println("Successfully updated Education");
			ResponseEntity<Education> reAddEdu = restTemplate.postForEntity(addEduURI, education, Education.class);
			if (reAddEdu.getBody() != null) {
				System.out.println("EDUCATION added");
			} else {
				System.out.println("failed to add educ background on create");
			}
		}
		return "redirect:../home";
	}
	// end add education function

	// this function is triggered when jobseeker hits ADD EXPERIENCE button in
	// his viewing page
	// start add experience function
	@RequestMapping(value = "/addExperience", method = RequestMethod.POST)
	public String jsaddExperience(Model m, @ModelAttribute Experience experience,
			@RequestParam(value = "username") String uname, HttpServletRequest req) {
		HttpSession session = req.getSession();
		System.out.println("Adding Education");
		String addExpURI = SMART_HIRE_URL + "/experienceRC/create/";
		String checkexperience = SMART_HIRE_URL + "/experienceRC/read/";
		String updateExp = SMART_HIRE_URL + "/experienceRC/update/";
		String username = (String) uname;

		session.setAttribute("username", username);
		experience.setUsername(username);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		System.out.println("Printing Education before adding");
		System.out.println("" + experience.toString());
		ResponseEntity<Experience> reEduc = restTemplate.getForEntity(
				checkexperience + experience.getUsername() + "/" + experience.getEmployer() + "/", Experience.class);
		if (reEduc.getBody() != null) {
			// there exists a school with same qualifications
			experience.setId(reEduc.getBody().getId());

			restTemplate.put(updateExp, experience);
			m.addAttribute("eduMsg", "Already existing educational background");
			return "redirect:../home";
		} else {
			// no record need to update first - if no data then create;
			System.out.println("Successfully updated Experience");
			ResponseEntity<Experience> reAddExp = restTemplate.postForEntity(addExpURI, experience, Experience.class);
			if (reAddExp.getBody() != null) {
				System.out.println("Experience added");
			} else {
				System.out.println("failed to add exp background on create");
			}
		}
		return "redirect:../home";
	}
	// end add experience function

	// this function is triggered when the JOBSEEKER hits JOB ALERTS button in
	// his viewing page
	// start viewing jobAlerts function
	@RequestMapping(value = "/viewJobAlerts{username}", method = RequestMethod.GET)
	public String dashBoardContent(Model m, HttpServletRequest req, @PathVariable String username) {
		HttpSession session = req.getSession();
		String uname = (String) session.getAttribute("username");
		session.setAttribute("username", uname);
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("state", "alerts");

		session.setAttribute("username", username);
		m.addAttribute("username", username);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		ResponseEntity<JobAlert[]> reGetAllJobAlerts = restTemplate.getForEntity(uriReadAllAlerts + uname + "/",
				JobAlert[].class);
		if (reGetAllJobAlerts.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("Success read all job alerts");
			m.addAttribute("jpAlerts", reGetAllJobAlerts.getBody());
		} else {
			System.err.println("Failed to read alerts!");
		}

		return "js.home";
	}
	// end view jobAlerts function

	// this function is triggered when the jobseeker views his job applications
	// start viewing of jobs applied
	@RequestMapping(value = "/contentViewJobsApplied", method = RequestMethod.GET)
	public String viewJobsAppliedContent(Model m, HttpServletRequest req) {
		System.out.println("Here");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("firstname", firstname);
		// here to retrieve all job post applied by applicant

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy");
		Date date;
		DateFormat dateFormatDb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ResponseEntity<JobPostApplicants[]> rejpArray = restTemplate.getForEntity(uriGetListAppliedJobPosts + username,
				JobPostApplicants[].class);

		ArrayList<JobsAppliedContent> aJPA = new ArrayList<>();
		JobsAppliedContent jac;
		String date_applied;
		String job_title;
		String job_description;
		String company;

		for (JobPostApplicants jjp : rejpArray.getBody()) {
			try {
				ResponseEntity<JobPost> rejp = restTemplate
						.getForEntity(uriGetListJobPosts + "read/" + jjp.getJob_post_id() + "/", JobPost.class);
				job_title = rejp.getBody().getJob_title();
				job_description = rejp.getBody().getJob_description();
				company = rejp.getBody().getUsername();
				System.out.println("Date: " + jjp.getDate_applied());
				date_applied = sdfDisplay.format(dateFormatDb.parse(jjp.getDate_applied()));
				System.out.println("Here");
				jac = new JobsAppliedContent(job_title, job_description, date_applied, company);
				// jjp.setDate_applied(sdfDisplay.format(dateFormatDb.parse(jjp.getDate_applied())));
				// //converting date to readable date
				aJPA.add(jac);
			} catch (ParseException e) {
				System.out.println("Here catch Parse exception contentViewJobsApplied: " + e);
				e.printStackTrace();
			}
		}
		Collections.sort(aJPA, new Comparator<JobsAppliedContent>() {
			public int compare(JobsAppliedContent j1, JobsAppliedContent j2) {
				return j2.getDate_applied().compareTo(j1.getDate_applied());
			}
		});

		m.addAttribute("aJPA", aJPA);
		m.addAttribute("state", "viewJobsApplied");
		return "js.home";
	}
	// end viewing of jobs applied

	// this function is triggered when the JOBSEEKER hits the JOB postings and
	// view the job
	// start viewing job posting function
	@RequestMapping(value = "/viewJob/{job_post_id}", method = RequestMethod.GET)
	public String viewJob(Model m, @PathVariable Long job_post_id, HttpServletRequest req) {
		System.out.println("I'm viewing job");
		HttpSession session = req.getSession();
		String uriReadJobPost = SMART_HIRE_URL + "/jobPostRC/read/" + job_post_id + "/";
		String uriReadEmployer = SMART_HIRE_URL + "/employerRC/read/";
		String uriReadJobPostSkillsRequirement = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/" + job_post_id + "/";
		String uriReadJobPostSkillsReq = SMART_HIRE_URL + "/jobPostSkillsRC/getListById/";

		String username = (String) session.getAttribute("username");
		System.out.println("session username = " + username);
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("firstname", firstname);

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		JobPost jp = null;
		ResponseEntity<JobPost> reJobPost = restTemplate.getForEntity(uriReadJobPost, JobPost.class);
		if (reJobPost.getBody() != null) {
			jp = reJobPost.getBody();
			System.err.println("\nJobPost retrieved!");
			ResponseEntity<Employer> reEmployer = restTemplate
					.getForEntity(uriReadEmployer + reJobPost.getBody().getUsername() + "/", Employer.class);
			if (reEmployer.getBody() != null) {
				System.out.println("Retrieved employer from the job post id = " + reJobPost.getBody().getJob_post_id());
				System.err.println("Employer retrieved!");
				m.addAttribute("emp", reEmployer.getBody());
			}

			ResponseEntity<JobPostSkills[]> readSkillsReq = restTemplate
					.getForEntity(uriReadJobPostSkillsReq + "/" + job_post_id + "/", JobPostSkills[].class);
			if (readSkillsReq.getBody() != null) {
				m.addAttribute("skillsReq", readSkillsReq.getBody());
				System.err.println("Required skills retrieved!");
			}

			m.addAttribute("jp", reJobPost.getBody());
			// m.addAttribute("state", "viewJob");
		} else {
			System.err.println("read job post failed");

		}

		session.setAttribute("username", username);
		m.addAttribute("username", username);
		m.addAttribute("state", "viewJob");
		System.out.println("Proceed to view js.home --> js.job page");
		return "js.home";
	}
	// end viewing job posting function

	// this function is triggered when the JOBSEEKER hits the BROWSE JOBS button
	// start browsing jobs function
	@RequestMapping(value = "/contentViewRecentJobs", method = RequestMethod.GET)
	public String viewRecentJobsContent(Model m, @RequestParam(value = "username") String uname,
			HttpServletRequest req) {
		System.out.println("I'm contentViewRecentJobs");
		HttpSession session = req.getSession();
		// String username = (String) session.getAttribute("username");
		String username = (String) uname;
		System.out.println("session username = " + username);
		// String firstname = (String) session.getAttribute("firstname");
		// m.addAttribute("firstname", firstname);

		session.setAttribute("username", username);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		SimpleDateFormat sdfDisplay = new SimpleDateFormat("MMMM dd, yyyy");
		Date date;
		DateFormat dateFormatDb = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ResponseEntity<JobPost[]> rejpArray = restTemplate.getForEntity(uriGetListJobPosts, JobPost[].class);
		ArrayList<JobPost> aJP = new ArrayList<>();
		for (JobPost jjp : rejpArray.getBody()) {
			try {
				System.out.println(dateFormatDb.parse(jjp.getDate_posted()));
				jjp.setDate_posted(sdfDisplay.format(dateFormatDb.parse(jjp.getDate_posted()))); // converting
																									// date
																									// to
																									// readable
				System.out.println(dateFormatDb.parseObject(jjp.getClosing_date())); // date
				jjp.setClosing_date(sdfDisplay.format(dateFormatDb.parse(jjp.getClosing_date())));

			} catch (ParseException e) {
				e.printStackTrace();
			}
			aJP.add(jjp);
		}

		Collections.sort(aJP, new Comparator<JobPost>() {

			public int compare(JobPost j1, JobPost j2) {
				return j2.getDate_posted().compareTo(j1.getDate_posted());
			}
		});

		m.addAttribute("aJP", aJP);

		m.addAttribute("state", "viewRecentJobs");
		System.out.println("finished in contentViewRecentJobPosts");
		return "js.home";
	}
	// end browsing jobs function

	// this function is triggered when the JOBSEEKER hits the ACCOUNT button
	// from his viewing page
	// start redirecting to edit account page
	@RequestMapping(value = "/editAccountPage", method = RequestMethod.GET)
	public String jobseekerEditAccount(Model m, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("session username = " + username);
		String firstname = (String) session.getAttribute("firstname");
		m.addAttribute("firstname", firstname);
		m.addAttribute("state", "editAccount");
		System.out.println("I'm jobseeker editAccount");
		return "js.editAccount";
		// return "redirect:../home";
	}
	// end redirecting to edit account page

	// this function is triggered when the JOBSEEKER hits the APPLY button from
	// his viewing page
	// start APPLY JOB function
	@RequestMapping(value = "/applyJob/{job_post_id}", method = RequestMethod.GET)
	public String applyJob(Model m, HttpServletRequest req, @PathVariable Long job_post_id) {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("session username = " + username);
		System.out.println("Apply Job. Jobseeker Controller. job_post_id: " + job_post_id);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		session.setAttribute("username", username);

		JobPostApplicants jpa = new JobPostApplicants(job_post_id, username, "");
		ResponseEntity<JobPostApplicants> readExistingApplication = restTemplate.getForEntity(
				uriCheckExistApplication + "/" + job_post_id + "/" + username + "/", JobPostApplicants.class);
		if (readExistingApplication.getBody() != null) {
			// there is an existing application do not add
			return "redirect:../contentViewRecentJobs?username=" + username;
		} else {
			// no application record ; create
			ResponseEntity<JobPostApplicants> rejp = restTemplate.postForEntity(uriCreateJobPostApplicants, jpa,
					JobPostApplicants.class);
			if (rejp.getStatusCode().equals(HttpStatus.OK)) {
				// return "redirect:../contentViewJobsApplied";
				return "redirect:../contentViewRecentJobs?username=" + username;
			} else {
				return "redirect:../contentViewRecentJobs?username=" + username;
			}
		}
	}
	// end apply job function

	// this function is triggered when the JOBSEEKER hits the IMPORT FROM
	// FACEBOOK button from his viewing page
	// start LOGGING in to FACEBOOK. AUTHENTICATION is done by FACEBOOK server
	// AUTHENTICATED account proceeds to popup window for GRANTING ACCESS by
	// user the SMARTHire Application
	// collect data if granted
	// start FACEBOOK FUNCTION
	@Autowired(required = false)
	@RequestMapping(value = "/accessTokenSend/", method = RequestMethod.GET)
	public void ajaxFacebookLogin(Model m,
			@RequestParam(value = "accessToken", defaultValue = "null") String accessToken,
			@RequestParam(value = "userID", defaultValue = "null") String userID,
			@RequestParam(value = "expiresIn", defaultValue = "null") String expiresIn,
			@RequestParam(value = "signedRequest", defaultValue = "null") String signedRequest,
			@RequestParam(value = "username", defaultValue = "null") String username) {
		System.out.println("********** Here in accessTokenSend. Username: " + username);
		final String uria = "http://localhost:8080/SMARTHire/fbREST/extendAccessToken";
		String shortAccessToken = accessToken;
		System.out.println("===Short Live AccessToken: " + shortAccessToken);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		String extendedAccessToken = restTemplate.postForObject(uria, accessToken, String.class);
		System.out.println("=====Extended AccessToken: " + extendedAccessToken);

		// START Getting the current date and save it to last update
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		// END Getting the current date and save it to last update(String)

		ResponseEntity<SocialMediaAccess> smaFb = restTemplate.getForEntity(uriReadSM + username + "/facebook/",
				SocialMediaAccess.class);
		System.out.println("--------------------------START check in smaFB");
		SocialMediaAccess smaNewCreate = new SocialMediaAccess(username, "facebook", "access_token",
				extendedAccessToken, "long_live", dateFormat.format(date));
		if (smaFb.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println("--------------------------*STARTThere is smaFB in database. need to UPDATE. smaFB.id: "
					+ smaFb.getBody().getSma_id());

			smaNewCreate.setSma_id(smaFb.getBody().getSma_id());
			restTemplate.put(uriUpdateSMA, smaNewCreate);
			// ResponseEntity<SocialMediaAccess> resmaUpdated =
			// restTemplate.getForEntity(uriReadSM+username+"/facebook/",SocialMediaAccess.class
			// ,smaNewCreate);
			// ResponseEntity<SocialMediaAccess> resmaUpdated =
			// restTemplate.postForEntity(uriUpdateSM, smaNewCreate,
			// SocialMediaAccess.class);
			System.out.println("--------------------------*ENDThere is smaFB in database. need to UPDATE");
			// restTemplate.put(uriReadSM+username+"/facebook/", smaFb);
		} else {
			System.out.println("--------------------------*START There is NO smaFB in database. need to CREATE");
			System.out.println("Username in smaNewCreate: " + smaNewCreate.getUsername());
			System.out.println("Username in jobseeker: " + username);
			ResponseEntity<SocialMediaAccess> resmaCreated = restTemplate.postForEntity(uriCreateSMA, smaNewCreate,
					SocialMediaAccess.class);
			System.out.println("--------------------------*END There is NO smaFB in database. need to CREATE");
		}

		// START getting facebook data
		System.out.println("Getting facebook data");

		ResponseEntity<SocialMediaData[]> smDataArr = restTemplate.postForEntity(uriGetFbDataWeb, smaNewCreate,
				SocialMediaData[].class);

		System.out.println("Here padung create");
		// START storing of facebook data to database
		for (SocialMediaData smData : smDataArr.getBody()) {
			ResponseEntity<SocialMediaData> oldsmd = restTemplate
					.getForEntity(uriGetSMDataByID + smData.getData_id() + "/", SocialMediaData.class);
			if (oldsmd.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println("Social Media Data Id = " + smData.getData_id() + " exists already");
			} else {
				System.out
						.println("Social Media Data id = " + smData.getData_id() + " doesn't exists. Need to create..");
				ResponseEntity<SocialMediaData> smD = restTemplate.postForEntity(uriSocialMediaDataCreate, smData,
						SocialMediaData.class);
			}
		}
		// END storing of facebook data to database
		// END getting facebook data

		ResponseEntity<SocialMediaData[]> allSMDataUser = restTemplate.getForEntity(uriGetSMDataOfUser + username + "/",
				SocialMediaData[].class);

		for (SocialMediaData smData : allSMDataUser.getBody()) {
			System.out.println("Printing: " + smData.getData());
		}

		doAsyncProcessCharacter(username);

	}
	// end FACEBOOK FUNCTION

	// this function is triggered when the JOBSEEKER hits the IMPORT FROM
	// TWITTER button from his viewing page
	// start connecting to TWITTER API
	// start collecting data (no authentication needed when TWITTER profile is
	// public, otherwise AUTHENTICATE)
	// start collection of data if VALID
	@RequestMapping(value = "/twitterUpdateUsername/", method = RequestMethod.POST)
	public String twitterUpdateUsername(HttpServletRequest req,
			@RequestParam(value = "t_username", defaultValue = "null") String t_username) {

		System.out.println("Updating twitter data!");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("Here twitter Update Username: " + username);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		SocialMediaAccess smaNewCreate = new SocialMediaAccess(username, "twitter", "twitter_username", t_username,
				"long_live", dateFormat.format(date));
		ResponseEntity<SocialMediaAccess> smaTwitter = restTemplate.getForEntity(uriReadSM + username + "/twitter/",
				SocialMediaAccess.class);

		System.out.println("--------------------------START check in smaTwitter. username: " + username);
		if (smaTwitter.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println(
					"--------------------------***START. There is smaTwitter in database. need to UPDATE. smaFB.id: "
							+ smaTwitter.getBody().getSma_id());
			smaNewCreate.setSma_id(smaTwitter.getBody().getSma_id());
			restTemplate.put(uriUpdateSMA, smaNewCreate);
			System.out.println(
					"--------------------------***END. There is smaTwitter in database. need to UPDATE. smaFB.id: "
							+ smaTwitter.getBody().getSma_id());
		} else {
			System.out.println(
					"--------------------------******START. There is NO smaTwitter in database. need to CREATE.");
			ResponseEntity<SocialMediaAccess> resmaCreated = restTemplate.postForEntity(uriCreateSMA, smaNewCreate,
					SocialMediaAccess.class);
			System.out.println(
					"--------------------------******END. There is NO smaTwitter in database. need to CREATE.");
		}
		System.out.println("--------------------------END check in smaTwitter");

		// START getting twitter data
		System.out.println("Getting twitter data");

		ResponseEntity<SocialMediaData[]> smDataArr = restTemplate.postForEntity(uriGetTwitterDataWeb, smaNewCreate,
				SocialMediaData[].class);
		System.out.println("+++START Twitter update data. smdLength: " + smDataArr.getBody().length);
		// START storing of twitter data to database
		for (SocialMediaData smData : smDataArr.getBody()) {
			ResponseEntity<SocialMediaData> oldsmd = restTemplate
					.getForEntity(uriGetSMDataByID + smData.getData_id() + "/", SocialMediaData.class);
			if (oldsmd.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println("Social Media Data Id = " + smData.getData_id() + " exists already");
			} else {
				System.out
						.println("Social Media Data id = " + smData.getData_id() + " doesn't exists. Need to create..");
				ResponseEntity<SocialMediaData> smD = restTemplate.postForEntity(uriSocialMediaDataCreate, smData,
						SocialMediaData.class);
			}
			/*
			 * System.out.println("Printing: " + smData.getData());
			 * ResponseEntity<SocialMediaData> smD =
			 * restTemplate.postForEntity(uriSocialMediaDataCreate, smData,
			 * SocialMediaData.class);
			 */
		}
		System.out.println("+++END Updating twitter data+++");

		// END storing of twitter data to database
		// END getting twitter data
		doAsyncProcessCharacter(username);
		//
		// HttpSession session = req.getSession();
		// String username = (String) session.getAttribute("username");
		// doAsyncProcessCharacter(username);
		return "redirect:../home";
	}
	// end TWITTER FUNCTION

	// this function is triggered when the JOBSEEKER hits the IMPORT FROM
	// LINKEDIN button from his viewing page
	// start connecting to JSOUP CRAWLER
	// start crawling data
	// start LINKEDIN function
	@RequestMapping(value = "/linkedInUpdateURL/", method = RequestMethod.POST)
	public String linkedInUpdateURL(HttpServletRequest req,
			@RequestParam(value = "l_publicUrl", defaultValue = "null") String l_publicUrl) {
		System.out.println("Updating LinkedIn Data!");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("Here linkedInUpdateURL: " + username);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		SocialMediaAccess smaNewCreate = new SocialMediaAccess(username, "linkedin", "linkedin_url", l_publicUrl,
				"long_live", dateFormat.format(date));
		ResponseEntity<SocialMediaAccess> smaLinkedIn = restTemplate.getForEntity(uriReadSM + username + "/linkedin/",
				SocialMediaAccess.class);

		System.out.println("--------------------------START check in smaLinkedIn. username: " + username);
		if (smaLinkedIn.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println(
					"--------------------------***START. There is smaLinkedIn in database. need to UPDATE. smaFB.id: "
							+ smaLinkedIn.getBody().getSma_id());
			smaNewCreate.setSma_id(smaLinkedIn.getBody().getSma_id());
			restTemplate.put(uriUpdateSMA, smaNewCreate);
			System.out.println(
					"--------------------------***END. There is smaLinkedIn in database. need to UPDATE. smaFB.id: "
							+ smaLinkedIn.getBody().getSma_id());
		} else {
			System.out.println(
					"--------------------------******START. There is NO smaLinkedIn in database. need to CREATE.");
			ResponseEntity<SocialMediaAccess> resmaCreated = restTemplate.postForEntity(uriCreateSMA, smaNewCreate,
					SocialMediaAccess.class);
			System.out.println(
					"--------------------------******END. There is NO smaLinkedIn in database. need to CREATE.");
		}
		System.out.println("--------------------------END check in smaLinkedIn");

		// START getting linkedin data
		ResponseEntity<String[]> allSkills = restTemplate.postForEntity(uriGetLinkedInSkillsWeb, l_publicUrl,
				String[].class);
		System.out.println("+++START Here padung create+++. Length: " + allSkills.getBody().length);
		// START storing of linkedin data to database
		JobSeekerSkills jss = new JobSeekerSkills();
		LinkedList<String> seekerSkills = new LinkedList<>();
		boolean proceed = false;
		for (String skill : allSkills.getBody()) {
			jss.setUsername(username);
			jss.setSkill_name(skill);
			seekerSkills.add(skill);
			System.out.println("In JobSeeker Controller. Skills are:: " + skill);
			ResponseEntity<JobSeekerSkills> rejsk = restTemplate.postForEntity(uriCreateJobSeekerSkills, jss,
					JobSeekerSkills.class);
			proceed = true;
		}
		if (proceed) {
			// start reading job postings and matched skills to alert jobseeker
			ResponseEntity<JobPost[]> readAllJobPostings = restTemplate.getForEntity(uriGetListJobPosts,
					JobPost[].class);
			if (readAllJobPostings.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println(">>Retrieved posts");
				for (JobPost jp : readAllJobPostings.getBody()) {
					LinkedList<String> jobPostSkillsReq = new LinkedList<>();
					ResponseEntity<JobPostSkills[]> getAllJobPostSkillsReq = restTemplate.getForEntity(
							uriGetAllJobPostSkillsReqByID + jp.getJob_post_id() + "/", JobPostSkills[].class);
					if (getAllJobPostSkillsReq.getStatusCode().equals(HttpStatus.OK)) {
						System.out.println(">>>Retrieved JobPostSkills");
						for (JobPostSkills s : getAllJobPostSkillsReq.getBody()) {
							jobPostSkillsReq.add(s.getSkill_name());
						}
						if (jobPostSkillsReq.isEmpty()) {
							System.out.println("JOB POST ID has empty skills req.. skip..");
						} else {
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
								j.setJsUsername(username);
								j.setStatus("Not yet Applied");
								j.setDateCreated(jp.getDate_posted());
								j.setKeywords(keywords.substring(0, keywords.length() - 1));
								ResponseEntity<JobAlert> readExisting = restTemplate.getForEntity(
										uriCheckExistingAlert + "/" + username + "/" + jp.getJob_post_id() + "/",
										JobAlert.class);
								if (readExisting.getBody() != null) {
									// update alert
									j.setId(readExisting.getBody().getId());
									j.setJob_post_id(readExisting.getBody().getJob_post_id());
									restTemplate.put(uriUpdateAlert, j);
								} else {
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
			}
		} else {
			// no alerts created
		}

		System.out.println("+++END Here padung create+++");
		// END storing of linkedin data to database
		// END getting linkedin data
		return "redirect:../home";
	}
	// end LINKEDIN function

	// this function is triggered when the JOBSEEKER hits the IMPORT FROM
	// UPWORK button from his viewing page
	// start connecting to JSOUP CRAWLER
	// start crawling data
	// start UPWORK function
	@RequestMapping(value = "/upworkUpdateURL/", method = RequestMethod.POST)
	public String upworkUpdateURL(HttpServletRequest req,
			@RequestParam(value = "u_publicUrl", defaultValue = "null") String u_publicUrl) {
		System.out.println(">>> Start Upwork crawling...");
		System.out.println("Updating Upwork Data!");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("Here upWorkUpdateURL: " + username);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		SocialMediaAccess smaNewCreate = new SocialMediaAccess(username, "upWork", "upWorkUrl", u_publicUrl,
				"long_live", dateFormat.format(date));
		ResponseEntity<SocialMediaAccess> smaUpWork = restTemplate.getForEntity(uriReadSM + username + "/upWork/",
				SocialMediaAccess.class);

		System.out.println("--------------------------START check in smaUpWork. username: " + username);
		if (smaUpWork.getStatusCode().equals(HttpStatus.OK)) {
			System.out.println(
					"--------------------------***START. There is smaUpWork in database. need to UPDATE. smaFB.id: "
							+ smaUpWork.getBody().getSma_id());
			smaNewCreate.setSma_id(smaUpWork.getBody().getSma_id());
			restTemplate.put(uriUpdateSMA, smaNewCreate);
			System.out.println(
					"--------------------------***END. There is smaUpWork in database. need to UPDATE. smaFB.id: "
							+ smaUpWork.getBody().getSma_id());
		} else {
			System.out.println(
					"--------------------------******START. There is NO smaUpWork in database. need to CREATE.");
			ResponseEntity<SocialMediaAccess> resmaCreated = restTemplate.postForEntity(uriCreateSMA, smaNewCreate,
					SocialMediaAccess.class);
			System.out
					.println("--------------------------******END. There is NO smaUpWork in database. need to CREATE.");
		}
		System.out.println("--------------------------END check in smaUpWork");

		// START getting upWork data skills
		ResponseEntity<String[]> allSkills = restTemplate.postForEntity(uriGetUpworkSkillsWeb + "/" + username + "/",
				u_publicUrl, String[].class);
		System.out.println("upwork allskills.getbody length =  " + allSkills.getBody().length);

		/*
		 * ResponseEntity<TestDTO[]> allTests =
		 * restTemplate.postForEntity(uriGetUpworkTests, u_publicUrl,
		 * TestDTO[].class); System.out.println(
		 * "upwork alltests.getbody length = " + allTests.getBody().length);
		 */
		// START storing of upWork data to database
		LinkedList<String> seekerSkills = new LinkedList<>();
		JobSeekerSkills jss = new JobSeekerSkills();
		boolean proceed = false;
		for (String skill : allSkills.getBody()) {
			jss.setUsername(username);
			jss.setSkill_name(skill);
			seekerSkills.add(skill);
			System.out.println("In JobSeeker Controller. UpworkSkills are:: " + skill);
			ResponseEntity<JobSeekerSkills> rejsk = restTemplate.postForEntity(uriCreateJobSeekerSkills, jss,
					JobSeekerSkills.class);
			if (rejsk.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println("Success create jobseeker skills!");
				proceed = true;
			} else {
				System.out.println("Failed to create jobseeker skill!");
			}
		}

		if (proceed) {
			// start reading job postings and matched skills to alert jobseeker
			ResponseEntity<JobPost[]> readAllJobPostings = restTemplate.getForEntity(uriGetListJobPosts,
					JobPost[].class);
			if (readAllJobPostings.getStatusCode().equals(HttpStatus.OK)) {
				System.out.println(">>Retrieved posts");
				for (JobPost jp : readAllJobPostings.getBody()) {
					LinkedList<String> jobPostSkillsReq = new LinkedList<>();
					ResponseEntity<JobPostSkills[]> getAllJobPostSkillsReq = restTemplate.getForEntity(
							uriGetAllJobPostSkillsReqByID + jp.getJob_post_id() + "/", JobPostSkills[].class);
					if (getAllJobPostSkillsReq.getStatusCode().equals(HttpStatus.OK)) {
						System.out.println(">>>Retrieved JobPostSkills");
						for (JobPostSkills s : getAllJobPostSkillsReq.getBody()) {
							jobPostSkillsReq.add(s.getSkill_name());
						}
						if (jobPostSkillsReq.isEmpty()) {
							System.out.println("JOB POST ID has empty skills req.. skip..");
						} else {
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
								j.setJsUsername(username);
								j.setStatus("Not yet Applied");
								j.setDateCreated(jp.getDate_posted());
								j.setKeywords(keywords.substring(0, keywords.length() - 1));
								ResponseEntity<JobAlert> readExisting = restTemplate.getForEntity(
										uriCheckExistingAlert + "/" + username + "/" + jp.getJob_post_id() + "/",
										JobAlert.class);
								if (readExisting.getBody() != null) {
									// update alert
									j.setId(readExisting.getBody().getId());
									j.setJob_post_id(readExisting.getBody().getJob_post_id());
									restTemplate.put(uriUpdateAlert, j);
								} else {
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
			}
		} else {
			// no alerts created
		}

		System.out.println(">>> END Upwork data crawling!");
		// END storing of upWork data to database
		// END getting upWork data
		return "redirect:../home";
	}
	// end UPWORK function

	// this function is triggered for pre population of data
	// this redirects to a test page
	// this enables testing of asynchronous process of retrieving data from
	// social networking sites
	@Async
	public void doAsyncProcessCharacter(String username) {
		// start asynchronous process
		System.out.println("creating new thread async...");
		System.out.println("start category scoring...");
		System.out.println("username doAsync = " + username);
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
			// System.out.println("------------Printing: " +
			// smData.getData());
			alsmd.add(smData);
		}

		System.out.println(">>>>>>>>>>>>>>>TOTAL NO OF POSTS: " + alsmd.size());

		MainProcess pc = new MainProcess();
		pc.setUsername(username);
		try {
			LinkedList<CHAR_CATEGORYSCORE> llcs = new LinkedList<>();
			pc.main(alsmd);
			llcs = pc.getLlcs();
			Monitor monitor = new Monitor();
			monitor.startRealMonitor(llcs);
			LinkedList<CHAR_CATEGORYSCORE> cscoreList = monitor.getCscoreList();

			System.out.println("\n======start print of category scores for username : " + username);
			// reupdate db
			// CHAR_CATEGORYSCORE ccts;
			System.out.println("CHAR_CATEGORYSCORE DATA LIST");
			for (CHAR_CATEGORYSCORE ccts : cscoreList) {
				System.out.println("" + ccts.toString());
				System.out.println("Ready to update char_categoryscore table");
				ResponseEntity<CHAR_CATEGORYSCORE> readCts = restTemplate.getForEntity(
						uriReadChar_Category + "/" + username + "/" + ccts.getCategoryName() + "/",
						CHAR_CATEGORYSCORE.class);
				System.out.println("read record char category = " + ccts.getCategoryName());

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
	// end asynchronous function
}
