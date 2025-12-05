package com.sparta.getallbrands.restassured;

import com.sparta.getallbrands.restassured.pojos.BrandsItem;
import com.sparta.getallbrands.restassured.pojos.GetAllBrandsListResponse;
import com.sparta.getallproducts.utils.Config;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ListAllBrandsTest {
    private static Response response;
    private static GetAllBrandsListResponse getAllBrandsListResponse;
    private static final String BASE_URI = Config.getBaseUri();

    //creating a hook
    //create a Response method
    // test statuscode is 200
    //generate a pojo from json
    //create a pojo response variable

    @BeforeAll
    static void beforeAll() {
        response = RestAssured
                .given()//given some condition
                    .baseUri(BASE_URI)//url sending the request to                                                               th request to
                    .headers("Accept", "application/json")
                    .expect().defaultParser(Parser.JSON)
                .when()//
                    .get("/api/brandsList")//Specify request                                             want
                .then()// the results of what is being returned
                    .log().all(false)
                    .extract().response();
        getAllBrandsListResponse = response.as(GetAllBrandsListResponse.class);

    }

    @Test
    void testStatusCode200() {
        MatcherAssert.assertThat(response.statusCode(), Matchers.is(200));
    }

    @Test
    void getAllBrands(){
        MatcherAssert.assertThat(
                response.jsonPath().getList("brands", BrandsItem.class).size(), Matchers.is(34));
    }

    @Test
    void getBrandName(){
        System.out.println(getAllBrandsListResponse.getBrands().getFirst().getBrand());
        MatcherAssert.assertThat(getAllBrandsListResponse.getBrands().getFirst().getBrand(), Matchers.is("Polo"));

    }
}
