package ua.nure.gateway.service.controllets.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ua.nure.gateway.service.client.UserService;
import ua.nure.gateway.service.controllets.UserController;
import ua.nure.gateway.service.model.User;
import ua.nure.gateway.service.validation.Validator;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    private UserService userService;
    private Validator validator;

    /**
     * Instantiates new user controller.
     *
     * @param userService the user service
     * @param validator the validator
     */
    @Autowired
    public UserControllerImpl(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @Override
    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public @ResponseBody User getUserByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
        log.debug("UserControllerImpl#getUserByLoginAndPassword starts");
        User foundUser = userService.findUserByLoginAndPassword(login, password);
        log.info("UserControllerImpl#getUserByLoginAndPassword found user - " + foundUser);
        log.debug("UserControllerImpl#getUserByLoginAndPassword finished");
        return foundUser;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<User> findUsers() {
        log.debug("UserControllerImpl#findUsers starts");
        List<User> users = userService.findUsers();
        log.info("UserControllerImpl#getUserByLoginAndPassword found users - " + users);
        log.debug("UserControllerImpl#findUsers finished");
        return users;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody User createUser(@RequestBody User user) {
        log.debug("UserControllerImpl#createUser starts");
        validator.validate(user);
        User createdUser = userService.createUser(user);
        log.info("UserControllerImpl#createUser created user - " + createdUser);
        log.debug("UserControllerImpl#createUser finished");
        return createdUser;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User updatedUser) {
        log.debug("UserControllerImpl#createUser starts");
        validator.validate(updatedUser);
        User user = userService.editUserById(updatedUser);
        log.info("UserControllerImpl#createUser updated user - " + user);
        log.debug("UserControllerImpl#createUser finished");
    }

    @Override
    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public List<User> getUsersByFullName(@RequestParam("fullName") String fullName) {
        log.debug("UserControllerImpl#getUsersByFullName starts");
        List<User> users = userService.getUsersByFullName(fullName);
        log.info("UserControllerImpl#getUsersByFullName found users - " + users);
        log.debug("UserControllerImpl#getUsersByFullName finished");
        return users;
    }
}
