package ua.nure.gateway.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The user model.
 *
 * @author Stanislav_Vorozhka
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private long id;
    @NonNull
    @NotNull
    @Size(min = 4, max = 36, message = "Login size should be from 4 to 36 count of symbols")
    private String login;
    @NonNull
    @NotNull
    @Size(min = 4, max = 36, message = "Password size should be from 4 to 36 count of symbols")
    private String password;
    @NonNull
    @NotNull
    @Size(min = 2, max = 36, message = "Full name size should be from 2 to 36 count of symbols")
    private String fullName;
    @NonNull
    private UserRole role;
    private boolean blocked;
}