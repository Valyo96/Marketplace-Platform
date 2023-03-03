package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.DATE_MUST_BE_VALID;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureDateTimeValidator.class)
public @interface FutureDateTime {
    String message() default DATE_MUST_BE_VALID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
