package com.sparta.searchproduct.restassured.pojos;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchProductResponse{

    @JsonProperty("message")
    private String message;

	@JsonProperty("responseCode")
	private int responseCode;

	@JsonProperty("products")
	private List<ProductsItem> products;

    public String getMessage(){
        return message;
    }

	public int getResponseCode(){
		return responseCode;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}