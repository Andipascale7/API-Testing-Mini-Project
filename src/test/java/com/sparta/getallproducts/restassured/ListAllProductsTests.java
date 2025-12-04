package com.sparta.getallproducts.restassured;

import com.sparta.getallproducts.restassured.pojos.ListAllProductsResponse;
import com.sparta.getallproducts.restassured.pojos2.ListAllProductsResponse2;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ListAllProductsTests {
    private static Response response;
    private static ListAllProductsResponse listAllProductsResponse;
    private static ListAllProductsResponse2 listAllProductsResponse2;

    @BeforeAll
    static void beforeAll() {
        response = RestAssured
                .given()
                .baseUri("https://automationexercise.com")
                .headers("Accept", "application/json")
                .expect().defaultParser(Parser.JSON)
                .when()
                .get("/api/productsList")
                .then()
                .log().all(false)
                .extract().response();
        listAllProductsResponse = response.as(ListAllProductsResponse.class);
        listAllProductsResponse2 = response.as(ListAllProductsResponse2.class);
    }

    @Test
    void getAllProducts() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
        MatcherAssert.assertThat(
                listAllProductsResponse2.getProducts()[1],
                Matchers.is("")// the getResultMethod is from the singlePostcodeResponse class then the getPostcode is from Results class
        );
    }
}
