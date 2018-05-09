package ua.nure.users.heating.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.users.heating.service.model.UserHeating;

import java.util.List;

/**
 * The user heating controller, provide access to heating systems (users heating).
 *
 * @author Stanislav_Vorozhka
 */
public interface UserHeatingController {

    /**
     * Creates user heating.
     *
     * @param userHeating the user heating
     * @return the created user heating with generated id
     */
    UserHeating createUserHeating(UserHeating userHeating);

    /**
     * Gets user heating by id.
     *
     * @return the found user heating
     */
    UserHeating findUserHeatingById(long id);

    /**
     * Gets users heating by user id.
     *
     * @param userId the user id
     * @return the found users heating
     */
    List<UserHeating> findUsersHeatingByUserId(long userId);

    /**
     * Gets users heating by heating id.
     *
     * @param heatingId the heating id
     * @return the found users heating
     */
    List<UserHeating> findUsersHeatingByHeatingId(long heatingId);

    /**
     * Edits user heating by id.
     *
     * @param userHeating the user heating with defined id
     * @return the edited user heating
     * @throws IllegalArgumentException if user heating does not have id
     */
    UserHeating editUserHeatingById(UserHeating userHeating);

    /**
     * Deletes user heating by id.
     *
     * @param userHeatingId the user heating identifier
     */
    void deleteUserHeatingById(long userHeatingId);

    /**
     * Deletes users heating by user id and heating id.
     *
     * @param userId    the user id
     * @param heatingId the heating id
     */
    void deleteUserHeatingByUserAndHeatingIds(long userId, long heatingId);
}
