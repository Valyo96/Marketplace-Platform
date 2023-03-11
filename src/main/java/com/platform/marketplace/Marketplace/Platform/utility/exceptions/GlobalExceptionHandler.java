package com.platform.marketplace.Marketplace.Platform.utility.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler({AlreadyExistException.class ,
                NotAuthorizeException.class ,
                NotFoundException.class,
                WrongPasswordException.class ,
                BadCredentialsException.class ,
                InvalidDateTimeFormatException.class ,
                InvalidDateTimeFormatException.class})
        public ModelAndView handleException(Exception ex , HttpServletRequest request) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:" + request.getHeader("Referer"));
            mav.addObject("errorMessage", ex.getMessage());
            return mav;
        }


}