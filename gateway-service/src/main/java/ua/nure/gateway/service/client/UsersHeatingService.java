package ua.nure.gateway.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.nure.gateway.service.client.impl.fallback.UsersHeatingServiceFallbackImpl;
import ua.nure.gateway.service.model.UserHeating;

import java.util.List;

/**
 * The users heating service, provide access to data via feign client.
 *
 * @author Stanislav_Vorozhka
 */
@FeignClient(value = "users-heating-service", fallback = UsersHeatingServiceFallbackImpl.class)
public interface UsersHeatingService {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody UserHeating createUserHeating(@RequestBody UserHeating userHeating);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody UserHeating findUserHeatingById(@PathVariable("id") long id);

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseBody List<UserHeating> findUsersHeatingByUserId(@PathVariable("userId") long userId);

    @RequestMapping(value = "/heating/{heatingId}", method = RequestMethod.GET)
    @ResponseBody List<UserHeating> findUsersHeatingByHeatingId(@PathVariable("heatingId") long heatingId);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody UserHeating editUserHeatingById(@RequestBody UserHeating userHeating);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody void deleteUserHeatingById(@PathVariable("id") long userHeatingId);

    @RequestMapping(value = "/user/{userId}/heating/{heatingId}", method = RequestMethod.DELETE)
    void deleteUserHeatingByUserAndHeatingIds(@PathVariable("userId") long userId, @PathVariable("heatingId") long heatingId);
}
