package com.sparta.searchproduct.restassured;

import com.sparta.searchproduct.restassured.pojos.SearchProductResponse;
import com.sparta.searchproduct.utils.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchProductTests {
    private static Response response;
    private static SearchProductResponse searchProductResponse;
    private static final String BASE_URI = Config.getBaseUri();

    @Test
    @DisplayName("GET To Search Product gives 405 unsupported request method")
    void getToSearchProductGives405Error() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .expect().defaultParser(Parser.JSON)
                .when()
                .get("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(405));
        MatcherAssert.assertThat(searchProductResponse.getMessage(), Matchers.is("This request method is not supported."));
    }

    @Test
    @DisplayName("POST To Search Product with no parameter gives 400 bad request method")
    void postToSearchProductWithNoParameterGives400Error() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .contentType(ContentType.JSON)
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(400));
        MatcherAssert.assertThat(searchProductResponse.getMessage(), Matchers.is("Bad request, search_product parameter is missing in POST request."));
    }

    @Test
    @DisplayName("POST To Search Product with invalid parameter gives 400 bad request method")
    void postToSearchProductWithParameterGives200OK() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("bad_request", "x")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(400));
        MatcherAssert.assertThat(searchProductResponse.getMessage(), Matchers.is("Bad request, search_product parameter is missing in POST request."));
    }

    @Test
    @DisplayName("POST To Search Product with parameter gives 200 OK")
    void postToSearchProductWithInvalidParameterGives200OK() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("search_product", "x")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(200));
    }

    @Test
    @DisplayName("POST To Search Product with empty parameter gives 200 OK and returns results")
    void postToSearchProductWithEmptyParameterGives200OK() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("search_product", "")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(200));
        MatcherAssert.assertThat(searchProductResponse.getProducts().size(),Matchers.not(Matchers.is(0)));
    }

    @Test
    @DisplayName("POST To Search Product with valid parameter and no results gives 200 OK and returns results")
    void postToSearchProductWithValidParameterNoResultsGives200OK() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("search_product", "gvsahgsfjsfgjzasgjmk")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(200));
        MatcherAssert.assertThat(searchProductResponse.getProducts().size(),Matchers.is(0));
    }

    @Test
    @DisplayName("POST To Search Product with valid results in name gives 200 OK and returns results")
    void postToSearchProductWithValidNameMatchGives200OK() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("search_product", "polo")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(200));
        MatcherAssert.assertThat(searchProductResponse.getProducts().size(),Matchers.not(Matchers.is(0)));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getFirst().getName().toLowerCase(),Matchers.containsString("polo"));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getFirst().getCategory().getCategory().toLowerCase(),Matchers.not(Matchers.containsString("polo")));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getLast().getName().toLowerCase(),Matchers.containsString("polo"));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getLast().getCategory().getCategory().toLowerCase(),Matchers.not(Matchers.containsString("polo")));
    }

    @Test
    @DisplayName("POST To Search Product with valid results in category gives 200 OK and returns results")
    void postToSearchProductWithValidCategoryMatchGives200OK() {
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/api")
                .headers("Accept", "application/json")
                .contentType("multipart/form-data")
                .multiPart("search_product", "tshirts")
                .expect().defaultParser(Parser.JSON)
                .when()
                .post("/searchProduct")
                .then()
                .log().all(false)
                .extract().response();
        searchProductResponse = response.as(SearchProductResponse.class);
        MatcherAssert.assertThat(searchProductResponse.getResponseCode(), Matchers.is(200));
        MatcherAssert.assertThat(searchProductResponse.getProducts().size(),Matchers.not(Matchers.is(0)));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getFirst().getName().toLowerCase(),Matchers.not(Matchers.containsString("tshirts")));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getFirst().getCategory().getCategory().toLowerCase(),Matchers.containsString("tshirts"));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getLast().getName().toLowerCase(),Matchers.not(Matchers.containsString("tshirts")));
        MatcherAssert.assertThat(searchProductResponse.getProducts().getLast().getCategory().getCategory().toLowerCase(),Matchers.containsString("tshirts"));
    }
}
