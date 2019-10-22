package com.smarthire.main.controller.api.upwork;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.smarthire.main.models.JobSeeker;
import com.smarthire.xander.dto.Certificate;
import com.smarthire.xander.dto.Profile;
import com.smarthire.xander.dto.TestDTO;
import com.smarthire.xander.models.Test;

@Controller
@RequestMapping(value = "/getSMData/upWorkAPI")
public class UpworkJavaController {
	private final static String up_key = "816088c9c63de8ce9844247760fb035b"; // request
																				// token
	private final static String up_secret = "4c2d2ca5e26803a1"; // secret
	private final static String SMART_HIRE_URL = "http://localhost:8080/SMARTHire";

	// profile create
	public static final String uriCreateTest = SMART_HIRE_URL + "/testRC/create/";
	public static final String uriCreateEducation = SMART_HIRE_URL + "/educationRC/create/";
	public static final String uriCreateExperience = SMART_HIRE_URL + "/experienceRC/create/";
	public static final String uriCreateCertificate = SMART_HIRE_URL + "/certificateRC/create/";
	public static final String uriUpdateProfile = SMART_HIRE_URL + "/jobSeeker/update/";
	public static final String uriReadProfile = SMART_HIRE_URL + "/jobSeeker/read/";
	public static final String uriCreateProfile = SMART_HIRE_URL + "/jobSeeker/create/";
	public static final String uriReadTest = SMART_HIRE_URL + "/testRC/read/";
	public static final String uriUpdateTest = SMART_HIRE_URL +"/testRC/update/";

	public static final String uriGetAllEducationByUser = SMART_HIRE_URL + "/educationRC/getAllEducationByUser/";
	public static final String uriGetAllCertificateByUser = SMART_HIRE_URL + "/certificateRC/getAllCertificatesByUser/";

	private LinkedList<String> skills;
	// private LinkedList<TestDTO> testsList;
	// private LinkedList<Certificate> certList;
	// private LinkedList<Experience> expList;

