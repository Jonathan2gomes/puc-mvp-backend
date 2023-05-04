package com.sistemas.senior.stockcontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<MessageExceptionHandler> resourceAlreadyExists(ConflictException exception) {
        MessageExceptionHandler costumeError = new MessageExceptionHandler(
                new Date(), HttpStatus.CONFLICT.value(), exception.getMessage());
        return new ResponseEntity<>(costumeError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> entityNotFound(NotFoundException exception) {
        MessageExceptionHandler costumeError = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(costumeError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> handleAllUncaughtException() {
        return new ResponseEntity<>(new MessageExceptionHandler(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknow Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
