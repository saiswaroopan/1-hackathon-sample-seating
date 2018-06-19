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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.dto.UserForm;
import com.krishna.seatbooking.service.SecurityService;
import com.krishna.seatbooking.service.UserService;
import com.krishna.seatbooking.util.UserValidator;

@CrossOrigin
@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserService userService;
	
	private UserValidator userValidator;
	
	@Autowired
	private SecurityService securityService;
	
	public UserController(UserService userService, UserValidator userValidator, SecurityService securityService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.securityService = securityService;
	}
	
	
	@RequestMapping(value = "/login")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("countries", addCounties());

        logger.info(" ----- In registration method for loading form data and countries ---");
        return "login";
    }
	
	
	@PostMapping(value = "/registration")
    public String registration(@ModelAttribute("userForm") UserForm userFormObject, BindingResult bindingResult, Model model) {
		logger.info(" ----- In registration of POST method ---");
		logger.info("email from userFormObject ---  :"+userFormObject.getEmail());
		userValidator.validate(userFormObject, bindingResult);
		
		logger.info(" ----- error count---"+bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
        	logger.info(" -----Registration page havign errors-------");
        	model.addAttribute("countries", addCounties());
			return "login";
        }

        userService.save(buildUser(userFormObject));
        logger.info(" -----After saving user details---");
        securityService.autologin(userFormObject.getEmail(), userFormObject.getPassword());

        return "redirect:/home";
    }
    
	private User buildUser(UserForm userForm) {
		User user = User.builder().updatedTmstp(Calendar.getInstance().getTime()).userName(userForm.getEmail())
				.firstName(userForm.getFirstName()).lastName(userForm.getLastName()).enabled(true)
				.location(userForm.getLocation()).country(userForm.getCountry()).password(userForm.getPassword())
				.createdTmstp(Calendar.getInstance().getTime()).build();
		return user;
	}

	public List<String> addCounties() {
		return Stream.of("IN", "US", "UK").collect(Collectors.toList());
	}

}