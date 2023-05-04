package com.sistemas.senior.stockcontrol.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
