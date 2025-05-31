package com.book.api.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Common {
	
	private static Common common;
	
	private Common() {
		
	}
	
	public static Common getCommonInstance() {
		if(common==null) {
			common = new Common();
		}
		return common;
	}
	
	public Response getRequest(ApiRequest apiRequest) {
		
		try {
			Response response = RestAssured.given()
	                .baseUri(apiRequest.getBaseUri())
	                .headers(apiRequest.getHeaders()).when().get(apiRequest.getPathParams());
			return response;
		}catch(Exception e) {
			return null;
		}
		
	}
	
	public Response postRequest(ApiRequest apiRequest) {
		
		try {
			Response response = RestAssured.given()
	                .baseUri(apiRequest.getBaseUri())
	                .headers(apiRequest.getHeaders()).body(apiRequest.getBody()).when().post(apiRequest.getPathParams());
			return response;
		}catch(Exception e) {
			return null;
		}
		
	}
	
	public Response deleteRequest(ApiRequest apiRequest) {
		
		try {
			Response response = RestAssured.given()
	                .baseUri(apiRequest.getBaseUri())
	                .headers(apiRequest.getHeaders()).when().delete(apiRequest.getPathParams());
			return response;
		}catch(Exception e) {
			return null;
		}
		
	}
	
public Response putRequest(ApiRequest apiRequest) {
		
		try {
			Response response = RestAssured.given()
	                .baseUri(apiRequest.getBaseUri())
	                .headers(apiRequest.getHeaders()).body(apiRequest.getBody()).when().put(apiRequest.getPathParams());
			return response;
		}catch(Exception e) {
			return null;
		}
		
	}

}
