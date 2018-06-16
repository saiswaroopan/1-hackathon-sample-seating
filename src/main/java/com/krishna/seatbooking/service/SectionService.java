package com.krishna.seatbooking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krishna.seatbooking.dto.Section;
import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.repository.SectionRepository;

@Service
public class SectionService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private SectionRepository sectionRepository;
	
	@Transactional
	public void bookSeat(Long sectionId, Long seatId, String userName) {

		Section section = sectionRepository.getOne(sectionId);

		section.getSeats().stream().filter(seat -> seat.getId().equals(seatId)).forEach(seat -> {

			seat.setAvailable(false);
			//seat.setUserName(userName);
		});



	}
}
