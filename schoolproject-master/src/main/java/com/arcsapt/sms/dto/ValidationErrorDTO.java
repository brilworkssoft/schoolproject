package com.arcsapt.sms.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO {
	private List<FieldErrorDTO> errors = new ArrayList<FieldErrorDTO>();

	public ValidationErrorDTO() {

	}

	public void addFieldError(String path, String message) {
		FieldErrorDTO error = new FieldErrorDTO(path, message);
		errors.add(error);
	}

	public List<FieldErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldErrorDTO> errors) {
		this.errors = errors;
	}
}
