package com.sparta.createaccount.restassured.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Createaccount{

	@JsonProperty("message")
	private String message;

	@JsonProperty("responseCode")
	private int responseCode;

	public String getMessage(){
		return message;
	}

	public int getResponseCode(){
		return responseCode;
	}
}