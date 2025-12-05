package com.sparta.searchproduct.restassured.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usertype{

	@JsonProperty("usertype")
	private String usertype;

	public String getUsertype(){
		return usertype;
	}
}