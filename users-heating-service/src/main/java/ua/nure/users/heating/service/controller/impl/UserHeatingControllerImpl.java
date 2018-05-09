package ua.nure.users.heating.service.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.users.heating.service.controller.UserHeatingController;
import ua.nure.users.heating.service.model.UserHeating;
import ua.nure.users.heating.service.service.UserHeatingService;

import java.util.List;

/**
 * The user heating REST-full controller, provide access to heating systems (users heating).
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@RestController
public class UserHeatingControllerImpl implements UserHeatingController {

    private UserHeatingService userHeatingService;

    /**
     * Instantiates new user controller.
     *
     * @param userService the user service.
     */
    @Autowired
    public UserHeatingControllerImpl(UserHeatingService userService) {
        this.userHeatingService = userService;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody UserHeating createUserHeating(@RequestBody UserHeating userHeating) {
        log.debug("UserHeatingControllerImpl#createUserHeating starts");
        UserHeating createdUserHeatingWithGeneratedId = userHeatingService.createUserHeating(userHeating);
        log.info("UserHeatingControllerImpl#createUserHeating created user heating with generated id - " + createdUserHeatingWithGeneratedId);
        log.debug("UserHeatingControllerImpl#createUserHeating finished");
        return createdUserHeatingWithGeneratedId;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody UserHeating findUserHeatingById(@PathVariable("id") long id) {
        log.debug("UserHeatingControllerImpl#findUserHeatingById starts");
        UserHeating foundUserHeating = userHeatingService.findUserHeatingById(id);
        log.info("UserHeatingControllerImpl#findUserHeatingById found user heating - " + foundUserHeating);
        log.debug("UserHeatingControllerImpl#findUserHeatingById finished");
        return foundUserHeating;
    }

    @Override
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<UserHeating> findUsersHeatingByUserId(@PathVariable("userId") long userId) {
        log.debug("UserHeatingControllerImpl#findUsersHeatingByUserId starts");
        List<UserHeating> foundUsersHeating = userHeatingService.findUsersHeatingByUserId(userId);
        log.info("UserHeatingControllerImpl#findUsersHeatingByUserId found users heating - " + foundUsersHeating);
        log.debug("UserHeatingControllerImpl#findUsersHeatingByUserId finished");
        return foundUsersHeating;
    }

    @Override
    @RequestMapping(value = "/heating/{heatingId}", method = RequestMethod.GET)
    public @ResponseBody List<UserHeating> findUsersHeatingByHeatingId(@PathVariable("heatingId") long heatingId) {
        log.debug("UserHeatingControllerImpl#findUsersHeatingByHeatingId starts");
        List<UserHeating> foundUsersHeating = userHeatingService.findUsersHeatingByHeatingId(heatingId);
        log.info("UserHeatingControllerImpl#findUsersHeatingByHeatingId found users heating - " + foundUsersHeating);
        log.debug("UserHeatingControllerImpl#findUsersHeatingByHeatingId finished");
        return foundUsersHeating;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public @ResponseBody UserHeating editUserHeatingById(@RequestBody UserHeating userHeating) {
        log.debug("UserHeatingControllerImpl#editUserHeatingById starts");
        UserHeating editedUser= userHeatingService.editUserHeatingById(userHeating);
        log.info("UserHeatingControllerImpl#editUserHeatingById edited user heating - " + editedUser);
        log.debug("UserHeatingControllerImpl#editUserHeatingById finished");
        return editedUser;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteUserHeatingById(@PathVariable("id") long userHeatingId) {
        log.debug("UserHeatingControllerImpl#deleteUserHeatingById starts");
        userHeatingService.deleteUserHeatingById(userHeatingId);
        log.debug("UserHeatingControllerImpl#deleteUserHeatingById finished");
    }

    @Override
    @RequestMapping(value = "/user/{userId}/heating/{heatingId}", method = RequestMethod.DELETE)
    public void deleteUserHeatingByUserAndHeatingIds(@PathVariable long userId, @PathVariable long heatingId) {
        log.debug("UserHeatingControllerImpl#deleteUserHeatingByUserAndHeatingIds starts");
        userHeatingService.deleteUserHeatingByUserAndHeatingIds(userId, heatingId);
        log.debug("UserHeatingControllerImpl#deleteUserHeatingByUserAndHeatingIds finished");
    }
}
