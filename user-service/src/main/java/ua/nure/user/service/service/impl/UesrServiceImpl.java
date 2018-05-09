package ua.nure.user.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.user.service.dao.UserDAO;
import ua.nure.user.service.model.User;
import ua.nure.user.service.service.UserService;

import java.util.List;

/**
 * The user service, provide access to data,
 * and transaction level.
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@Service
public class UesrServiceImpl implements UserService {

    private UserDAO userDAO;

    /**
     * Instantiates new user service.
     *
     * @param userDAO the user DAO
     */
    @Autowired
    public UesrServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findUserById(long id) {
        return userDAO.findOne(id);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userDAO.findAll();
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return userDAO.findUserByLoginAndPassword(login, password);
    }

    @Override
    @Transactional
    public User editUserById(User user) {
        if (user.getId() == 0 || !userDAO.exists(user.getId())) {
            throw new IllegalArgumentException("User does not contain id - " + user.getId());
        }
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(long userId) {
        userDAO.delete(userId);
    }

    @Override
    public List<User> getUsersByFullName(String fullName) {
        return userDAO.findUsersByFullNameContaining(fullName);
    }
}
