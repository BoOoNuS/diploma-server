package ua.nure.heating.service.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.heating.service.controller.HeatingController;
import ua.nure.heating.service.model.Heating;
import ua.nure.heating.service.service.HeatingService;

import java.util.List;

/**
 * The heating REST-full controller, provide access to heating systems (heating).
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@RestController
public class HeatingControllerImpl implements HeatingController {

    private HeatingService heatingService;

    /**
     * Instantiates new heating controller.
     *
     * @param heatingService the heating service.
     */
    @Autowired
    public HeatingControllerImpl(HeatingService heatingService) {
        this.heatingService = heatingService;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody Heating createHeating(@RequestBody Heating heating) {
        log.debug("HeatingControllerImpl#createHeating starts");
        Heating createdHeatingWithGeneratedId = heatingService.createHeating(heating);
        log.info("HeatingControllerImpl#createHeating created heating with generated id - " + createdHeatingWithGeneratedId);
        log.debug("HeatingControllerImpl#createHeating finished");
        return createdHeatingWithGeneratedId;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Heating findHeatingById(@PathVariable("id") long heatingId) {
        log.debug("HeatingControllerImpl#findHeatingById starts");
        Heating foundHeating = heatingService.findHeatingById(heatingId);
        log.info("HeatingControllerImpl#findHeatingById found heating - " + foundHeating);
        log.debug("HeatingControllerImpl#findHeatingById finished");
        return foundHeating;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public @ResponseBody Heating editHeatingById(@RequestBody Heating heating) {
        log.debug("HeatingControllerImpl#editHeatingById starts");
        Heating editedHeating = heatingService.editHeatingById(heating);
        log.info("HeatingControllerImpl#editHeatingById edited heating - " + editedHeating);
        log.debug("HeatingControllerImpl#editHeatingById finished");
        return editedHeating;
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void deleteHeatingById(@PathVariable("id") long heatingId) {
        log.debug("HeatingControllerImpl#deleteHeatingById starts");
        heatingService.deleteHeatingById(heatingId);
        log.debug("HeatingControllerImpl#deleteHeatingById finished");
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody List<Heating> findAll() {
        log.debug("HeatingControllerImpl#findAll starts");
        List<Heating> heatings = heatingService.findAll();
        log.info("HeatingControllerImpl#findAll heatings - " + heatings);
        log.debug("HeatingControllerImpl#findAll finished");
        return heatings;
    }

    @Override
    @RequestMapping(value = "/description", method = RequestMethod.GET)
    public @ResponseBody List<Heating> findHeatingsByDescription(@RequestParam("description") String description) {
        log.debug("HeatingControllerImpl#findHeatingsByDescriptionContaining starts");
        log.info("HeatingControllerImpl#findHeatingsByDescriptionContaining description - " + description);
        List<Heating> heatings = heatingService.findHeatingsByDescription(description);
        log.info("HeatingControllerImpl#findHeatingsByDescriptionContaining heatings - " + heatings);
        log.debug("HeatingControllerImpl#findHeatingsByDescriptionContaining finished");
        return heatings;
    }
}
