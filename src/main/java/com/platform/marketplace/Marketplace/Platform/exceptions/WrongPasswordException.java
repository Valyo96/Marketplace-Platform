package com.platform.marketplace.Marketplace.Platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String message) {
        super(message);

    }
}
