package ua.nure.heating.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The heating model.
 *
 * @author Stanislav_Vorozhka
 */
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Heating {

    @Id
    @GeneratedValue
    private long id;
    private String host;
    private int port;
    private String description;
}