package br.com.testapi.cadastrousuario.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<AgeConstraint, LocalDate> {
    private int minAge;

    @Override
    public void initialize(AgeConstraint constraintAnnotation) {
        this.minAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true;
        }
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dateOfBirth, currentDate).getYears();

        if (age < minAge) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A idade mínima é " + minAge + " anos.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}

