package ua.nure.heating.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.heating.service.model.Heating;

import java.util.List;

/**
 * The heating controller, provide access to heating systems (heating).
 *
 * @author Stanislav_Vorozhka
 */
public interface HeatingController {

    /**
     * Creates heating.
     *
     * @param heating the heating machine
     * @return the created heating machine with generated id
     */
    Heating createHeating(Heating heating);

    /**
     * Gets heating by id.
     *
     * @param heatingId the heating identifier
     * @return the found heating machine
     */
    Heating findHeatingById(long heatingId);

    /**
     * Edits heating by id.
     *
     * @param heating the heating machine with defined id
     * @return the edited heating machine
     * @throws IllegalArgumentException if heating does not have id
     */
    Heating editHeatingById(Heating heating);

    /**
     * Deletes heating by id.
     *
     * @param heatingId the heating identifier
     */
    void deleteHeatingById(long heatingId);

    /**
     * Gets all heatings.
     *
     * @return the list of all heatings
     */
    List<Heating> findAll();

    /**
     * Gets all heatings by room name (description).
     *
     * @param description the room name (description)
     * @return the heatings found by room name (description)
     */
    List<Heating> findHeatingsByDescription(String description);
}
