package com.smarthire.main.controller.api.linkedin;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/linkedInRest")
public class LinkedInREST {

	private final static String client_id = "75ef6jpx9ot2q9";
	private final static String client_secret = "Sp5gVDyY2UPTfb9L";
	private final static String callback = "http://localhost:8080/SMARTHire/getSMData/linkedIn/auth/callback";

	@RequestMapping(value = "/getSkills", method = RequestMethod.POST)
	public ResponseEntity<List<String>> getSkills(@RequestBody String l_publicUrl) {
		List<String> allSkills = new ArrayList<>();
		System.out.println("Public URL is: " + l_publicUrl.trim());
		try {
			// Document doc = org.jsoup.Jsoup.connect(l_publicUrl.trim()).get();
			Document doc = Jsoup.connect(l_publicUrl.trim())
					.userAgent(
							"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
					.get();
			Elements skills = doc.getElementsByClass("skill");
			for (Element skill : skills) {
				String sk = skill.text();
				System.out.println("LinkedIn REST. List -> Skill: " + sk);
				allSkills.add(sk);
			}

			System.out.println("PRINTING LinkedIn toString web.html");
			String toString = doc.toString();
			System.out.println("" + toString);
			System.out.println("Length = " + toString.length());
		} catch (Exception e) {
			System.out.println("Catch in LinkedInRest getSkills");
		}
		return new ResponseEntity<>(allSkills, HttpStatus.OK);
	}

	@RequestMapping(value = "/getSkillsOriginal", method = RequestMethod.POST)
	public ResponseEntity<List<String>> getSkillsOriginal(@RequestBody String l_publicUrl) {
		List<String> allSkills = new ArrayList<>();
		System.out.println("Public URL is: " + l_publicUrl.trim());
		try {
			Document doc = org.jsoup.Jsoup.connect(l_publicUrl.trim()).get();
			Elements skills = doc.getElementsByClass("skill");
			for (Element skill : skills) {
				String sk = skill.text();
				System.out.println("LinkedIn REST. List -> Skill: " + sk);
				allSkills.add(sk);
			}
		} catch (Exception e) {
			System.out.println("Catch in LinkedInRest getSkills");
		}
		return new ResponseEntity<>(allSkills, HttpStatus.OK);
	}

}
