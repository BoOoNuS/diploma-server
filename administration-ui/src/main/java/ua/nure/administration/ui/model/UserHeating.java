package ua.nure.administration.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The user heating model.
 *
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserHeating {

    private long id;
    @NonNull
    private long userId;
    @NonNull
    private long heatingId;
}
