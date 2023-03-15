package com.platform.marketplace.Marketplace.Platform.utility.annotations;

import com.platform.marketplace.Marketplace.Platform.dto.EventDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class FutureEndDateTimeValidator implements ConstraintValidator<FutureEndDateTime ,EventDTO>{
    @Override
    public void initialize(FutureEndDateTime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(EventDTO eventDTO, ConstraintValidatorContext constraintValidatorContext) {
        if(eventDTO.getStartsAt()==null){
            return true;
        }
        return eventDTO.getEndsAt().isAfter(eventDTO.getStartsAt());
    }
}
