package ua.nure.heating.service.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ua.nure.heating.service.model.Heating;

/**
 * Provide access to heating table.
 *
 * @author Stanislav_Vorozhka
 */
public interface HeatingDAO extends CrudRepository<Heating, Long> {

    /**
     * Gets all heatings by room name (description).
     *
     * @param description the room name (description)
     * @return the heatings found by room name (description)
     */
    List<Heating> findHeatingsByDescriptionContaining(String description);
}
