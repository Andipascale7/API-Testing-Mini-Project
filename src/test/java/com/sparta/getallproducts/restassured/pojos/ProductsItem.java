package com.sparta.getallproducts.restassured.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsItem{

	@JsonProperty("price")
	private String price;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("brand")
	private String brand;

	public String getPrice(){
		return price;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public Category getCategory(){
		return category;
	}

	public String getBrand(){
		return brand;
	}
}