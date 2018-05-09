package ua.nure.gateway.service.controllets;

import java.util.List;

/**
 * @author Stanislav_Vorozhka
 */
public interface PermissionController {

    /**
     * Gives heating permissions to users by heating id.
     *
     * @param heatingId the heating id
     * @param userIds   the user ids
     */
    void giveHeatingPermissionsToUsers(long heatingId, List<Long> userIds);

    /**
     * Takes away heating permissions from users by heating id.
     *
     * @param heatingId the heating id
     * @param userIds   the user ids
     */
    void takeAwayHeatingPermissionsFromUsers(long heatingId, List<Long> userIds);
}
