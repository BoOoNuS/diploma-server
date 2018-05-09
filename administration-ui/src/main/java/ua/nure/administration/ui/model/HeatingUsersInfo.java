package ua.nure.administration.ui.model;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeatingUsersInfo {

    private Heating heating;
    private HeatingConfiguration heatingConfiguration;
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
