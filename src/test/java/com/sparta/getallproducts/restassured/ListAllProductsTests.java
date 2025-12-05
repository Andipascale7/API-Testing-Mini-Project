package com.sparta.getallproducts.restassured;

import com.sparta.getallproducts.restassured.pojos.ListAllProductsResponse;
import com.sparta.getallproducts.restassured.pojos.ProductsItem;
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
    void getstatucode200() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    void getAllProducts() {
        MatcherAssert.assertThat(
                response.jsonPath().getList("products", ProductsItem.class).size(),
                Matchers.is(34)
        );
    }

    @Test
    void getSecondProductPrice() {
        System.out.println(listAllProductsResponse.getProducts().get(1).getPrice());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(1).getPrice(), Matchers.is("Rs. 400"));
    }
    @Test
    void getSecondProductName() {
        System.out.println(listAllProductsResponse.getProducts().get(1).getName());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(1).getName(), Matchers.is("Men Tshirt"));
    }
    @Test
    void getFirstProductId() {
        System.out.println(listAllProductsResponse.getProducts().get(0).getId());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(0).getId(), Matchers.is(1));
    }
    @Test
    void getSecondProductId() {
        System.out.println(listAllProductsResponse.getProducts().get(1).getId());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(1).getId(), Matchers.is(2));
    }
    @Test
    void getSecondProductCategory() {
        System.out.println(listAllProductsResponse.getProducts().get(1).getCategory().getCategory());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(1).getCategory().getCategory(), Matchers.is("Tshirts"));
    }
    @Test
    void getSecondProductUserType() {
        System.out.println(listAllProductsResponse.getProducts().get(1).getCategory().getUsertype().getUsertype());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(1).getCategory().getUsertype().getUsertype(), Matchers.is("Men"));
    }
    @Test
    void getSecondProductBrand() {
        System.out.println(listAllProductsResponse.getProducts().get(1).getBrand());
        MatcherAssert.assertThat(listAllProductsResponse.getProducts().get(1).getBrand(), Matchers.is("H&M"));
    }
}

