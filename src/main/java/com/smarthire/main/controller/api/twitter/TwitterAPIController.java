package com.smarthire.main.controller.api.twitter;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Controller
@RequestMapping(value = "/getSMData/twitter")
public class TwitterAPIController {

	private final static String consumer_key = "8CzDVBQzYvyXT23OrQYVSmSqd";
	private final static String consumer_secret = "GPg1xGyX2d2aB7rr3X6PaJZ9HwAmh65IhjDCPSMu4aOfYjkMi9";
	private final static String callback = "http://localhost:8080/SMARTHire/getSMData/facebook/auth/callback";

	@RequestMapping(value = "/auth/callback", method = RequestMethod.GET)
	public void twitterHome(Model m) throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("8CzDVBQzYvyXT23OrQYVSmSqd")
				.setOAuthConsumerSecret("GPg1xGyX2d2aB7rr3X6PaJZ9HwAmh65IhjDCPSMu4aOfYjkMi9")
				.setOAuthAccessToken("4727907122-CiqmtO6gyaj46Po9MWQBPtIEpnsF73H3tsVpYIc")
				.setOAuthAccessTokenSecret("nugyYJv8z8UjZbJiyAh7o2696ND3v17iUzDeL0DUnhJMi");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		// Twitter twitter = TwitterFactory.getSingleton();
		List<Status> statuses = null;
		try {
			statuses = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Showing home timeline.");
		for (Status status : statuses) {
			System.out.println(status.getUser().getName() + ":" + status.getText());
		}
		
//		Twitter unauthenticatedTwitter = new TwitterFactory().getInstance();
		Twitter unauthenticatedTwitter = tf.getInstance();
		// First param of Paging() is the page number, second is the number per
		// page (this is capped around 200 I think.
		Paging paging = new Paging(1, 100);
		List<Status> statusess = unauthenticatedTwitter.getUserTimeline("HugotDre", paging);
		System.out.println("========HUGOTDRE===============");
		for (Status statuss : statusess) {
			System.out.println(statuss.getUser().getName() + ":" + statuss.getText());
		}
	}
}
