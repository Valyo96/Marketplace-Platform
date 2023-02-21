package com.platform.marketplace.Marketplace.Platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NotAuthorizeException extends RuntimeException{
    public NotAuthorizeException(String message) {
        super(message);

    }
}
