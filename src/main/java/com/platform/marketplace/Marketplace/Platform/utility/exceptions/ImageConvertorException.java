package com.platform.marketplace.Marketplace.Platform.utility.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ImageConvertorException extends RuntimeException{
    public ImageConvertorException(String message){
        super(message);
    }
}
