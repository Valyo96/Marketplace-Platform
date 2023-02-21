package com.platform.marketplace.Marketplace.Platform.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.platform.marketplace.Marketplace.Platform.consts.Regex.emailRegexPattern;

public class EmailExtendedValidator implements ConstraintValidator<EmailExtended , String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        Pattern pattern = Pattern.compile(emailRegexPattern);
        if(email == null){
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}