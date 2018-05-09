package ua.nure.gateway.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.nure.gateway.service.client.impl.fallback.HeatingServiceFallbackImpl;
import ua.nure.gateway.service.model.Heating;

import java.util.List;

/**
 * The heating service, provide access to data via feign client.
 *
 * @author Stanislav_Vorozhka
 */
@FeignClient(value = "heating-service", fallback = HeatingServiceFallbackImpl.class)
public interface HeatingService {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody Heating createHeating(@RequestBody Heating heating);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody Heating findHeatingById(@PathVariable("id") long heatingId);

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody Heating editHeatingById(@RequestBody Heating heating);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody List<Heating> findAll();

    @RequestMapping(value = "/description", method = RequestMethod.GET)
    @ResponseBody List<Heating> findHeatingsByDescription(@RequestParam("description") String description);
}