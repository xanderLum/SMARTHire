package com.smarthire.main.utils;

import java.io.IOException;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class FacebookAccessTokenVerify {
	
	public int verifyFBToken(String accessToken){
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://graph.facebook.com/me?access_token=CAANQmN8BRmMBAHr3QUWlqsJYawsuijxvSrbILI7LHt6QiD3zBAyoUZB4vnoVRmlGlC9e21bAYPsbHVIXGNVjDeqjl3rR3C9SwZA7LeLZAUa5foI5QJ4uyDJ3H9hgVzsxcZCraSh1lzHS1eUNrdY46RmOkfBEisGQM4TdjZA2O4XXhwNPnp5mI")
		  .get()
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "a8f60cdf-b04e-190e-c885-8e0afb7677c7")
		  .build();

		try {
			Response response = client.newCall(request).execute();
			int respStatus = response.code();
			if(respStatus==200){
				return 1;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
