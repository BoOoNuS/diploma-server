package ua.nure.gateway.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The heating model.
 *
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Heating {

    private long id;
    @NonNull
    @NotNull
    @NotBlank(message = "Host should not contains white spaces")
    @Size(min = 4, max = 255, message = "Size of host should be from 4 to 255 count of symbols")
    private String host;
    @NonNull
    @NotNull
    @Range(min = 1024, max = 65535, message = "Port number should be from 1024 to 65535")
    private int port;
    @NonNull
    @Size(max = 255, message = "Size of description should be from 0 to 255 count of symbols")
    private String description;
}