	@RequestMapping(value = "/getUpworkSkills/{username}/", method = RequestMethod.POST)
	public ResponseEntity<List<String>> getUpworkSkills(@RequestBody String u_publicUrl,
			@PathVariable String username) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});

		skills = new LinkedList<>();
		// List<String> allSkills = new ArrayList<>();
		System.out.println("Public URL is: " + u_publicUrl.trim());
		try {
			System.out.println("Establishing connection");
			Document doc = Jsoup.connect(u_publicUrl.trim())
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.get();
			System.out.println("Document connected");
			String toString = doc.toString();
			System.out.println("getting skills....");
			getSkills(toString);

			System.out.println("getting tests.....");
			getTests(toString);
			System.out.println("============PRINTING ALL Crawled TESTS========");
			for (Test t : allTests) {
				System.out.println("" + t.toString());
				Test test = new Test();
				test.setDateTaken(t.getDateTaken());
				test.setDuration(t.getDuration());
				test.setName(t.getName());
				test.setPassed(t.isPassed());
				test.setUsername(username);
				test.setId(t.getId());
				test.setPercentile(t.getPercentile());
				test.setRank(t.getRank());
				test.setProvider(t.getProvider());
				test.setScore(t.getScore());
				// creating test in db
				ResponseEntity<Test> reReadTest = restTemplate.getForEntity(uriReadTest+"/"+username+"/"+test.getName()+"/", Test.class);
				if(reReadTest.getBody()!=null){
					System.out.println("There's a record for this test! Need to update");
					test.setTestId(reReadTest.getBody().getTestId());
					restTemplate.put(uriUpdateTest,test);
				}else{
					System.out.println("No record test..");
					System.out.println("Creating test...");
					ResponseEntity<Test> reCreateTest = restTemplate.postForEntity(uriCreateTest, test, Test.class);
					if (reCreateTest.getStatusCode().equals(HttpStatus.OK)) {
						System.out.println(">>>Success create test!");
					} else {
						System.err.println("!!!Failed to create test!..");
					}
				}
			}

			System.out.println("getting certificates...");
			getCertificates(toString);
			System.out.println("============PRINTING ALL CERTIFICATES========");
			if (!allCerts.isEmpty())
				for (Certificate cert : allCerts) {
					System.out.println("" + cert.toString());
				}
			else {
				System.out.println("no certificates retrieved");
			}

			System.out.println("getting profile....");
			getProfile(toString);
			System.out.println("============PRINTING BRIEF PROFILE========");
			if (profile != null) {
				System.out.println(profile.toString());
				System.out.println(">>>Profile retrieved! Update or import profile..");
				ResponseEntity<JobSeeker> readJs = restTemplate.getForEntity(uriReadProfile + "/" + username + "/",
						JobSeeker.class);
				if (readJs.getBody() != null) {
					// update profile
					JobSeeker js = readJs.getBody();
					if(!profile.getTitle().isEmpty())
						js.setProfessionalTitle(profile.getTitle());
					if(!profile.getDescription().isEmpty())
						js.setDescription(profile.getDescription());
					
					restTemplate.put(uriUpdateProfile, js, JobSeeker.class);

				} else {
					System.out.println("printing profile before creating..");
					System.out.println(profile.toString());
					ResponseEntity<JobSeeker> readJsCreate = restTemplate
							.postForEntity(uriCreateProfile + "/" + username + "/", profile, JobSeeker.class);
					if (readJsCreate.getStatusCode().equals(HttpStatus.OK)) {
						System.out.println(">>>Success create profile!");
					}
				}
			} else {
				System.out.println("no profile retrieved");
				System.err.println("ERROR! Crawling PROFILE!");
			}

			// start storing profile documents to database
			System.out.println("Ready for storing!");
		} catch (Exception e) {
			System.err.println("ERROR! Connection LOST! IP BANNED!");
			System.out.println("Catch in UpworkRest getSkills_-----" + e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(skills, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUpworkTests", method = RequestMethod.POST)
	public ResponseEntity<List<Test>> getUpworkTests(@RequestBody String u_publicUrl) {
		allTests = new LinkedList<>();
		System.out.println("Public URL is: " + u_publicUrl.trim());
		try {
			System.out.println("Establishing connection");
			Document doc = Jsoup.connect(u_publicUrl.trim())
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.get();
			System.out.println("Document connected");
			String toString = doc.toString();
			System.out.println("getting tests.....");
			getTests(toString);
			// System.out.println("============PRINTING ALL Crawled
			// TESTS========");
		} catch (Exception e) {
			System.out.println("Catch in UpworkRest getTests-----" + e.getMessage());
			e.printStackTrace();
		}
		for (Test c : allTests) {
			System.out.println("test == " + c.toString());
		}
		return new ResponseEntity<>(allTests, HttpStatus.OK);
	}

	@RequestMapping(value = "/getProfile", method = RequestMethod.POST)
	public ResponseEntity<Profile> getUpworkProfile(@RequestBody String u_publicUrl) {
		profile = new Profile();
		System.out.println("Public URL is: " + u_publicUrl);
		try {
			System.out.println("Establishing connections");
			Document doc = Jsoup.connect(u_publicUrl.trim())
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.get();
			System.out.println("Document connected");
			String toString = doc.toString();
			System.out.println("getting profile.....");
			getProfile(toString);
		} catch (Exception e) {
			System.out.println("Catch in UpworkRest getProfile----" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Profile toString = " + profile.toString());
		return new ResponseEntity<>(profile, HttpStatus.OK);

	}

	private void getSkills(String content) {
		System.out.println("content length = " + content.length());
		Pattern p = Pattern.compile("\"prettyName\":\"[\\w\\s]*\"");
		Matcher m = p.matcher(content);
		while (m.find()) {
			System.out.println("PRINTING CLEAN CONTENT");
			System.out.println("" + m.group(0));
			String clean = cleanString(m.group(0));
			if (!isFound(clean)) {
				skills.add(clean);
				System.out.println("added skills to skill List : " + clean);
			}
		}
	}

	private String cleanString(String dataRetrieved) {
		return "" + dataRetrieved.substring(14, dataRetrieved.length() - 1);
	}

	private boolean isFound(String skillToMatch) {
		for (String s : skills) {
			if (s.equalsIgnoreCase(skillToMatch)) {
				return true;
			}
		}
		return false;
	}

	// getting allTests
	LinkedList<Test> allTests = new LinkedList<>();

	private void getTests(String content) {
		Pattern p = Pattern.compile(
				"\"tests\":\\[\\{\"name\":\"[\\w-.\\s]*\",\"score\":\\d*,\"duration\":\\d*,\"isPassed\":\\w*,\"rank\":\\d*,\"percentile\":\\d*,\"id\":\\d*,\"dateTaken\":\"[\\d-:.\\w]*\",\"provider\":\"[\\w\\s]*\",\"visibility\":\\d\\}[,\\{\":\\w\\s-.\\}]*\\]");
		Matcher m = p.matcher(content);
		while (m.find()) {
			System.out.println("PRINTING Match CONTENT tests");
			System.out.println("" + m.group(0));
			cleanTests(m.group(0));
		}
	}

	private boolean isFoundInTestsList(Test t) {
		for (Test test : allTests) {
			if (test.getName().equals(t.getName()) && test.getScore() == t.getScore()) {
				return true;
			}
		}
		return false;
	}

	private void cleanTests(String dataRetrieved) {
		Test t = null;
		String[] split = dataRetrieved.split(",");
		System.out.println("printing splits tests");
		for (String s : split) {
			System.out.println("" + s);
			if (s.contains("name\":\"")) {
				t = new Test();
				String name = s.substring(s.lastIndexOf("name\":\"") + "name\":\"".length());
				name = name.substring(0, name.length() - 1);
				System.out.println("name = " + name);
				t.setName(name);
			} else if (s.contains("\"score\"")) {
				String score = s.substring(s.lastIndexOf("\"score\"") + "\"score\"".length() + 1);
				System.out.println("score = " + score);
				t.setScore(Integer.parseInt(score));
			} else if (s.contains("\"duration\"")) {
				String duration = s.substring(s.lastIndexOf("\"duration\"") + "\"duration\"".length() + 1);
				System.out.println("duration = " + duration);
				t.setDuration(Integer.parseInt(duration));
			} else if (s.contains("\"isPassed\"")) {
				String isPassed = s.substring(s.lastIndexOf("\"isPassed\"") + "\"isPassed\"".length() + 1);
				System.out.println("isPassed = " + isPassed);
				t.setPassed(Boolean.getBoolean(isPassed));
			} else if (s.contains("\"rank\"")) {
				String rank = s.substring(s.lastIndexOf("\"rank\"") + "\"rank\"".length() + 1);
				System.out.println("rank = " + rank);
				t.setRank(Integer.parseInt(rank));
			} else if (s.contains("\"percentile\"")) {
				String percentile = s.substring(s.lastIndexOf("\"percentile\"") + "\"percentile\"".length() + 1);
				System.out.println("percentile = " + percentile);
				t.setPercentile(Integer.parseInt(percentile));
			} else if (s.contains("\"id\"")) {
				String id = s.substring(s.lastIndexOf("\"id\"") + "\"id\"".length() + 1);
				System.out.println("id = " + id);
				t.setId(Integer.parseInt(id));
			} else if (s.contains("\"dateTaken\"")) {
				String dateTaken = s.substring(s.lastIndexOf("\"dateTaken\"") + "\"dateTaken\"".length() + 1);
				System.out.println("dateTaken = " + dateTaken);
				t.setDateTaken(dateTaken);
			} else if (s.contains("\"provider\"")) {
				String provider = s.substring(s.lastIndexOf("\"provider\"") + "\"provider\"".length() + 1);
				System.out.println("provider = " + provider);
				t.setProvider(provider);

				if (!isFoundInTestsList(t)) {
					allTests.add(t);
					System.out.println("added test to tests list");
				}
			}
		}
	}

	// getting allCertificates
	LinkedList<Certificate> allCerts;

	private void getCertificates(String content) {
		// System.out.println("toString print = " + content);
		allCerts = new LinkedList<>();
		// Pattern p =
		// Pattern.compile("\"certificate\":\\{\"uid\":\"\\d*\",\"personUid\":\"\\d*\",\"name\":\"[\\w\\s]*\",\"description\":\"[\\w\\s-\\,.;]*\"");
		// Pattern p =
		// Pattern.compile("\"certificate\":\\{\"uid\":\"\\d*\",\"personUid\":\"\\d*\",\"name\":\"[\\w\\s]*\",\"description\":\"[\\w\\s-\\,.;]*\"");
		// Pattern p = Pattern.compile("\"certificate\":\\{\"uid\":\"\\d*\"");
		// Matcher m = p.matcher(content);

		Pattern p = Pattern.compile(
				"\"certificates\":\\[\\{\"uid\":\"\\d*\",\"personUid\":\"\\d*\",\"certificate\":\\{\"uid\":\"\\d*\""
						+ ",\"personUid\":\"\\d*\",\"name\":\"[\\w\\s]*\",\"description\":\"[\\w\\s-\\,.;]*\",\"verificationGuideline\":[\"][\\w\\s]*[\"],\"logoUrl\":[\"][\\w\\s]*[\"],"
						+ "\"active\":\\w*,\"extraDataRequired\":\\w*,\"verified\":\\w*,\"provider\":\"[\\w\\s-.]*\",\"creationTs\":\"[\\d-:.\\w]*\",\"lastUpdateTs\":\"[\\d-:.\\w]*\",\"link\":\\w*,\"legacyRid\":\\d,\"certificateSkills\":\\w*\\},"
						+ "\"dateEarned\":\"[\\d-]*\",\"submissionCode\":\\w*,\"notes\":\\w*,\"score\":\\w*,\"active\":\\w*,\"verified\":\\w*,\"creationTs\":\"[\\d-:.\\w]*\",\"lastUpdateTs\":\"[\\d-:.\\w]*\",\"url\":\\w*,\"externalId\":\\w*,"
						+ "\"legacyRid\":\\d\\}[,\\{\":\\w\\s-.\\}]*\\]");

		Matcher m = p.matcher(content);
		while (m.find()) {
			System.out.println("Certificate found!");
			System.out.println("PRINTING Match certificate");
			System.out.println("" + m.group(0));
			cleanCertificates(m.group(0));
		}
	}

	private boolean isFoundInCertLists(Certificate cert) {
		for (Certificate cer : allCerts) {
			if (cer.getName().equals(cert.getName())) {
				return true;
			}
		}
		return false;
	}

	private void cleanCertificates(String dataRetrieved) {
		Certificate cert = null;
		String[] split = dataRetrieved.split(",");
		System.out.println("PRINTING certificate splits");
		for (String s : split) {
			System.out.println("" + s);
			if (s.contains("\"uid\"")) {
				cert = new Certificate();
				String uid = s.substring(s.lastIndexOf("\"uid\"") + "\"uid\"".length() + 1);
				System.out.println("uid = " + uid);
				cert.setUid(Integer.parseInt(uid));
			} else if (s.contains("\"personUid\"")) {
				String personUid = s.substring(s.lastIndexOf("\"personUid\""), "\"personUid\"".length() + 1);
				System.out.println("personUid = " + personUid);
			} else if (s.contains("\"name\"")) {
				String name = s.substring(s.lastIndexOf("\"name\""), "\"name\"".length() + 1);
				System.out.println("name = " + name);
				cert.setName(name);
			} else if (s.contains("\"description\"")) {
				String description = s.substring(s.lastIndexOf("\"description\""), "\"description\"".length() + 1);
				System.out.println("description = " + description);
				cert.setDescription(description);
				if (!isFoundInCertLists(cert)) {
					allCerts.add(cert);
					System.out.println("added certficate to certficate list");
				}
			}
		}

	}

	// getting brief profile
	Profile profile;

	private void getProfile(String content) {
		Pattern p = Pattern.compile(
				"\"profile\":\\{\"name\":\"[\\w\\s.,]*\",\"shortName\":\"[\\w\\s.]*\",\"title\":\"[\\w\\s\\\\/.,:;-]*\",\"description\":\"[^\"]*\"");
		Matcher m = p.matcher(content);
		while (m.find()) {
			System.out.println("PRINTING Match Profile");
			System.out.println("" + m.group(0));
			profile = cleanProfiles(m.group(0));
			System.out.println("Added profile!");
		}
	}

	private Profile cleanProfiles(String dataRetrieved) {
		Profile p = new Profile();
		String[] split = dataRetrieved.split("\",\"");
		System.out.println("PRINTING certificate splits");
		for (String s : split) {
			System.out.println("" + s);
			if (s.contains("\"name\":\"")) {
				String name = s.substring(s.lastIndexOf("\"name\":\"") + "\"name\":\"".length());
				//name = name.substring(0, name.length() - 1);
				System.out.println("name = " + name);
				p.setName(name);
			} else if (s.contains("shortName\":\"")) {
				String shortName = s.substring(s.lastIndexOf("shortName\":\"") + "shortName\":\"".length());
				//shortName = shortName.substring(0, shortName.length() - 1);
				System.out.println("shortName = " + shortName);
				p.setShortName(shortName);
			} else if (s.contains("title\":\"")) {
				String title = s.substring(s.lastIndexOf("title\":\"") + "title\":\"".length());
				//title = title.substring(0, title.length() - 1);
				System.out.println("title = " + title);
				p.setTitle(title);
			} else if (s.contains("description\":\"")) {
				String description = s.substring(s.lastIndexOf("description\":\"") + "description\":\"".length());
				description = description.substring(0, description.length() - 1);
				System.out.println("description = " + description);
			
				p.setDescription(description);
			}
		}
		return p;
	}

	

}
