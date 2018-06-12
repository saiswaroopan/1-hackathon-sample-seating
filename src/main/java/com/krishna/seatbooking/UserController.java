package com.krishna.seatbooking;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.service.SecurityService;
import com.krishna.seatbooking.service.UserService;
import com.krishna.seatbooking.util.UserValidator;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserService userService;
	
	private SecurityService securityService;
	
	@Autowired
	private UserValidator userValidator;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	public UserController() {
		
	}
	
	public UserController(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("countries", addCounties());

        logger.info(" ----- In registration of GET method ---");
        return "registration";
    }
	
	public List<String> addCounties() {
		return Stream.of("IN", "US", "UK").collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userFormObject, BindingResult bindingResult, Model model) {
		logger.info(" ----- In registration of POST method ---");
		
		userValidator.validate(userFormObject, bindingResult);
		
		logger.info(" ----- error count---"+bindingResult.getErrorCount());
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userFormObject);

        securityService.autologin(userFormObject.getUserName(), userFormObject.getConfirmPassword());

        return "redirect:/home";
    }
    
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
		
		logger.info(" ----- In login method ---");
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	logger.info(" ----- In welcome method ---");
        return "welcome";
    }
    


    

}