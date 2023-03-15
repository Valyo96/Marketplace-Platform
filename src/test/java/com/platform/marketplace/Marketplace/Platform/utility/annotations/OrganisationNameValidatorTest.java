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

class OrganisationNameValidatorTest {
    /**
     * Method under test: {@link OrganisationNameValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid() {
        OrganisationNameValidator organisationNameValidator = new OrganisationNameValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(organisationNameValidator.isValid("foo",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link OrganisationNameValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid2() {
        OrganisationNameValidator organisationNameValidator = new OrganisationNameValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(organisationNameValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link OrganisationNameValidator#isValid(String, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid3() {
        OrganisationNameValidator organisationNameValidator = new OrganisationNameValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(organisationNameValidator.isValid("А9999",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

