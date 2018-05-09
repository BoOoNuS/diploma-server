package ua.nure.gateway.service.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ua.nure.gateway.service.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @param <T> {inheritance}
 * @author Stanislav_Vorozhka
 */
@Component
public class ValidatorImpl implements Validator {

    private javax.validation.Validator validator;

    /**
     * Instantiates new validator.
     *
     * @param validator the validator
     */
    @Autowired
    public ValidatorImpl(javax.validation.Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!CollectionUtils.isEmpty(violations)) {
            StringBuilder violationsSB = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                violationsSB.append(violation.getMessage())
                        .append(". Invalid value - ")
                        .append(violation.getInvalidValue())
                        .append(System.lineSeparator());
            }
            throw new ValidationException(violationsSB.toString());
        }
    }
}
