package ua.nure.gateway.service.client.impl.fallback;

import org.springframework.stereotype.Service;
import ua.nure.gateway.service.client.UserService;
import ua.nure.gateway.service.model.User;
import ua.nure.gateway.service.model.UserRole;

import java.util.Collections;
import java.util.List;

/**
 * The user service, provide access to stubbing data, when user-server fallen.
 *
 * @author Stanislav_Vorozhka
 */
@Service
public class UserServiceFallbackImpl implements UserService {

    private User stubbedUser = new User("default", "default", "default", UserRole.USER);

    @Override
    public User createUser(User user) {
        return stubbedUser;
    }

    @Override
    public User findUserById(long id) {
        return stubbedUser;
    }

    @Override
    public List<User> findUsers() {
        return Collections.emptyList();
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return stubbedUser;
    }

    @Override
    public User editUserById(User user) {
        return stubbedUser;
    }

    @Override
    public void deleteUserById(long userId) {
        // do nothing
    }

    @Override
    public List<User> getUsersByFullName(String fullName) {
        return Collections.emptyList();
    }
}
