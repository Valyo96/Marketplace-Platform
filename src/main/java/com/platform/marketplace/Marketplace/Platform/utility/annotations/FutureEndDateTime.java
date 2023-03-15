package com.platform.marketplace.Marketplace.Platform.utility.annotations;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FutureEndDateTimeValidator.class)
public @interface FutureEndDateTime {
}
