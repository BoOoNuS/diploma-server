package ua.nure.administration.ui.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.nure.administration.ui.model.User;

import java.util.List;

/**
 * @author Stanislav_Vorozhka
 */
@RequestMapping("/user")
@FeignClient(value = "gateway-service")
public interface UserService {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody User createUser(@RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody User findUserById(@PathVariable("id") long id);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody List<User> findUsers();

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    @ResponseBody User findUserByLoginAndPassword(@RequestParam("login") String login, @RequestParam("password") String password);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody User editUserById(@RequestBody User user);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody void deleteUserById(@PathVariable("id") long userId);

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    List<User> getUsersByFullName(@RequestParam("fullName") String fullName);
}
