package com.platform.marketplace.Marketplace.Platform.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.platform.marketplace.Marketplace.Platform.consts.ConstantMessages.passwordValidationMessage;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default passwordValidationMessage;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
