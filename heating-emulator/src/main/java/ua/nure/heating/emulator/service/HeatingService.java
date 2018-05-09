package ua.nure.heating.emulator.service;

import ua.nure.heating.emulator.model.HeatingConfiguration;

/**
 * @author Stanislav_Vorozhka
 */
public interface HeatingService {

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
