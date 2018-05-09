package ua.nure.users.heating.service.dao;

import org.springframework.data.repository.CrudRepository;
import ua.nure.users.heating.service.model.UserHeating;

import java.util.List;

/**
 * Provide access to users heating table.
 *
 * @author Stanislav_Vorozhka
 */
public interface UserHeatingDAO extends CrudRepository<UserHeating, Long> {

    /**
     * Gets users heating by user id.
     *
     * @param userId the user id
     * @return the found users heating
     */
    List<UserHeating> findAllByUserId(long userId);

    /**
     * Gets users heating by heating id.
     *
     * @param heatingId the heating id
     * @return the found users heating
     */
    List<UserHeating> findAllByHeatingId(long heatingId);

    /**
     * Deletes users heating by user id and heating id.
     *
     * @param userId    the user id
     * @param heatingId the heating id
     */
    void deleteByUserIdAndHeatingId(long userId, long heatingId);
}
