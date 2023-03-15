package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import jakarta.validation.ClockProvider;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.metadata.ConstraintDescriptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class NotEmptyFileValidatorTest {
    /**
     * Method under test: {@link NotEmptyFileValidator#isValid(MultipartFile, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid() throws IOException {
        NotEmptyFileValidator notEmptyFileValidator = new NotEmptyFileValidator();
        MockMultipartFile multipartFile = new MockMultipartFile("Name",
                new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

        ClockProvider clockProvider = mock(ClockProvider.class);
        assertTrue(notEmptyFileValidator.isValid(multipartFile,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link NotEmptyFileValidator#isValid(MultipartFile, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid2() {
        NotEmptyFileValidator notEmptyFileValidator = new NotEmptyFileValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(notEmptyFileValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    /**
     * Method under test: {@link NotEmptyFileValidator#isValid(MultipartFile, ConstraintValidatorContext)}
     */
    @Test
    void testIsValid3() throws IOException {
        NotEmptyFileValidator notEmptyFileValidator = new NotEmptyFileValidator();
        MockMultipartFile multipartFile = new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{}));

        ClockProvider clockProvider = mock(ClockProvider.class);
        assertFalse(notEmptyFileValidator.isValid(multipartFile,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(),
                        (ConstraintDescriptor<?>) mock(ConstraintDescriptor.class), "Constraint Validator Payload",
                        ExpressionLanguageFeatureLevel.DEFAULT, ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

