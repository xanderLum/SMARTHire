package com.smarthire.main.controller.api.facebook;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarthire.main.dto.AccessTokenResponseFacebook;
import com.smarthire.main.dto.FacebookBasicProfile;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Controller
@RequestMapping(value = "/getSMData/facebook")
public class FacebookAPIController {

	private final static String client_id = "933042436785763";
	private final static String client_secret = "47f628b090f741aa146ae0aac0f17371";
	private final static String callback = "http://localhost:8080/SMARTHire/getSMData/facebook/auth/callback";

	// @RequestMapping(value = "/auth/callback", method = RequestMethod.GET)
	// public String facebookHome(Model m, @RequestParam(value = "code",
	// defaultValue = "null") String code,
	// @RequestParam(value = "state", defaultValue = "null") String state,
	// @RequestParam(value = "error", defaultValue = "null") String error,
	// @RequestParam(value = "error_description", defaultValue = "null") String
	// error_description) {
	//
	// String access = getAccessToken(code);
	// System.out.println("\ntoken: " + access);
	// try {
	// //START Get user basic data
	//
	// String graph = null;
	// String g =
	// "https://graph.facebook.com/me?fields=id,name,first_name,last_name," +
	// "birthday,gender,email&"
	// + access;
	//
	// URL u = new URL(g);
	// URLConnection c = u.openConnection();
	// BufferedReader in = new BufferedReader(new
	// InputStreamReader(c.getInputStream()));
	// String inputLine;
	// StringBuffer b = new StringBuffer();
	// while ((inputLine = in.readLine()) != null)
	// b.append(inputLine + "\n");
	// in.close();
	// graph = b.toString();
	// System.out.println("Basic Data: " + graph);
	// //END Get user basic data
	//
	// //START Get user post
	// String graphP = null;
	// String gP = "https://graph.facebook.com/me?fields=posts&"+ access;
	//// String gP = "https://graph.facebook.com/me?fields=posts&"+ genAccess;
	// URL uP = new URL(gP);
	// URLConnection cP = uP.openConnection();
	// BufferedReader inP = new BufferedReader(new InputStreamReader(
	// cP.getInputStream()));
	// String inputLineP;
	// StringBuffer bP = new StringBuffer();
	// while ((inputLineP = inP.readLine()) != null)
	// bP.append(inputLineP + "\n");
	// inP.close();
	// graphP = bP.toString();
	// System.out.println("POSTS: " + graphP);
	// m.addAttribute("userposts",graphP );
	// //END Get user post
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new RuntimeException("ERROR in getting FB graph data. " + e);
	// }
	//
	// return "testFacebookHome";
	// }
	//
	// public String getAccessToken(String code) {
	// String accessToken = "";
	// if ("".equals(accessToken)) {
	// URL fbGraphURL;
	// try {
	// fbGraphURL = new URL(getFBGraphUrl(code)); // here 1
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// throw new RuntimeException("Invalid code received " + e);
	// }
	// URLConnection fbConnection;
	// StringBuffer b = null;
	// try {
	// fbConnection = fbGraphURL.openConnection();
	// BufferedReader in;
	// in = new BufferedReader(new
	// InputStreamReader(fbConnection.getInputStream()));
	// String inputLine;
	// b = new StringBuffer();
	// while ((inputLine = in.readLine()) != null)
	// b.append(inputLine + "\n");
	// in.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// throw new RuntimeException("Unable to connect with Facebook " + e);
	// }
	// accessToken = b.toString();
	// if (accessToken.startsWith("{")) {
	// throw new RuntimeException("ERROR: Access Token Invalid: " +
	// accessToken);
	// }
	// }
	// return accessToken;
	// }
	//
	// public String getFBGraphUrl(String code) {
	// String fbGraphUrl = "";
	//
	// fbGraphUrl = "https://graph.facebook.com/oauth/access_token?" +
	// "client_id=" + client_id + "&redirect_uri="
	// + callback + "&client_secret=" + client_secret + "&code=" + code;
	//
	// return fbGraphUrl;
	// }

	@RequestMapping(value = "/auth/callback", method = RequestMethod.GET)
	public String facebookHome(Model m, @RequestParam(value = "code", defaultValue = "null") String code,
			@RequestParam(value = "state", defaultValue = "null") String state,
			@RequestParam(value = "error", defaultValue = "null") String error,
			@RequestParam(value = "error_description", defaultValue = "null") String error_description) {
		System.out.println("Here");

		// String access_token =
		// START exchanging code for access token
		OkHttpClient client = new OkHttpClient();
		Request requestAT = new Request.Builder().url("https://graph.facebook.com/v2.5/oauth/access_token?"
				// .url("https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + client_id + "&redirect_uri=" + callback + "&client_secret=" + client_secret + "&code="
				+ code).get().addHeader("cache-control", "no-cache").build();

		Response responseAT;
		try {
			responseAT = client.newCall(requestAT).execute();

			String respBodyAT = responseAT.body().string();
			System.out.println("respBodyAT:" + respBodyAT);
			ObjectMapper mapperInfo = new ObjectMapper();

			AccessTokenResponseFacebook atrf = mapperInfo.readValue(respBodyAT, AccessTokenResponseFacebook.class);
			String access_token = atrf.getAccess_token();
			System.out.println("Access Token -- " + access_token);
			System.out.println("Token Type -- " + atrf.getToken_type());
			System.out.println("Expires In -- " + atrf.getExpires_in());
			// END exchanging code for access token

			System.out.println("Here1");
			// START get facebook basic data.
			Request requestInfo = new Request.Builder().url("https://graph.facebook.com/me?"
					+ "fields=id,name,first_name,last_name,gender" + "&access_token=" + access_token).get()
					.addHeader("cache-control", "no-cache").build();

			Response responseInfo = client.newCall(requestInfo).execute();
			String respBodyInfo = responseInfo.body().string();

			FacebookBasicProfile fbp = mapperInfo.readValue(respBodyInfo, FacebookBasicProfile.class);
			System.out.println("FB Name: " + fbp.getName());
			System.out.println("FB ID: " + fbp.getId());
			// END get facebook basic data.
			System.out.println("Here2");
			
			// START get facebook posts.
			Request requestPosts = new Request.Builder()
					.url("https://graph.facebook.com/me?" + "fields=posts" + "&access_token=" + access_token).get()
					.addHeader("cache-control", "no-cache").build();

			System.out.println("access_token: " + access_token);
			Response responsePosts = client.newCall(requestPosts).execute();
			String respBodyPosts = responsePosts.body().string();

			System.out.println("POSTS : " + respBodyPosts);
			// END get facebook posts.
			m.addAttribute("userposts", respBodyPosts);

		} catch (IOException e) {
			System.out.println("Exception : " + e);
			e.printStackTrace();
		}
		System.out.println("Here3");
		return "testFacebookHome";
	}

}
