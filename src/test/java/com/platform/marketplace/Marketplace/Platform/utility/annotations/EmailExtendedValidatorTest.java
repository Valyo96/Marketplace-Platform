package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class EmailExtendedValidatorTest {
    /**
     * Method under test: {@link EmailExtendedValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid() {
        EmailExtendedValidator emailExtendedValidator = new EmailExtendedValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(emailExtendedValidator.isValid("jane.doe@example.org",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link EmailExtendedValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid2() {
        EmailExtendedValidator emailExtendedValidator = new EmailExtendedValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(emailExtendedValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

