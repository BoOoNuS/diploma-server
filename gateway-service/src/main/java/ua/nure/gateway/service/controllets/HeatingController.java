package ua.nure.gateway.service.controllets;

import java.util.List;

import ua.nure.gateway.service.model.Heating;
import ua.nure.gateway.service.model.HeatingConfiguration;
import ua.nure.gateway.service.model.HeatingUsersInfo;

/**
 * @author Stanislav_Vorozhka
 */
public interface HeatingController {

    /**
     * Gets list of heatings by user id.
     *
     * @param userId the user id
     * @return the list of heatings available by the user id
     */
    List<Heating> findUserHeatings(long userId);

    /**
     * Reconfigure heating emulator with the heating configuration, by user id and heating id.
     *
     * @param userId               the user id
     * @param heatingId            the heating id
     * @param heatingConfiguration the new heating configuration
     */
    void reConfigHeating(long userId, long heatingId, HeatingConfiguration heatingConfiguration);

    /**
     * Gets current heating configuration by the user id and heating id
     *
     * @param userId    the user id
     * @param heatingId the heating id
     * @return the current heating configuration
     */
    HeatingConfiguration getHeatingConfiguration(long userId, long heatingId);

    /**
     * Gets heating info without assigned users information.
     *
     * @param userId the user id
     * @return the heatings info, contains heating + heating settings
     */
    List<HeatingUsersInfo> getHeatingInfoWithoutAssignedUsersByUserId(long userId);

    /**
     * Gets all heating users info.
     *
     * @return the heating users info
     */
    List<HeatingUsersInfo> getAllHeatingUsersInfo();

    /**
     * Updates heating by updatedHeating#id
     *
     * @param updatedHeating the heating with new settings
     * @return the updated heating
     * @throws IllegalArgumentException when heating with updatedHeating#id does't exists
     */
    Heating updateHeating(Heating updatedHeating);

    /**
     * Creates new heating.
     *
     * @param heating the heating
     * @return created heating with generated id, and default values, if needed
     */
    Heating createHeating(Heating heating);

    /**
     * Search heating by the id.
     *
     * @param heatingId the heating id
     * @return the found heating
     */
    Heating findHeatingById(long heatingId);

    /**
     * Gets all heatings by room name (description) and user id.
     *
     * @param userId      the user id
     * @param description the room name (description)
     * @return the heatings found by room name (description) and user id
     */
    List<HeatingUsersInfo> getHeatingUserInfoByUserIdAndDescription(long userId, String description);

    /**
     * Gets all heatings by room name (description).
     *
     * @param description the room name (description)
     * @return the heatings found by room name (description)
     */
    List<HeatingUsersInfo> getHeatingUserInfoByDescription(String description);
}
