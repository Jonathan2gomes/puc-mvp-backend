package com.sistemas.senior.stockcontrol.exception;


public class ConflictException extends RuntimeException {

    public ConflictException(final String errorDescription) {
        super(errorDescription);
    }
}
