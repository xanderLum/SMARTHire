package com.smarthire.main.controller.api.facebook;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthire.main.dto.fb.Data;
import com.smarthire.main.dto.fb.FbUserData;
import com.smarthire.main.dto.fb.Posts;
import com.smarthire.main.models.SocialMediaAccess;
import com.smarthire.main.models.SocialMediaData;
import com.smarthire.main.utils.DateConvert;
import com.smarthire.thaliaNew.Process.DataCleaner;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@RestController
@RequestMapping(value = "/fbREST")
public class FacebookREST {
	
	DateConvert dc = new DateConvert();

	private final static String client_id = "933042436785763";
	private final static String client_secret = "47f628b090f741aa146ae0aac0f17371";

	@RequestMapping(value = "/extendAccessToken", method = RequestMethod.POST)
	public ResponseEntity<String> extendAccess(@RequestBody String accessToken) {
		System.out.println("ExtendAccessToken START: " + accessToken);
		String accessTokenLong ="";
				// START exchanging code for access token
				OkHttpClient client = new OkHttpClient();
				Request requestAT = new Request.Builder().url(
						"https://graph.facebook.com/oauth/access_token?format=json&grant_type=fb_exchange_token" + "&client_id="
								+ client_id + "&client_secret=" + client_secret + "&fb_exchange_token=" + accessToken)
						.get().addHeader("cache-control", "no-cache").build();
				Response responseAT;
				try {
					responseAT = client.newCall(requestAT).execute();
					String respBodyAT = responseAT.body().string();
					System.out.println("-->>respBodyAT: " + respBodyAT);
					String[] allReturned = respBodyAT.split("&");
					
					String accessTokenLongWithVar = allReturned[0];
					String expireWithVar = allReturned[1];
					
					String[] accessTokenSeparate = accessTokenLongWithVar.split("=");
					accessTokenLong = accessTokenSeparate[1];
					
					String[] expireSeparate = expireWithVar.split("=");
					String expire = expireSeparate[1];
					
				} catch (Exception e) {
					System.out.println("Error Here create: " + e);
				}
				// END exchanging code for access token
		//START Checking if there is accesstoken in database
//		if(rSMAcheck.getCode() < 0){//HERE EDIT
//			//no access token in database
//			System.out.println("No access Token in DataBase. Going SocialMediaAccessService. create");
//			rSMAcheck = socialMediaAccessService.create(s);
//			System.out.println("Here saved social media access data: ID is:" + rSMAcheck.getEntity().getUser_id());
//		}
//		else{
//			//there is access token in database. just need to update
//			System.out.println("There is Token in DataBase. Going SocialMediaAccessService. update");
//			rSMAcheck = socialMediaAccessService.update(s);
//			System.out.println("Here update social media access data: ID is:" + rSMAcheck.getEntity().getUser_id());
//		}
		//END Checking if there is accesstoken in database
		return new ResponseEntity<String>(accessTokenLong, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getFbData", method = RequestMethod.POST)
	public ResponseEntity<List<SocialMediaData>> getFbData(@RequestBody SocialMediaAccess s) {
		System.out.println("accessToken: " +s.getToken_data());
		
		List<SocialMediaData> smaL = new ArrayList<>();
		SocialMediaData smd;
		
		//START get fb posts
		OkHttpClient client = new OkHttpClient();
		Request requestPosts = new Request.Builder()
				.url("https://graph.facebook.com/me?" + "fields=posts" + "&access_token=" + s.getToken_data()).get()
				.addHeader("cache-control", "no-cache").build();
		try {
			Response responsePosts = client.newCall(requestPosts).execute();
			String respBodyPosts = responsePosts.body().string();
			ObjectMapper mapperInfo = new ObjectMapper();
			String nextPage = "nextPage";
			
			FbUserData fbud = mapperInfo.readValue(respBodyPosts, FbUserData.class);
			Posts post = fbud.getPosts();
			nextPage = post.getPaging().getNext();
			
			List<Data> fbd = post.getData();
//			1420070400
//			int dayLimit = 1325376000; //just a limit. this is january 1, 2012.
		//	int dayLimit = 1420070400; //just a limit. this is january 1, 2015.
			int dayLimit = 1800000000; //latest
			int currentDayPost = 1325376001; //just to initialize day is greater than january 1, 2012.
			String[] getCurrentDayPostArr;
			String getCurrentDayPost;
			
			int count=0;
			while(dayLimit>currentDayPost){
				DataCleaner dcleaner = new DataCleaner();
				System.out.println("daylimit = "+dayLimit+" < currentDayPost = "+currentDayPost);
				if(!fbd.isEmpty()){
					for(Data f : fbd){
						System.out.println("------------------------- " + count);
						System.out.println("Message :" + f.getMessage());
						System.out.println("Story : " + f.getStory());
						System.out.println("Time :" + f.getCreated_time());
						System.out.println("Id: " + f.getId());
						
						//START storage of social media data
						try{
							if(f.getMessage()!=null && !f.getMessage().equals("")){
								System.out.println("MESSAGE added to List: " + f.getMessage());
								String clean = dcleaner.cleanText(f.getMessage());
								System.out.println("--------------CLEAN------------- = "+clean);
								smd = new SocialMediaData(f.getId(),s.getUsername(),"facebook", f.getMessage(), clean, "facebook_post", dc.isoToEpoch(f.getCreated_time()));
								smaL.add(smd);
								System.out.println("DONE MESSAGE added to List: " + f.getMessage());
							}
						}
						catch(Exception e){
							System.out.println("Error in saving Social Media Data");
						}
						
						//END storage of social media data
						count++;
					}
					
					//START get data from next pages. getting earlier posts
					requestPosts = new Request.Builder()
							.url(nextPage).get()
							.addHeader("cache-control", "no-cache").build();
					responsePosts = client.newCall(requestPosts).execute();
					respBodyPosts = responsePosts.body().string();
					post = mapperInfo.readValue(respBodyPosts, Posts.class);
					fbd = post.getData();
				try{
						nextPage = post.getPaging().getNext();
						System.out.println("Next Page: " + nextPage);
					}catch(NullPointerException ex){
						System.out.println("No more pages to read.. Reach end of Page");
						break;
					}
							//START getting the day in unix epoch
							getCurrentDayPostArr = nextPage.split("until="); //getting text after the until
							getCurrentDayPost = getCurrentDayPostArr[1];
							getCurrentDayPostArr = getCurrentDayPost.split("&"); //getting text before _paging_token
							getCurrentDayPost = getCurrentDayPostArr[0];
							//END getting the day in unix epoch
						
						//END get data from next pages. getting earlier posts
					
					System.out.println("Current day is: " + getCurrentDayPost);
					currentDayPost = Integer.parseInt(getCurrentDayPost);
					//dayLimit = Integer.parseInt(getCurrentDayPost);
				}
				else{
					System.out.println("page is already empty. NO DATA");
					currentDayPost =0;
				}
			}
			System.out.println("Reach 2012");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//END get fb posts
		
		System.out.println("\n------------PRINT SMD LIST-----------");
		for(SocialMediaData socialMD : smaL){
			System.out.println("data id = "+socialMD.getData_id());
			System.out.println("username = "+socialMD.getUsername());
			System.out.println("social media = "+socialMD.getSocial_media());
			System.out.println("data = "+socialMD.getData());
			System.out.println("usable_data = "+socialMD.getUsable_data());
			System.out.println("data type = "+socialMD.getData_type());
			System.out.println("data_posted = "+socialMD.getDate_posted());
		}
		
		return new ResponseEntity<>(smaL,HttpStatus.OK);
	}
}
