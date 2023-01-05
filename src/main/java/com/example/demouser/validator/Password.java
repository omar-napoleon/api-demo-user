package com.example.demouser.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD })

public @interface Password {
    String message() default "Invalid Pattern Password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
