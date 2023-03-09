package com.platform.marketplace.Marketplace.Platform.utility.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidDateTimeFormatException extends RuntimeException{
    public InvalidDateTimeFormatException(String message) {
        super(message);
    }
}
