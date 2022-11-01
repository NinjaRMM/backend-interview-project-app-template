package com.ninjaone.backendinterviewproject.exceptions;

import com.ninjaone.backendinterviewproject.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Slf4j
public class ExceptionsHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDto generalExceptionHandler(Exception e) {
		log.error("Error generalExceptionHandler :" + e);
		return ErrorDto.builder()
				.code("-1")
				.message("Unexpected Error")
				.build();
	}

	@ExceptionHandler(CustomValidationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorDto manageHttpMessageNotReadableException(Exception e) {
		log.error("Error manageHttpMessageNotReadableException :" + e);
		return ErrorDto.builder()
				.code("-1")
				.message("Device Already Exist")
				.build();
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public ErrorDto manageEntityNotFoundException(Exception e) {
		log.error("Error manageEntityNotFoundException :" + e);
		return ErrorDto.builder()
				.code("-1")
				.message("Data Not Found")
				.build();
	}

}
