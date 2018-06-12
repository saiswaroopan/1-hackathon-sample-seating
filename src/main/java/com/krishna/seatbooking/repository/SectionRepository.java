package com.krishna.seatbooking.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.seatbooking.dto.Section;
import com.krishna.seatbooking.dto.User;

public interface SectionRepository extends JpaRepository<Section, Long> {
	public Stream<Section> findByName(String name);
	
	List<User> findByNameIgnoreCase(String userName);
}
