package com.sparta.getuserdetail.restassured;

import com.sparta.getuserdetail.restassured.pojos.Getuserdetail;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GetUserDetailTests {
    private static Response response;
    private static Getuserdetail getuserdetail;

    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri("https://automationexercise.com/api")
                .expect().defaultParser(Parser.JSON)
                .when()
                .get("/getUserDetailByEmail?email=uzo3011@gmail.com")
                .then()
                .log().all(false)
                .extract().response();
        getuserdetail = response.as(Getuserdetail.class);
    }

    @Test
    void getDetail(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("correct first name")
    void testCorrectFirstName(){
        MatcherAssert.assertThat(
                response.jsonPath().getString("user.first_name"),
                Matchers.is("uzo"));
    }
}
