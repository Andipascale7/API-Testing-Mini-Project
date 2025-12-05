package com.sparta.getallbrands.restassured.utils;

import java.util.ResourceBundle;

public class Config {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("config");

    public static String getBaseUri() {return BUNDLE.getString("listAllProducts.uri");

    }
}
