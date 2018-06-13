package com.krishna.seatbooking.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishna.seatbooking.dto.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {
	public Stream<Section> findByName(String name);
	
	Optional<List<Section>> findBySeatsUserName(String userName);
}
