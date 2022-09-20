package com.itau.escolaItauSpring.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = TelefoneValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefone {
    String message() default "Telefone inválido";
    Class[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
