package com.microcompany.productsservice.constraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProductName.Validator.class})
public @interface ProductName {

    String message()
            default "El tamaño del nombre no es correcto";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    public class Validator implements ConstraintValidator<ProductName, String> {
        @Override
        public void initialize(final ProductName name) {
        }

        @Override
        public boolean isValid(final String name, final ConstraintValidatorContext constraintValidatorContext) {
            String cleanName = name.trim();
            return (cleanName.length() >= 3 && cleanName.length() <= 20);
        }
    }
}