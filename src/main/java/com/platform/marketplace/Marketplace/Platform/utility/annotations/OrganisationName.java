package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.platform.marketplace.Marketplace.Platform.utility.consts.ConstantMessages.ORGANISATION_NAME_VALIDATION_MESSAGES;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrganisationNameValidator.class)
public @interface OrganisationName {
    String message() default ORGANISATION_NAME_VALIDATION_MESSAGES;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
