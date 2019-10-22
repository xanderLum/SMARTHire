package com.smarthire.main.controller.api.twitter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.main.models.SocialMediaAccess;
import com.smarthire.main.models.SocialMediaData;
import com.smarthire.main.utils.DateConvert;
import com.smarthire.thaliaNew.Process.DataCleaner;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@RestController
@RequestMapping(value = "/twitterREST")
public class TwitterREST {
	
	private final static String consumer_key = "8CzDVBQzYvyXT23OrQYVSmSqd";
	private final static String consumer_secret = "GPg1xGyX2d2aB7rr3X6PaJZ9HwAmh65IhjDCPSMu4aOfYjkMi9";
	private final static String access_token = "4727907122-CiqmtO6gyaj46Po9MWQBPtIEpnsF73H3tsVpYIc";
	private final static String access_token_secret = "nugyYJv8z8UjZbJiyAh7o2696ND3v17iUzDeL0DUnhJMi";
	
	DateConvert dc = new DateConvert();

	
	//public ResponseEntity<List<SocialMediaData>> getTwitterData(@RequestBody SocialMediaAccess s) {
	
	@RequestMapping(value = "/getTwitterData", method = RequestMethod.POST)
	public ResponseEntity<List<SocialMediaData>> getFbData(@RequestBody SocialMediaAccess s) throws TwitterException{
		DataCleaner dcleaner = new DataCleaner();
		List<SocialMediaData> smaL = new ArrayList<>();
		SocialMediaData smd;
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(consumer_key)
				.setOAuthConsumerSecret(consumer_secret)
				.setOAuthAccessToken(access_token)
				.setOAuthAccessTokenSecret(access_token_secret);
		TwitterFactory tf = new TwitterFactory(cb.build());
//		Twitter twitter = tf.getInstance();

//		List<Status> statuses = null;
//		try {
//			statuses = twitter.getHomeTimeline();
//		} catch (TwitterException e) {
//			System.out.println("Catch here in statusses Twitter REST");
//		}
//		System.out.println("Showing home timeline.");
//		for (Status status : statuses) {
//			System.out.println(status.getUser().getName() + ":" + status.getText());
//		}
		
		Twitter unauthenticatedTwitter = tf.getInstance();
		// First param of Paging() is the page number, second is the number per
		// page (this is capped around 200, not so sure ko diri
		Paging paging = new Paging(1, 100);
		List<Status> statusess = unauthenticatedTwitter.getUserTimeline(s.getToken_data(), paging);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS+0000");
	    Date date;
	    
		System.out.println("========STATUS of USER: "+s.getToken_data()+"===============");
		for (Status statuss : statusess) {
			//error here
			date = statuss.getCreatedAt();
		    System.out.println("Date:" + df.format(date));
		    String clean = dcleaner.cleanText(statuss.getText());
			smd = new SocialMediaData(statuss.getId()+"",s.getUsername(),"twitter", statuss.getText(), clean, "tweets", dc.isoToEpoch(df.format(date)));
			smaL.add(smd);
			System.out.println(statuss.getUser().getName() + ":" + statuss.getText());
		}
		return new ResponseEntity<>(smaL,HttpStatus.OK);
	}
	
}
