package ua.nure.user.service.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import ua.nure.user.service.model.User;

/**
 * Provide access to user table.
 *
 * @author Stanislav_Vorozhka
 */
public interface UserDAO extends CrudRepository<User, Long> {

    /**
     * Gets user by login and password.
     *
     * @param login    the user login (unique)
     * @param password the user account password
     * @return the found user
     */
    User findUserByLoginAndPassword(String login, String password);

    /**
     * Gets users by full name.
     *
     * @param  fullName the full name
     * @return the users found by full name
     */
    List<User> findUsersByFullNameContaining(String fullName);
}
