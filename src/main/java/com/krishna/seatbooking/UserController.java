package com.krishna.seatbooking;

import java.util.Calendar;
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
import com.krishna.seatbooking.dto.UserForm;
import com.krishna.seatbooking.service.SecurityService;
import com.krishna.seatbooking.service.UserService;
import com.krishna.seatbooking.util.UserValidator;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private SecurityService securityService;
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("countries", addCounties());

        logger.info(" ----- In registration of GET method ---");
        return "registration";
    }
	
	public List<String> addCounties() {
		return Stream.of("IN", "US", "UK").collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UserForm userFormObject, BindingResult bindingResult, Model model) {
		logger.info(" ----- In registration of POST method ---");
		logger.info("email from userFormObject ---  :"+userFormObject.getEmail());
		userValidator.validate(userFormObject, bindingResult);
		
		logger.info(" ----- error count---"+bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
        	logger.info(" -----Registration page havign errors-------");
            return "registration";
        }

        userService.save(buildUser(userFormObject));
        logger.info(" -----After saving user details---");
        securityService.autologin(userFormObject.getEmail(), userFormObject.getPassword());

        return "redirect:/home";
    }
    
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
		
		logger.info(" ----- In login method ---"+error);
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("userForm") UserForm userFormObject, Model model, String error, String logout) {
		
		logger.info(" ----- In login method ---"+error);
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }*/
	
	private User buildUser(UserForm userForm) {
		User user = User.builder().updatedTmstp(Calendar.getInstance().getTime()).userName(userForm.getEmail())
				.firstName(userForm.getFirstName()).lastName(userForm.getLastName()).enabled(true)
				.location(userForm.getLocation()).country(userForm.getCountry()).password(userForm.getPassword())
				.createdTmstp(Calendar.getInstance().getTime()).build();
		return user;
	}


}