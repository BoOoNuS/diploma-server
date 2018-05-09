package ua.nure.heating.emulator.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.heating.emulator.model.HeatingConfiguration;
import ua.nure.heating.emulator.model.HeatingMachineEmulator;
import ua.nure.heating.emulator.service.HeatingService;

/**
 * @author Stanislav_Vorozhka
 */
@Service
public class HeatingServiceImpl implements HeatingService {

    private HeatingMachineEmulator heatingMachineEmulator;

    /**
     * Instantiates new heating service.
     *
     * @param heatingMachineEmulator the heating machine emulator
     */
    @Autowired
    public HeatingServiceImpl(HeatingMachineEmulator heatingMachineEmulator) {
        this.heatingMachineEmulator = heatingMachineEmulator;
    }

    @Override
    public void reConfigHeating(HeatingConfiguration heatingConfiguration) {
        BeanUtils.copyProperties(heatingConfiguration, heatingMachineEmulator);
    }

    @Override
    public HeatingConfiguration getHeatingConfiguration() {
        return new HeatingConfiguration(heatingMachineEmulator.getAirTemperature(),
                heatingMachineEmulator.getAirHumidity(),
                heatingMachineEmulator.getAirExchange());
    }
}
