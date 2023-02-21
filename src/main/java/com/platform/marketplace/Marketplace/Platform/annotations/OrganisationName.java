package com.platform.marketplace.Marketplace.Platform.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.organisationNameValidationMessages;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrganisationNameValidator.class)
public @interface OrganisationName {
    String message() default organisationNameValidationMessages;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
