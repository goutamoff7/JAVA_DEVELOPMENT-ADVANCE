package com.example.watchlist.entityValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingValidator implements ConstraintValidator<Rating,Float>
{
    @Override
    public boolean isValid(Float v, ConstraintValidatorContext constraintValidatorContext) {
        return (v >= 5) && (v <= 10);
    }
}
