package com.krishna.seatbooking.service;

import com.krishna.seatbooking.dto.User;

public interface UserService {

	void save(User user);
	
	User findByUserName(String userName);
	
}
