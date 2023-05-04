package com.sistemas.senior.stockcontrol.exception;

import lombok.Getter;

import java.util.Date;

public class MessageExceptionHandler {
    private final Date timestamp;
    private final Integer status;
    private final String message;

    public MessageExceptionHandler(Date timestamp, int status, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
