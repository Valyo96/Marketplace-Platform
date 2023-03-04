package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.EMAIL_REGEX_PATTERN;

public class EmailExtendedValidator implements ConstraintValidator<EmailExtended , String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        if(email == null){
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}