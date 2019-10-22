package com.smarthire.main.controller.api.linkedin;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthire.main.dto.AccessTokenResponse;
import com.smarthire.main.dto.LinkedInBasicProfile;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

@Controller
@RequestMapping(value = "/getSMData/linkedIn")
public class LinkedInAPIController {

	private final static String client_id = "75ef6jpx9ot2q9";
	private final static String client_secret = "Sp5gVDyY2UPTfb9L";

	private final static String callback = "http://localhost:8080/SMARTHire/getSMData/linkedIn/auth/callback";

	@RequestMapping(value = "/")
	public String linkedInHome(Model m) {
		return "testLinkedInHome";
	}

	@RequestMapping(value = "/auth/callback", method = RequestMethod.GET)
	public String authCallBack(Model m, @RequestParam(value = "code", defaultValue = "null") String code,
			@RequestParam(value = "state", defaultValue = "null") String state,
			@RequestParam(value = "error", defaultValue = "null") String error,
			@RequestParam(value = "error_description", defaultValue = "null") String error_description) {

		System.out.println("Code is: " + code);
		System.out.println("state is: " + state);
		System.out.println("error is: " + error);
		System.out.println("error_description is: " + error_description);

		//getting the access token. exchanging authorization code for access token
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=authorization_code&code=" + code + "&redirect_uri="
				+ callback + "&client_id=" + client_id + "&client_secret=" + client_secret);
		Request request = new Request.Builder().url("https://www.linkedin.com/uas/oauth2/accessToken").post(body)
				.addHeader("cache-control", "no-cache").addHeader("content-type", "application/x-www-form-urlencoded")
				.build();

		try {
			Response response = client.newCall(request).execute();
			String respBody = response.body().string();
			System.out.println("Here -- " + respBody);

			ObjectMapper mapper = new ObjectMapper();

			AccessTokenResponse atr = mapper.readValue(respBody, AccessTokenResponse.class);
			System.out.println("Access Token: " + atr.getAccess_token());
			System.out.println("Expire: " + atr.getExpires_in());

			m.addAttribute("token", atr.getAccess_token());

			//getting Linkedin Data. Basic Profile Data only, using the access token above
			Request requestInfo = new Request.Builder()
					.url("https://api.linkedin.com/v1/people/~:"
							+ "(firstName,lastName,id,headline,industry,num-connections,siteStandardProfileRequest,publicProfileURL)"
							+ "?format=json&oauth2_access_token=" + atr.getAccess_token())
					.get().addHeader("cache-control", "no-cache").build();

			Response responseInfo = client.newCall(requestInfo).execute();
			String respBodyInfo = responseInfo.body().string();
			System.out.println("HereBody -- " + respBodyInfo);

			ObjectMapper mapperInfo = new ObjectMapper();

			LinkedInBasicProfile lbp = mapperInfo.readValue(respBodyInfo, LinkedInBasicProfile.class);
			m.addAttribute("token", atr.getAccess_token());
			m.addAttribute("firstName", lbp.getFirstName());
			m.addAttribute("lastName", lbp.getLastName());
			m.addAttribute("id", lbp.getId());
			m.addAttribute("url", lbp.getUrl());
			m.addAttribute("headline", lbp.getHeadline());
			m.addAttribute("industry", lbp.getIndustry());
			m.addAttribute("numConnections", lbp.getNumConnections());
			m.addAttribute("publicProfileURL", lbp.getPublicProfileUrl());

			String publicUrl = lbp.getPublicProfileUrl();
			System.out.println("publicUrl: " + publicUrl);

			Document doc = org.jsoup.Jsoup.connect(publicUrl).get();

			Elements skills = doc.getElementsByClass("skill");
			for (Element skill : skills) {
				String sk = skill.text();
				System.out.println("Skill:: " + sk );
			}
			
			m.addAttribute("skills", skills);
			
		} catch (IOException e) {
			System.out.println("LinkedIn Catch: " + e);
			e.printStackTrace();
		}
		System.out.println("Here 2");
		return "testLinkedInHome";
	}

}
