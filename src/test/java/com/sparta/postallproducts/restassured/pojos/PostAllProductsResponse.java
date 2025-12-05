package com.sparta.postallproducts.restassured.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostAllProductsResponse{

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