package com.platform.marketplace.Marketplace.Platform.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.platform.marketplace.Marketplace.Platform.consts.Regex.organisationNameRegexPattern;

public class OrganisationNameValidator implements ConstraintValidator<OrganisationName , String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile(organisationNameRegexPattern );
        if (s == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
