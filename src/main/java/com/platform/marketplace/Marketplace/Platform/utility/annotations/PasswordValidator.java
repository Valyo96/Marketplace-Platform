package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.Regex.passwordRegexPattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    public boolean isValid(String password, ConstraintValidatorContext cxt) {
        Pattern pattern = Pattern.compile(passwordRegexPattern);

        if (password == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
