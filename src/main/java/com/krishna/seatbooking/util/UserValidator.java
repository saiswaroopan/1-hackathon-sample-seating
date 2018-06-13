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

	@Autowired
    private UserDetailsService userService;


	@Override
	public boolean supports(Class<?> classObj) {
		return User.class.equals(classObj);
	}


	@Override
	public void validate(Object obj, Errors errors) {
		UserForm user = (UserForm) obj;
		logger.info("email before validation---  :"+user.getEmail());
		logger.info("password before validation---  :"+user.getPassword());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
		try { 
			
			//logger.info("user details service for email--  :"+userService.loadUserByUsername(user.getEmail()));
			
			if (userService.loadUserByUsername(user.getEmail()) != null) {
	            errors.rejectValue("email", "Duplicate.userForm.userName");
	        }
		 } catch(Exception ex) {
	        	logger.warn(ex.getMessage(), ex);
         }
		 Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		 if (!(pattern.matcher(user.getEmail()).matches())) {

			errors.rejectValue("email", "user.email.invalid");

		 }
		
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
         if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
         }
        
         if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
         }
         logger.info("error object--  :"+errors);
       
	}
   

}