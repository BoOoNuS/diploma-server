package ua.nure.gateway.service.controllets.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.gateway.service.client.UsersHeatingService;
import ua.nure.gateway.service.controllets.PermissionController;
import ua.nure.gateway.service.model.UserHeating;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Stanislav_Vorozhka
 */
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionControllerImpl implements PermissionController {

    private UsersHeatingService usersHeatingService;

    /**
     * Instantiates new permission controller.
     *
     * @param usersHeatingService the users heating service
     */
    @Autowired
    public PermissionControllerImpl(UsersHeatingService usersHeatingService) {
        this.usersHeatingService = usersHeatingService;
    }

    @Override
    @RequestMapping(value = "/{heatingId}", method = RequestMethod.POST)
    public void giveHeatingPermissionsToUsers(@PathVariable long heatingId, @RequestBody List<Long> userIds) {
        log.debug("PermissionControllerImpl#giveHeatingPermissionsToUsers starts");
        log.info("PermissionControllerImpl#giveHeatingPermissionsToUsers heating id - " +
                heatingId + ", user ids - " + userIds);
        List<UserHeating> userHeatings = userIds.stream().map(toUsersHeating(heatingId)).collect(Collectors.toList());
        userHeatings.forEach(usersHeatingService::createUserHeating);
        log.debug("PermissionControllerImpl#giveHeatingPermissionsToUsers finished");
    }

    @Override
    @RequestMapping(value = "/{heatingId}", method = RequestMethod.DELETE)
    public void takeAwayHeatingPermissionsFromUsers(@PathVariable long heatingId, @RequestBody List<Long> userIds) {
        log.debug("PermissionControllerImpl#takeAwayHeatingPermissionsFromUsers starts");
        log.info("PermissionControllerImpl#takeAwayHeatingPermissionsFromUsers heating id - " +
                heatingId + ", user ids - " + userIds);
        List<UserHeating> userHeatings = userIds.stream().map(toUsersHeating(heatingId)).collect(Collectors.toList());
        userHeatings.forEach(userHeating -> usersHeatingService.deleteUserHeatingByUserAndHeatingIds(userHeating.getUserId(), heatingId));
        log.debug("PermissionControllerImpl#takeAwayHeatingPermissionsFromUsers finished");
    }

    private Function<Long, UserHeating> toUsersHeating(long heatingId) {
        return userId -> new UserHeating(userId, heatingId);
    }
}
