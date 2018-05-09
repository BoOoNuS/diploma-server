package ua.nure.gateway.service.client.impl.fallback;

import org.springframework.stereotype.Service;
import ua.nure.gateway.service.client.HeatingService;
import ua.nure.gateway.service.model.Heating;

import java.util.Collections;
import java.util.List;

/**
 * The heating service, provide access to stubbing data, when heating-server fallen.
 *
 * @author Stanislav_Vorozhka
 */
@Service
public class HeatingServiceFallbackImpl implements HeatingService {

    private Heating stubbedHeating = new Heating("default", 0, "default");

    @Override
    public Heating createHeating(Heating heating) {
        return stubbedHeating;
    }

    @Override
    public Heating findHeatingById(long heatingId) {
        return stubbedHeating;
    }

    @Override
    public Heating editHeatingById(Heating heating) {
        return stubbedHeating;
    }

    @Override
    public List<Heating> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Heating> findHeatingsByDescription(String description) {
        return Collections.emptyList();
    }
}
