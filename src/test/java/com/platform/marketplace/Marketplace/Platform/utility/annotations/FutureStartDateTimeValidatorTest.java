package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;

import java.time.LocalDateTime;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class FutureStartDateTimeValidatorTest {
    /**
     * Method under test: {@link FutureStartDateTimeValidator#isValid(LocalDateTime, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid() {
        FutureStartDateTimeValidator futureStartDateTimeValidator = new FutureStartDateTimeValidator();
        LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(futureStartDateTimeValidator.isValid(localDateTime,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link FutureStartDateTimeValidator#isValid(LocalDateTime, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid2() {
        FutureStartDateTimeValidator futureStartDateTimeValidator = new FutureStartDateTimeValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(futureStartDateTimeValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

