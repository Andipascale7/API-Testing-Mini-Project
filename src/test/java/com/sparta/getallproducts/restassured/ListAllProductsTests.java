package com.sparta.getallproducts.restassured;

import com.sparta.getallproducts.restassured.pojos.ListAllProductsResponse;
import com.sparta.getallproducts.utils.Config;
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
    private static final String BASE_URI = Config.getBaseUri();

    @BeforeAll
    static void beforeAll() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .headers("Accept", "application/json")
                .expect().defaultParser(Parser.JSON)
                .when()
                .get("/api/productsList")
                .then()
                .log().all(false)
                .extract().response();
        listAllProductsResponse = response.as(ListAllProductsResponse.class);
    }

    @Test
    void getAllProducts() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(201));
        System.out.println(listAllProductsResponse.getProducts().get(1).getPrice());
    }
}
