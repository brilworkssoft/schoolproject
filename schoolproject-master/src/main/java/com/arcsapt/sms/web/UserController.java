package com.arcsapt.sms.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arcsapt.sms.common.Constants;
import com.arcsapt.sms.dto.ResponseDTO;
import com.arcsapt.sms.dto.ValidationErrorDTO;
import com.arcsapt.sms.entity.UserMaster;
import com.arcsapt.sms.entity.UserMaster.CheckLogin;
import com.arcsapt.sms.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource(name = Constants.SERVICE_USER)
	private UserService userService;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseDTO processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		ResponseDTO objResponse = null;
		try {
			objResponse = new ResponseDTO(false, 400,
					processFieldErrors(fieldErrors), Constants.STATUS_ERROR,
					Constants.LINK_FIELD_ERROR);
		} catch (Exception exception) {

		}
		return objResponse;
	}

	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		for (FieldError fieldError : fieldErrors) {
			// String localizedErrorMessage =
			// resolveLocalizedErrorMessage(fieldError);
			// dto.addFieldError(fieldError.getField(), localizedErrorMessage);
			dto.addFieldError(fieldError.getField(),
					fieldError.getDefaultMessage());
		}

		return dto;
	}

	/*
	 * private String resolveLocalizedErrorMessage(FieldError fieldError) {
	 * Locale currentLocale = LocaleContextHolder.getLocale(); String
	 * localizedErrorMessage = messageSource.getMessage(fieldError,
	 * currentLocale);
	 * 
	 * //If the message was not found, return the most accurate field error code
	 * instead. //You can remove this check if you prefer to get the default
	 * error message. if
	 * (localizedErrorMessage.equals(fieldError.getDefaultMessage())) { String[]
	 * fieldErrorCodes = fieldError.getCodes(); localizedErrorMessage =
	 * fieldErrorCodes[0]; }
	 * 
	 * return localizedErrorMessage; }
	 */

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody
	ResponseDTO login(
			@RequestBody(required = true) @Validated(CheckLogin.class) UserMaster userMaster) {
		ResponseDTO objResponseDTO = null;
		try {
			objResponseDTO = this.userService.login(userMaster);
		} finally {

		}
		return objResponseDTO;
	}
	
	@RequestMapping(value = "name", method = RequestMethod.POST)
	public @ResponseBody String getName(){
		return "hello";
	}
}
