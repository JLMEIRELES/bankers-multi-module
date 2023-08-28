package com.example.meireles.banker.application.utils.validations.annotation;

import com.example.meireles.banker.application.utils.validations.validators.DocumentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = DocumentValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface Document {

    String message() default "Invalid document";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
