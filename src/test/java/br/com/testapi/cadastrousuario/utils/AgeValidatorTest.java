package br.com.testapi.cadastrousuario.utils;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AgeValidatorTest {

    @Test
    public void testValidAge() {
        AgeValidator ageValidator = new AgeValidator();
        ageValidator.initialize(createAgeConstraint(18));

        LocalDate dateOfBirth = LocalDate.now().minusYears(20);
        boolean isValid = ageValidator.isValid(dateOfBirth, mockContext());

        assertThat(isValid).isTrue();
    }

    @Test
    public void testInvalidAge() {
        AgeValidator ageValidator = new AgeValidator();
        ageValidator.initialize(createAgeConstraint(18));

        LocalDate dateOfBirth = LocalDate.now().minusYears(16);
        boolean isValid = ageValidator.isValid(dateOfBirth, mockContext());

        assertThat(isValid).isFalse();
    }

    private AgeConstraint createAgeConstraint(int minAge) {
        AgeConstraint ageConstraint = mock(AgeConstraint.class);
        when(ageConstraint.value()).thenReturn(minAge);
        return ageConstraint;
    }

    private ConstraintValidatorContext mockContext() {
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        when(context.buildConstraintViolationWithTemplate("A idade mínima é 18 anos."))
                .thenReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class));
        return context;
    }
}
