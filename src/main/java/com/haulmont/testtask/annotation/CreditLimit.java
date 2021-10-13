package com.haulmont.testtask.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = CreditLimitValidator.class)
@Documented
public @interface CreditLimit {

    String message() default "{Сумма не должна превышать лимит}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}