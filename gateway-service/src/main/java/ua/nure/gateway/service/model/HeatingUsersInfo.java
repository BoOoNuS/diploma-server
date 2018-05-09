package ua.nure.gateway.service.model;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeatingUsersInfo {

    @Valid
    private Heating heating;
    @Valid
    private HeatingConfiguration heatingConfiguration;
    @Valid
    private List<User> users;

    /**
     * Instantiates new heating users info.
     *
     * @param heating              the heating
     * @param heatingConfiguration the heating configuration
     */
    public HeatingUsersInfo(Heating heating, @Nullable HeatingConfiguration heatingConfiguration) {
        this.heating = heating;
        this.heatingConfiguration = heatingConfiguration;
    }
}
