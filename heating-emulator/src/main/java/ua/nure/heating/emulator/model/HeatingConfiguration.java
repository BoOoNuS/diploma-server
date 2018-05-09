package ua.nure.heating.emulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeatingConfiguration {

    private float airTemperature;
    private float airHumidity;
    private byte airExchange;
}
