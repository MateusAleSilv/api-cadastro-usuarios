package br.com.testapi.cadastrousuario.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
@Documented
public @interface AgeConstraint {
    String message() default "A idade mínima é {value} anos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value() default 18;
}
