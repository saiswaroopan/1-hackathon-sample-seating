package com.krishna.seatbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.krishna.seatbooking.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//@Query(value = "select * from \"users\" where username = ?1", nativeQuery = true)
	User findByUserName(String userName);
}
