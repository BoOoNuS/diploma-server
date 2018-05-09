package ua.nure.heating.emulator.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Heating machine emulator (singleton), contains average permissible climatic indexes.
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@ToString
@Component
@EqualsAndHashCode
public class HeatingMachineEmulator {

    private float airTemperature = 21.0f;
    private float airHumidity = 45.0f;
    private byte airExchange = 7;

    public float getAirTemperature() {
        log.info("Current air temperature - " + airTemperature);
        return airTemperature;
    }

    public void setAirTemperature(float airTemperature) {
        log.info("Air temperature sets to - " + airTemperature);
        this.airTemperature = airTemperature;
    }

    public float getAirHumidity() {
        log.info("Current air humidity - " + airHumidity);
        return airHumidity;
    }

    public void setAirHumidity(float airHumidity) {
        log.info("Air humidity sets to - " + airHumidity);
        this.airHumidity = airHumidity;
    }

    public byte getAirExchange() {
        log.info("Current air exchange - " + airExchange);
        return airExchange;
    }

    public void setAirExchange(byte airExchange) {
        log.info("Air exchange sets to - " + airExchange);
        this.airExchange = airExchange;
    }
}
