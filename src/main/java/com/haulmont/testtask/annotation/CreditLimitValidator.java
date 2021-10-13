package com.haulmont.testtask.annotation;

import com.haulmont.testtask.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditLimitValidator implements ConstraintValidator<CreditLimit, Integer> {

    private Credit credit;

    @Override
    public boolean isValid (Integer value, ConstraintValidatorContext context) {
        try {
            if (value <= credit.getCreditLimit()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}