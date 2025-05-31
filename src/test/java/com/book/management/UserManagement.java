package com.book.management;

import java.util.UUID;

import org.json.simple.JSONObject;

public class UserManagement {
	
	private String email;
	private String password = "Test@123";
	private static UserManagement userManagement;
	
	private UserManagement() {}
	
	public static UserManagement getUserManagementInstance() {
		if(userManagement==null) {
			userManagement = new UserManagement();
		}
		return userManagement;
	}
	
	public JSONObject generateUserData() {
		
		email = "user_" + UUID.randomUUID().toString().substring(0, 8) + "@test.com";
		JSONObject user = new JSONObject();
		user.put("email", email);
		user.put("password", password);
		
		return user;
	}
	

}
