package com.sparta.getuserdetail.restassured.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Getuserdetail{

	@JsonProperty("user")
	private User user;

	@JsonProperty("responseCode")
	private int responseCode;

	public User getUser(){
		return user;
	}

	public int getResponseCode(){
		return responseCode;
	}
}