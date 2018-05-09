package ua.nure.users.heating.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The user heating model.
 *
 * @author Stanislav_Vorozhka
 */
@Data
@Entity
@Table(name = "users_heating")
@NoArgsConstructor
@AllArgsConstructor
public class UserHeating {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "heating_id")
    private long heatingId;
}
