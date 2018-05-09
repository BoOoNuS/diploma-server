package ua.nure.gateway.service.client.impl.fallback;

import org.springframework.stereotype.Service;
import ua.nure.gateway.service.client.UsersHeatingService;
import ua.nure.gateway.service.model.UserHeating;

import java.util.Collections;
import java.util.List;

/**
 * The users heating service, provide access to stubbing data, when users-heating-server fallen.
 *
 * @author Stanislav_Vorozhka
 */
@Service
public class UsersHeatingServiceFallbackImpl implements UsersHeatingService {

    private UserHeating stubbedUserHeating = new UserHeating(0, 0);

    @Override
    public UserHeating createUserHeating(UserHeating userHeating) {
        return stubbedUserHeating;
    }

    @Override
    public UserHeating findUserHeatingById(long id) {
        return stubbedUserHeating;
    }

    @Override
    public List<UserHeating> findUsersHeatingByUserId(long userId) {
        return Collections.emptyList();
    }

    @Override
    public List<UserHeating> findUsersHeatingByHeatingId(long heatingId) {
        return Collections.emptyList();
    }

    @Override
    public UserHeating editUserHeatingById(UserHeating userHeating) {
        return stubbedUserHeating;
    }

    @Override
    public void deleteUserHeatingById(long userHeatingId) {
        // do nothing
    }

    @Override
    public void deleteUserHeatingByUserAndHeatingIds(long userId, long heatingId) {
        // do nothing
    }
}
