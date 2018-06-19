package com.krishna.seatbooking.util;


import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.krishna.seatbooking.dto.User;
import com.krishna.seatbooking.dto.UserForm;

@Component
//@PropertySource("validation.properties")
public class UserValidator implements Validator {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	private UserDetailsService userDetailsService;

	public UserValidator(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	@Override
	public boolean supports(Class<?> classObj) {
		return User.class.equals(classObj);
	}


	@Override
	public void validate(Object o, Errors errors) {
		UserForm user = (UserForm) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty");
		try {
			if (userDetailsService.loadUserByUsername(user.getEmail()) != null) {
				errors.rejectValue("email", "Duplicate.userForm.userName");
			}
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(user.getEmail()).matches())) {
			errors.rejectValue("email", "user.email.invalid");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getConfirmPassword() == null || !user.getConfirmPassword().equals(user.getPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userForm.confirmPassword");
		}

	}
   

}