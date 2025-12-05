package com.sparta.deleteaccount.restassured;

import com.sparta.deleteaccount.restassured.pojos.Deleteaccount;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DeleteAccountTests {
    private static Response response;
    private static Deleteaccount deleteaccount;

    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri("https://automationexercise.com/api")
                .formParam("email", "uzo3011@gmail.com")
                .formParam("password", "test1234")
                .expect().defaultParser(Parser.JSON)
                .when()
                .delete("/deleteAccount")
                .then()
                .log().all(false)
                .extract().response();
        deleteaccount = response.as(Deleteaccount.class);
    }

    @Test
    void deleteAccount(){
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

}
