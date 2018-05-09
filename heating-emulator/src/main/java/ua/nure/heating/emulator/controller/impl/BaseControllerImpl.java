package ua.nure.heating.emulator.controller.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.heating.emulator.controller.BaseController;
import ua.nure.heating.emulator.model.HeatingConfiguration;
import ua.nure.heating.emulator.service.HeatingService;

/**
 * @author Stanislav_Vorozhka
 */
@Slf4j
@RestController
public class BaseControllerImpl implements BaseController {

    private HeatingService heatingService;

    /**
     * Instantiates new base controller.
     *
     * @param heatingService the heating service
     */
    @Autowired
    public BaseControllerImpl(HeatingService heatingService) {
        this.heatingService = heatingService;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public void reConfigHeating(@RequestBody HeatingConfiguration heatingConfiguration) {
        log.debug("BaseControllerImpl#reConfigHeating starts");
        log.info("BaseControllerImpl#reConfigHeating heating configuration - " + heatingConfiguration);
        heatingService.reConfigHeating(heatingConfiguration);
        log.debug("BaseControllerImpl#reConfigHeating finished");
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody HeatingConfiguration getHeatingConfiguration() {
        log.debug("BaseControllerImpl#getHeatingConfiguration starts");
        HeatingConfiguration heatingConfiguration = heatingService.getHeatingConfiguration();
        log.info("BaseControllerImpl#getHeatingConfiguration current heating configuration - " + heatingConfiguration);
        log.debug("BaseControllerImpl#getHeatingConfiguration finished");
        return heatingConfiguration;
    }
}
