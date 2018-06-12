package com.krishna.seatbooking;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.krishna.seatbooking.dto.Seat;
import com.krishna.seatbooking.repository.SectionRepository;

import lombok.val;

@CrossOrigin
@Controller
public class SectionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//@Autowired
	private SectionRepository sectionsRepository;
	
	public SectionController(SectionRepository sectionsRepository){
		this.sectionsRepository = sectionsRepository;
	}

    @RequestMapping("/")
    public String home(Model model) {
    	val x = sectionsRepository.findAll();
    	model.addAttribute("sections", x);
    	
        return "home";
    }
    
    @GetMapping(value = "/findSeats/{sectionId}")
	@ResponseBody
	public List<Seat> findSeats(@PathVariable(value = "sectionId") Long sectionId, Model model) {
		val x = sectionsRepository.findById(sectionId);
		return x.get().getSeats();
	}
}