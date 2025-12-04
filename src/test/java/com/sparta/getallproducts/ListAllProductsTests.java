package com.sparta.getallproducts;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class ListAllProductsTests {
@Test
    void testStatusCode(){
    RestAssured
            .given()
            .baseUri("https://automationexercise.com")
            .when()
            .get("/api/productsList")
            .then()
            .log().all()
            .extract().response();

}
}
