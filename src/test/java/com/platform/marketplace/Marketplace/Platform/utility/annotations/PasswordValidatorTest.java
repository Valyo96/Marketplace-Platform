package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class PasswordValidatorTest {
    /**
     * Method under test: {@link PasswordValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid() {
        PasswordValidator passwordValidator = new PasswordValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(passwordValidator.isValid("iloveyou",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link PasswordValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid2() {
        PasswordValidator passwordValidator = new PasswordValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(passwordValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

