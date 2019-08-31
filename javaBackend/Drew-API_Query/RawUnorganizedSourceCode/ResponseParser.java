package com.resonance.api.response;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseParser {
	private Request request;
	private boolean successful;
	private String responseString;
	private final OkHttpClient client = new OkHttpClient();
	
	public ResponseParser() {}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public String parseResponse() {
		Response response = null;
		try {
			response = client.newCall(request).execute();
			responseString = response.body().string();
			successful = response.isSuccessful();
			response.close();
			
			if(successful)
				return responseString;
			else
				return null;
		} catch (IOException e) {
			System.out.println("Response Parsing Error - Message: " + response.message());
			e.printStackTrace();
			return null;
		}
	}
}
