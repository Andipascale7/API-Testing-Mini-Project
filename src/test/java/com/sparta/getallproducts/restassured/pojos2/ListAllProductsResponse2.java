package com.sparta.getallproducts.restassured.pojos2;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListAllProductsResponse2{

	@JsonProperty("responseCode")
	private int responseCode;

	@JsonProperty("products")
	private List<ProductsItem> products;

	public int getResponseCode(){
		return responseCode;
	}

	public List<ProductsItem> getProducts(){
		return products;
	}
}