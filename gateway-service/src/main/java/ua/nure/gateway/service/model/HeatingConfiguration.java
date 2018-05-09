package ua.nure.gateway.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeatingConfiguration {

    @Range(min = 10, max = 40, message = "Temperature range should be from 10 to 40 degrees of Celsius")
    private float airTemperature;
    @Range(min = 0, max = 100, message = "Humidity should be from 0 to 100 percent")
    private float airHumidity;
    @Range(min = 0, max = 100, message = "Exchange should be from 0 to 100 percent")
    private byte airExchange;
}
