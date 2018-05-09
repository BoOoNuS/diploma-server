package ua.nure.user.service.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.user.service.controller.UserController;
import ua.nure.user.service.model.User;
import ua.nure.user.service.service.UserService;

import java.util.List;

/**
 * The user REST-full controller, provide access to heating systems (user).
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@RestController
public class UserControllerImpl implements UserController {

    private UserService userService;

    /**
     * Instantiates new user controller.
     *
     * @param userService the user service.
     */
    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) {
        log.debug("UserControllerImpl#createUser starts");
        User createdUserWithGeneratedId = userService.createUser(user);
        log.info("UserControllerImpl#createUser created user with generated id - " + createdUserWithGeneratedId);
        log.debug("UserControllerImpl#createUser finished");
        return createdUserWithGeneratedId;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody User findUserById(@PathVariable("id") long id) {
        log.debug("UserHeatingControllerImpl#findUserById starts");
        User foundUser = userService.findUserById(id);
        log.info("UserHeatingControllerImpl#findUserById found user - " + foundUser);
        log.debug("UserHeatingControllerImpl#findUserById finished");
        return foundUser;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<User> findUsers() {
        log.debug("UserHeatingControllerImpl#findUsers starts");
        List<User> foundUsers = userService.findAllUsers();
        log.info("UserHeatingControllerImpl#findUsers found users - " + foundUsers);
        log.debug("UserHeatingControllerImpl#findUsers finished");
        return foundUsers;
    }

    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public @ResponseBody User findUserByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
        log.debug("UserControllerImpl#findUserByLoginAndPassword starts");
        User foundUser = userService.findUserByLoginAndPassword(login, password);
        log.info("UserControllerImpl#findUserByLoginAndPassword found user - " + foundUser);
        log.debug("UserControllerImpl#findUserByLoginAndPassword finished");
        return foundUser;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public @ResponseBody User editUserById(@RequestBody User user) {
        log.debug("UserControllerImpl#editUserById starts");
        User editedUser= userService.editUserById(user);
        log.info("UserControllerImpl#editUserById edited user - " + editedUser);
        log.debug("UserControllerImpl#editUserById finished");
        return editedUser;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteUserById(@PathVariable("id") long userId) {
        log.debug("UserControllerImpl#deleteUserById starts");
        userService.deleteUserById(userId);
        log.debug("UserControllerImpl#deleteUserById finished");
    }

    @Override
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public List<User> getUsersByFullName(@RequestParam("fullName") String fullName) {
        log.debug("UserHeatingControllerImpl#getUsersByFullName starts");
        List<User> foundUsers = userService.getUsersByFullName(fullName);
        log.info("UserHeatingControllerImpl#getUsersByFullName found users - " + foundUsers);
        log.debug("UserHeatingControllerImpl#getUsersByFullName finished");
        return foundUsers;
    }
}
