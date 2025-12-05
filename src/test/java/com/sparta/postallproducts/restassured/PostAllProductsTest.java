package com.sparta.postallproducts.restassured;

import com.sparta.getallproducts.restassured.pojos.ListAllProductsResponse;
import com.sparta.getallproducts.utils.Config;
import com.sparta.postallproducts.restassured.pojos.PostAllProductsResponse;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PostAllProductsTest {
    private static Response response;
    private static PostAllProductsResponse postAllProductsResponse;
    private static final String BASE_URI = Config.getBaseUri();

    @BeforeAll
    static void beforeAll() {
        response=RestAssured
        .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/productsList")
                .then()
                .log().all(false)
                .extract().response();
        postAllProductsResponse = response.as(PostAllProductsResponse.class);
    }

    @Test
    void getstatuscode200() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

@Test
void getresponsecode405() {
    MatcherAssert.assertThat(postAllProductsResponse.getResponseCode(), Matchers.is(405));
}
    @Test
    void getAllMessages() {
        MatcherAssert.assertThat(postAllProductsResponse.getMessage(), Matchers.is("This request method is not supported."));
    }
}
