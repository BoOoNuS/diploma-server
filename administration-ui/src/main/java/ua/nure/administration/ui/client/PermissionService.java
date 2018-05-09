package ua.nure.administration.ui.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Stanislav_Vorozhka
 */
@RequestMapping("/permission")
@FeignClient(value = "gateway-service")
public interface PermissionService {

    @RequestMapping(value = "/{heatingId}", method = RequestMethod.POST)
    void giveHeatingPermissionsToUsers(@PathVariable("heatingId") long heatingId,
                                       @RequestBody List<Long> userIds);

    @RequestMapping(value = "/{heatingId}", method = RequestMethod.DELETE)
    void takeAwayHeatingPermissionsFromUsers(@PathVariable("heatingId") long heatingId,
                                             @RequestBody List<Long> userIds);
}
