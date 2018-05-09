package ua.nure.administration.ui.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import ua.nure.administration.ui.model.Heating;
import ua.nure.administration.ui.model.HeatingConfiguration;
import ua.nure.administration.ui.model.HeatingUsersInfo;

/**
 * @author Stanislav_Vorozhka
 */
@RequestMapping("/heating")
@FeignClient(value = "gateway-service")
public interface HeatingService {

    @RequestMapping(value = "/related/{userId}", method = RequestMethod.GET)
    @ResponseBody List<Heating> findUserHeatings(@PathVariable("userId") long userId);

    @RequestMapping(value = "/user/{userId}/heating/{heatingId}", method = RequestMethod.PUT)
    void reConfigHeating(@PathVariable("userId") long userId,
                         @PathVariable("heatingId") long heatingId,
                         @RequestBody HeatingConfiguration heatingConfiguration);

    @RequestMapping(value = "/user/{userId}/heating/{heatingId}", method = RequestMethod.GET)
    @ResponseBody HeatingConfiguration getHeatingConfiguration(@PathVariable("userId") long userId,
                                                               @PathVariable("heatingId") long heatingId);

    @RequestMapping(value = "/users/info/{userId}", method = RequestMethod.GET)
    @ResponseBody List<HeatingUsersInfo> getHeatingInfoWithoutAssignedUsersByUserId(@PathVariable("userId") long userId);

    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    @ResponseBody List<HeatingUsersInfo> getAllHeatingUsersInfo();

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody Heating updateHeating(@RequestBody Heating updatedHeating);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody Heating createHeating(@RequestBody Heating heating);

    @RequestMapping(value = "/{heatingId}", method = RequestMethod.GET)
    Heating findHeatingById(@PathVariable("heatingId") long heatingId);

    @RequestMapping(value = "/users/info/{id}/description", method = RequestMethod.GET)
    @ResponseBody List<HeatingUsersInfo> getHeatingUserInfoByUserIdAndDescription(
        @PathVariable(value = "id") long userId, @RequestParam("description") String description);

    @RequestMapping(value = "/users/info/description", method = RequestMethod.GET)
    @ResponseBody List<HeatingUsersInfo> getHeatingUserInfoByDescription(@RequestParam("description") String description);
}
