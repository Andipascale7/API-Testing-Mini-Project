package com.sparta.getallproducts.restassured.pojos2;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Usertype{

	@JsonProperty("usertype")
	private String usertype;

	public String getUsertype(){
		return usertype;
	}
}