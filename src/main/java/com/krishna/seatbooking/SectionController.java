package com.krishna.seatbooking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krishna.seatbooking.dto.Seat;
import com.krishna.seatbooking.dto.SectionForm;
import com.krishna.seatbooking.service.SectionService;
import com.krishna.seatbooking.util.SectionValidator;
import com.krishna.seatbooking.util.UserSecurityDetails;

import lombok.val;

@CrossOrigin
@Controller
public class SectionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private SectionService sectionService;
	private SectionValidator sectionFormValidator;
	
	public SectionController(SectionService sectionService, SectionValidator sectionFormValidator) {
		this.sectionService = sectionService;
		this.sectionFormValidator = sectionFormValidator;
	}
	
	@RequestMapping(value = { "/", "/home" })
	public String home(Model model) {
		model.addAttribute("sectionForm", new SectionForm());
		logger.info("sectionService object in home method ---:"+sectionService);
		val x = sectionService.findAll();
		model.addAttribute("sections", x);
		String userName = UserSecurityDetails.findLoggedInUsername();
		model.addAttribute("username", userName);
		model.addAttribute("bookingHistory", sectionService.findBookingHistory(userName));
		return "home";
	}

    
	@GetMapping(value = "/findSeats/{sectionId}")
	@ResponseBody
	public List<Seat> findSeats(@PathVariable(value = "sectionId") Long sectionId, Model model) {
		val x = sectionService.findById(sectionId);
		return x.get().getSeats();
	}
    
	@RequestMapping(value = "/bookTickets")
	public String bookTickets(@ModelAttribute("sectionForm") SectionForm sectionForm, BindingResult bindingResult,
			Model model) {
		logger.info("sectionFormValidator object in bookTickets method---: "+sectionFormValidator);
		sectionFormValidator.validate(sectionForm, bindingResult);
		String userName = UserSecurityDetails.findLoggedInUsername();
		model.addAttribute("username", userName);
		if (bindingResult.hasErrors()) {
			val x = sectionService.findAll();
			model.addAttribute("sections", x);
			model.addAttribute("selectedSection", sectionForm.getSectionId());
			model.addAttribute("bookingHistory", sectionService.findBookingHistory(userName));
			return "home";
		}
		sectionService.bookSeat(sectionForm.getSectionId(), sectionForm.getSeatId(),
				UserSecurityDetails.findLoggedInUsername());
		return "redirect:/home?ticketBooked";
	}
    
    
}