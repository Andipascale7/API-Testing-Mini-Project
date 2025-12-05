package com.sparta.getuserdetail.restassured.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User{

	@JsonProperty("country")
	private String country;

	@JsonProperty("address2")
	private String address2;

	@JsonProperty("city")
	private String city;

	@JsonProperty("birth_month")
	private String birthMonth;

	@JsonProperty("address1")
	private String address1;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("title")
	private String title;

	@JsonProperty("birth_year")
	private String birthYear;

	@JsonProperty("birth_day")
	private String birthDay;

	@JsonProperty("zipcode")
	private String zipcode;

	@JsonProperty("name")
	private String name;

	@JsonProperty("company")
	private String company;

	@JsonProperty("id")
	private int id;

	@JsonProperty("state")
	private String state;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("email")
	private String email;

	public String getCountry(){
		return country;
	}

	public String getAddress2(){
		return address2;
	}

	public String getCity(){
		return city;
	}

	public String getBirthMonth(){
		return birthMonth;
	}

	public String getAddress1(){
		return address1;
	}

	public String getLastName(){
		return lastName;
	}

	public String getTitle(){
		return title;
	}

	public String getBirthYear(){
		return birthYear;
	}

	public String getBirthDay(){
		return birthDay;
	}

	public String getZipcode(){
		return zipcode;
	}

	public String getName(){
		return name;
	}

	public String getCompany(){
		return company;
	}

	public int getId(){
		return id;
	}

	public String getState(){
		return state;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getEmail(){
		return email;
	}
}