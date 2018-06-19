package com.krishna.seatbooking.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.krishna.seatbooking.dto.SectionForm;

@Component
public class SectionValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return SectionForm.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SectionForm sectionForm = (SectionForm) o;
		if (sectionForm.getSectionId() == null)
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sectionId", "NotEmpty");
		if (sectionForm.getSeatId() == null)
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "seatId", "NotEmpty");
	}
}
