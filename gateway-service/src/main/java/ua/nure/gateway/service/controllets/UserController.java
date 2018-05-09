package ua.nure.gateway.service.controllets;

import java.util.List;

import ua.nure.gateway.service.model.User;

/**
 * @author Stanislav_Vorozhka
 */
public interface UserController {

    /**
     * Sign in to the system by the user login and password.
     *
     * @param login    the user login
     * @param password the user password
     * @return the authenticated user with full information
     */
    User getUserByLoginAndPassword(String login, String password);

    /**
     * Gets all users.
     *
     * @return the found users
     */
    List<User> findUsers();

    /**
     * Sign up user in the system.
     *
     * @param user the user
     * @return created user with generated id, and default values, if needed
     */
    User createUser(User user);

    /**
     * Updates user by updatedUser#id.
     *
     * @param updatedUser the user with new settings
     */
    void updateUser(User updatedUser);

    /**
     * Gets users by full name.
     *
     * @param  fullName the full name
     * @return the users found by full name
     */
    List<User> getUsersByFullName(String fullName);
}
