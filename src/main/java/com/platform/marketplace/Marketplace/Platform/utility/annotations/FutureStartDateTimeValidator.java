package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class FutureStartDateTimeValidator implements ConstraintValidator<FutureStartDateTime, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        if (localDateTime == null) {
            return true; // null values are handled by @NotNull annotation
        }
        LocalDateTime now = LocalDateTime.now();
        return localDateTime.isAfter(now);
    }
}
