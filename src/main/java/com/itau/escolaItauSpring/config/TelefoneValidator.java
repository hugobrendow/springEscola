package com.itau.escolaItauSpring.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    private Pattern padrao = Pattern.compile("(\\(\\d{2}\\)\\s)?(\\d{4,5}\\-\\d{4})");

    @Override
    public void initialize(Telefone ann) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || "".equals(value)) {
            return true;
        }

        Matcher matcher = padrao.matcher(value);
        return matcher.matches();
    }

}
