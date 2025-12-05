package com.sparta.createaccount.restassured;

import com.sparta.createaccount.restassured.pojos.Createaccount;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateAccountTests {
    private static Response response;
    private static Createaccount createaccount;

    // The requestBody string is no longer used for the body, but keeping it
    // here as a reference to the data being sent via formParam
//    private static String requestBody = "{\"name\": \"uu\", \"email\": \"uzo3011@gmail.com\", \"password\": \"test12346\"," +
//            "\"firstname\": \"uzo\", \"lastname\": \"u\", \"address1\": \"ws\", \"country\": \"k\", \"state\": \"dc\", \"city\": \"fv\"," +
//            " \"zipcode\": \"az\", \"mobile_number\": \"qq\"}";

    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri("https://automationexercise.com/api")
                // 🚀 Sending data as form parameters as required by the API
                .formParam("name", "uu")
                .formParam("email", "uzo3011@gmail.com")
                .formParam("password", "test1234")
                .formParam("firstname", "uzo")
                .formParam("lastname", "u")
                .formParam("address1", "ws")
                .formParam("country", "k")
                .formParam("state", "dc")
                .formParam("city", "fv")
                .formParam("zipcode", "az")
                .formParam("mobile_number", "qq")
                // No need for explicit Content-Type header; RestAssured handles it.
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/createAccount")
                .then()
                .log().all(false)
                .extract().response();
        createaccount = response.as(Createaccount.class);
    }

    @Test
    void postCreateAccount(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

}