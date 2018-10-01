package com.mvppoa.adidas.exceptions;

import java.net.URI;

public final class ErrorConstants {

    public static final String PROBLEM_BASE_URL = "http://adidas-city-itinerary-management";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");

    private ErrorConstants() {
    }
}
