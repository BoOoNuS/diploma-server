package ua.nure.user.service.service;

import ua.nure.user.service.model.User;

import java.util.List;

/**
 * The user service, provide access to data.
 *
 * @author Stanislav_Vorozhka
 */
public interface UserService {

    /**
     * Creates user.
     *
     * @param user the user
     * @return the created user with generated id
     */
    User createUser(User user);

    /**
     * Gets user by id.
     *
     * @return the found user
     */
    User findUserById(long id);

    /**
     * Gets all users.
     *
     * @return the found users
     */
    List<User> findAllUsers();

    /**
     * Gets user by login and password.
     *
     * @param login    the heating unique field
     * @param password the user account password
     * @return the found user
     */
    User findUserByLoginAndPassword(String login, String password);

    /**
     * Edits user by id.
     *
     * @param user the user with defined id
     * @return the edited user
     * @throws IllegalArgumentException if user does not have id
     */
    User editUserById(User user);

    /**
     * Deletes user by id.
     *
     * @param userId the user identifier
     */
    void deleteUserById(long userId);

    /**
     * Gets users by full name.
     *
     * @param  fullName the full name
     * @return the users found by full name
     */
    List<User> getUsersByFullName(String fullName);
}