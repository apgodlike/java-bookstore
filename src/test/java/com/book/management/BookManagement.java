package com.book.management;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.book.api.common.ApiRequest;
import com.book.api.common.Common;
import com.book.api.common.Config;
import com.book.api.common.ExtentReportCommon;
import com.book.api.common.AssertUtil;

import io.restassured.response.Response;

public class BookManagement {

    public static Response postBookSignup(JSONObject userData, String accessToken) {
        Common common = Common.getCommonInstance();
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setPathParams("/signup");
        apiRequest.setBody(userData);

        Response response = common.postRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("POST /signup response: " + response.asString());
        AssertUtil.assertEquals("Signup status", 200, response.getStatusCode());
        return response;
    }

    public static Response postBookLogin(JSONObject userData, String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        apiRequest.setBody(userData);
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setPathParams("/login");

        Response response = common.postRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("POST /login response: " + response.asString());
        AssertUtil.assertEquals("Login status", 200, response.getStatusCode());
        return response;
    }

    public static Response getBookWithId(int id, String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setPathParams("/books/" + id);

        Response response = common.getRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("GET /books/" + id + " response: " + response.asString());
        return response;
    }

    public static Response getAllBook(String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setPathParams("/books/");

        Response response = common.getRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("GET /books response: " + response.asString());
        return response;
    }

    public static Response deleteBookWithId(int id, String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setPathParams("/books/" + id);

        Response response = common.deleteRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("DELETE /books/" + id + " response: " + response.asString());
        return response;
    }

    public static Response postCreateBook(JSONObject body, String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setBody(body);
        apiRequest.setPathParams("/books/");

        Response response = common.postRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("POST /books response: " + response.asString());
        return response;
    }

    public static Response putUpdateBook(int bookId, JSONObject body, String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setBody(body);
        apiRequest.setPathParams("/books/" + bookId);

        Response response = common.putRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("PUT /books/" + bookId + " response: " + response.asString());
        return response;
    }
    
    public static Response getUpdateBook(int bookId, JSONObject body, String accessToken) {
        Common common = Common.getCommonInstance();

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setBaseUri(Config.get("base.uri"));
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + accessToken);
        apiRequest.setHeaders(headers);
        apiRequest.setPathParams("/books/" + bookId);

        Response response = common.getRequest(apiRequest);
        ExtentReportCommon.getTestStep().pass("PUT /books/" + bookId + " response: " + response.asString());
        return response;
    }
    
}