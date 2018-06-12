package com.krishna.seatbooking.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.service.UserService;

@Component
public class UserValidator implements Validator {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private UserService userService;


	@Override
	public boolean supports(Class<?> classObj) {
		return User.class.equals(classObj);
	}


	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		logger.info("email before validation---  :"+user.getEmail());
		logger.info("password before validation---  :"+user.getPassword());
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("userName", "Size.userForm.userName");
        }
		
		if (userService.findByUserName(user.getEmail()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.userName");
        }
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
        logger.info("error object--  :"+errors);
	}
   

}