package com.ninjaone.backendinterviewproject.controller.advicers;

import com.ninjaone.backendinterviewproject.exceptions.DeviceNotFoundException;
import com.ninjaone.backendinterviewproject.controller.responses.RestControllerErrorTemplate;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotAvailableForDeviceException;
import com.ninjaone.backendinterviewproject.exceptions.ServiceNotFoundException;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleEmptyResultException(RuntimeException ex, WebRequest request){
        String body = "Couldn't find the object requested by the ID passed as parameter, please check.";

        return handleExceptionInternal(ex, new RestControllerErrorTemplate(body, HttpStatus.NOT_FOUND.value(), "ERROR"), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected  ResponseEntity<Object> handleIntegrityConstraintViolations(RuntimeException ex, WebRequest request) {
        String body = "There is a property passed as parameter that don't allow duplicates and already exists in the database, please rectify.";

        return handleExceptionInternal(ex, new RestControllerErrorTemplate(body, HttpStatus.BAD_REQUEST.value(), "ERROR"), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @ExceptionHandler(value = {PropertyValueException.class})
    protected  ResponseEntity<Object> handleNullPropertyValues(RuntimeException ex, WebRequest request) {
        String body = "There is a property missing that cannot be NULL. Please, rectify.";

        return handleExceptionInternal(ex, new RestControllerErrorTemplate(body, HttpStatus.BAD_REQUEST.value(), "ERROR"), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {DeviceNotFoundException.class})
    protected  ResponseEntity<Object> handleDeviceNotFoundException() {
        String body = "There is no device with the ID passed. Please validate.";

        return new ResponseEntity<>(new RestControllerErrorTemplate(body, HttpStatus.NOT_FOUND.value(), "ERROR"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {ServiceNotFoundException.class})
    protected  ResponseEntity<Object> handleServiceNotFoundException() {
        String body = "There is no Service with the ID passed. Please validate.";

        return new ResponseEntity<>(new RestControllerErrorTemplate(body, HttpStatus.NOT_FOUND.value(), "ERROR"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ServiceNotAvailableForDeviceException.class})
    protected ResponseEntity<Object> handleServiceNotMatchingDevice() {
        String body = "The Service Passed isn't available for the device's type";

        return new ResponseEntity<>(new RestControllerErrorTemplate(body, HttpStatus.BAD_REQUEST.value(), "INFORMATION MISMATCH"), HttpStatus.BAD_REQUEST);
    }

}
