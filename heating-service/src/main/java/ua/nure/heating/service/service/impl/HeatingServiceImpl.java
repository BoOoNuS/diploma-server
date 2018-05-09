package ua.nure.heating.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ua.nure.heating.service.dao.HeatingDAO;
import ua.nure.heating.service.model.Heating;
import ua.nure.heating.service.service.HeatingService;

/**
 * The heating service, provide access to data,
 * and transaction level.
 *
 * @author Stanislav_Vorozhka
 */
@Slf4j
@Service
public class HeatingServiceImpl implements HeatingService {

    private HeatingDAO heatingDAO;

    /**
     * Instantiates new heating service.
     *
     * @param heatingDAO the heating DAO
     */
    @Autowired
    public HeatingServiceImpl(HeatingDAO heatingDAO) {
        this.heatingDAO = heatingDAO;
    }

    @Override
    @Transactional
    public Heating createHeating(Heating heating) {
        return heatingDAO.save(heating);
    }

    @Override
    public Heating findHeatingById(long heatingId) {
        return heatingDAO.findOne(heatingId);
    }

    @Override
    @Transactional
    public Heating editHeatingById(Heating heating) {
        if (heating.getId() == 0 || !heatingDAO.exists(heating.getId())) {
            throw new IllegalArgumentException("Heating does not contain id - " + heating.getId());
        }
        return heatingDAO.save(heating);
    }

    @Override
    @Transactional
    public void deleteHeatingById(long heatingId) {
        heatingDAO.delete(heatingId);
    }

    @Override
    public List<Heating> findAll() {
        return (List<Heating>) heatingDAO.findAll();
    }

    @Override
    public List<Heating> findHeatingsByDescription(String description) {
        return heatingDAO.findHeatingsByDescriptionContaining(description);
    }
}
