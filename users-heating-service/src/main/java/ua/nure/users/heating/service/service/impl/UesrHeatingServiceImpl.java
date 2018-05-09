package ua.nure.users.heating.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.users.heating.service.dao.UserHeatingDAO;
import ua.nure.users.heating.service.model.UserHeating;
import ua.nure.users.heating.service.service.UserHeatingService;

import java.util.List;

/**
 * The users heating service, provide access to data,
 * and transaction level.
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@Service
public class UesrHeatingServiceImpl implements UserHeatingService {

    private UserHeatingDAO userHeatingDAO;

    /**
     * Instantiates new users heating service.
     *
     * @param userDAO the user DAO
     */
    @Autowired
    public UesrHeatingServiceImpl(UserHeatingDAO userDAO) {
        this.userHeatingDAO = userDAO;
    }

    @Override
    @Transactional
    public UserHeating createUserHeating(UserHeating userHeating) {
        return userHeatingDAO.save(userHeating);
    }

    @Override
    public UserHeating findUserHeatingById(long id) {
        return userHeatingDAO.findOne(id);
    }

    @Override
    public List<UserHeating> findUsersHeatingByUserId(long userId) {
        return userHeatingDAO.findAllByUserId(userId);
    }

    @Override
    public List<UserHeating> findUsersHeatingByHeatingId(long heatingId) {
        return userHeatingDAO.findAllByHeatingId(heatingId);
    }

    @Override
    @Transactional
    public UserHeating editUserHeatingById(UserHeating userHeating) {
        if (userHeating.getId() == 0 || !userHeatingDAO.exists(userHeating.getId())) {
            throw new IllegalArgumentException("Users heating does not contain id - " + userHeating.getId());
        }
        return userHeatingDAO.save(userHeating);
    }

    @Override
    @Transactional
    public void deleteUserHeatingById(long userHeatingId) {
        userHeatingDAO.delete(userHeatingId);
    }

    @Override
    @Transactional
    public void deleteUserHeatingByUserAndHeatingIds(long userId, long heatingId) {
        userHeatingDAO.deleteByUserIdAndHeatingId(userId, heatingId);
    }
}
