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
import com.krishna.seatbooking.repository.SectionRepository;
import com.krishna.seatbooking.service.SectionService;
import com.krishna.seatbooking.service.SecurityService;

import lombok.val;

@CrossOrigin
@Controller
public class SectionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//@Autowired
	private SectionRepository sectionsRepository;
	
	private SecurityService securityService;
	
	private SectionService sectionService;
	
	public SectionController(SectionRepository sectionsRepository, SecurityService securityService){
		this.sectionsRepository = sectionsRepository;
		this.securityService = securityService;
	}
	
	/*public SectionController(SecurityService securityService){
		this.securityService = securityService;
	}*/
	
	public SectionController() {
		
	}

    @RequestMapping("/")
    public String home(Model model) {
    	logger.info("home method mode object------: "+model);
    	val x = sectionsRepository.findAll();
    	model.addAttribute("sections", x);
    	
        return "home";
    }
    
    @GetMapping(value = "/findSeats/{sectionId}")
	@ResponseBody
	public List<Seat> findSeats(@PathVariable(value = "sectionId") Long sectionId, Model model) {
    	logger.info("sectionId ------: "+sectionId);
		val x = sectionsRepository.findById(sectionId);
		return x.get().getSeats();
	}
    
    @RequestMapping(value = "/bookTickets")
	public String bookTickets(@ModelAttribute("sectionForm") SectionForm sectionForm, BindingResult bindingResult, Model model) {

		sectionService.bookSeat(sectionForm.getSectionId(), sectionForm.getSeatId(),
		securityService.findLoggedInUserName());

		return "redirect:/home";

	}
    
    
}