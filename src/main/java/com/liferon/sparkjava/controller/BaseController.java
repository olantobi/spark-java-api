package com.liferon.sparkjava.controller;

import com.google.gson.Gson;
import com.liferon.sparkjava.dto.ApiResponse;

public class BaseController {
    protected static final String APPLICATION_JSON = "application/json";

    protected final Gson gson;

    public BaseController() {
        gson = new Gson();
    }

    public String jsonResponse(ApiResponse apiResponse) {
        return gson.toJson(apiResponse);
    }
}
