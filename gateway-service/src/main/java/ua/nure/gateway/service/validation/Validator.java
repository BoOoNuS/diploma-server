package ua.nure.gateway.service.validation;

/**
 * @author Stanislav_Vorozhka
 */
public interface Validator {

    /**
     * Validate entity.
     *
     * @param entity the entity for validation
     * @throws javax.validation.ValidationException when set of violations is not empty
     */
    <T> void validate(T entity);
}
