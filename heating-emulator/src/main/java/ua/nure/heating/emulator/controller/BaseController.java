package ua.nure.heating.emulator.controller;

import ua.nure.heating.emulator.model.HeatingConfiguration;

/**
 * @author Stanislav_Vorozhka
 */
public interface BaseController {

    /**
     * Re-config heating machine emulator with the new configuration.
     *
     * @param heatingConfiguration the new heating configuration
     */
    void reConfigHeating(HeatingConfiguration heatingConfiguration);

    /**
     * Gets current hating machine emulator configuration.
     *
     * @return the current heating configuration
     */
    HeatingConfiguration getHeatingConfiguration();
}
