package com.rest.webservices.controllers;

import com.rest.webservices.exceptionHandler.ExceptionBody;
import com.rest.webservices.exceptionHandler.PostNotFound;
import com.rest.webservices.exceptionHandler.UserAlreadyExists;
import com.rest.webservices.exceptionHandler.UserNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionBody> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public final ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExists ex , WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFound ex , WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFound.class)
    public final ResponseEntity<Object> handlePostNotFoundException(PostNotFound ex , WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionBody, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody("Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity<>(exceptionBody, HttpStatus.BAD_REQUEST);
    }

}
