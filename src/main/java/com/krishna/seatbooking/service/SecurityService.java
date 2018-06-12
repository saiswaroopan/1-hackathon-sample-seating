package com.krishna.seatbooking.service;

public interface SecurityService {

	String findLoggedInUserName();
	
	void autologin(String username, String password);
	
}
