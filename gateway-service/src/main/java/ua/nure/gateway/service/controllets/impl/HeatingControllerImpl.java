package ua.nure.gateway.service.controllets.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import ua.nure.gateway.service.client.HeatingService;
import ua.nure.gateway.service.client.UserService;
import ua.nure.gateway.service.client.UsersHeatingService;
import ua.nure.gateway.service.controllets.HeatingController;
import ua.nure.gateway.service.model.Heating;
import ua.nure.gateway.service.model.HeatingConfiguration;
import ua.nure.gateway.service.model.HeatingUsersInfo;
import ua.nure.gateway.service.model.User;
import ua.nure.gateway.service.model.UserHeating;
import ua.nure.gateway.service.validation.Validator;

/**
 * @author Stanislav_Vorozhka
 */
@Slf4j
@RestController
@RequestMapping("/heating")
public class HeatingControllerImpl implements HeatingController {

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().
        setConnectTimeout(100).
        setConnectionRequestTimeout(100).
        setSocketTimeout(100).build();
    private static final HttpClient CLIENT = new HttpClient();

    private UserService userService;
    private HeatingService heatingService;
    private UsersHeatingService usersHeatingService;
    private ObjectMapper objectMapper;
    private Validator validator;

    /**
     * Instantiates new heating controller.
     *
     * @param userService         the user service
     * @param heatingService      the heating service
     * @param usersHeatingService the users heating service
     * @param objectMapper        the object mapper
     * @param validator           the validator
     */
    @Autowired
    public HeatingControllerImpl(UserService userService, HeatingService heatingService,
                                 UsersHeatingService usersHeatingService,
                                 ObjectMapper objectMapper, Validator validator) {
        this.userService = userService;
        this.heatingService = heatingService;
        this.usersHeatingService = usersHeatingService;
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @Override
    @RequestMapping(value = "/related/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Heating> findUserHeatings(@PathVariable long userId) {
        log.debug("HeatingControllerImpl#findUserHeatings starts");
        List<UserHeating> usersHeating = usersHeatingService.findUsersHeatingByUserId(userId);
        log.info("HeatingControllerImpl#findUserHeatings users heating by user id - " + usersHeating);
        List<Heating> heatings = usersHeating.stream().map(toHeating()).collect(Collectors.toList());
        log.info("HeatingControllerImpl#findUserHeatings user heatings - " + heatings);
        log.debug("HeatingControllerImpl#findUserHeatings finished");
        return heatings;
    }

    @Override
    @RequestMapping(value = "/user/{userId}/heating/{heatingId}", method = RequestMethod.PUT)
    public void reConfigHeating(@PathVariable long userId,
                                @PathVariable long heatingId,
                                @RequestBody HeatingConfiguration heatingConfiguration) {
        log.debug("HeatingControllerImpl#reConfigHeating starts");
        log.info("HeatingControllerImpl#reConfigHeating request body: userId - " +
                userId + ", heatingId - " +
                heatingId + ", heatingId config - " +
                heatingConfiguration);

        validator.validate(heatingConfiguration);
        boolean userHasAccessToTheHeating = usersHeatingService.findUsersHeatingByHeatingId(heatingId)
                .stream()
                .anyMatch(userHeating -> userHeating.getUserId() == userId);
        log.info("HeatingControllerImpl#reConfigHeating user has access to the heating - " + userHasAccessToTheHeating);
        if (!userHasAccessToTheHeating) {
            throw new IllegalArgumentException("User has no access to the heating or user with the id " + userId + " does not exist");
        }

        Heating heating = heatingService.findHeatingById(heatingId);
        log.info("HeatingControllerImpl#reConfigHeating user heating by heating id - " + heating);
        PutMethod putMethod = new PutMethod("http://" + heating.getHost() + ":" + heating.getPort());
        try {
            putMethod.setRequestEntity(new StringRequestEntity(
                objectMapper.writeValueAsString(heatingConfiguration),
                "application/json",
                "UTF-8"));
            CLIENT.executeMethod(putMethod);
        } catch (IOException e) {
            log.warn("HeatingControllerImpl#reConfigHeating cannot connect to emulator", e);
        }
        log.debug("HeatingControllerImpl#reConfigHeating finished");
    }

    @Override
    @RequestMapping(value = "/user/{userId}/heating/{heatingId}", method = RequestMethod.GET)
    public @ResponseBody HeatingConfiguration getHeatingConfiguration(@PathVariable long userId,
                                                                      @PathVariable long heatingId) {
        log.debug("HeatingControllerImpl#getHeatingConfiguration starts");
        log.info("HeatingControllerImpl#getHeatingConfiguration request body: userId - " +
                userId + ", heatingId - " +
                heatingId);

        boolean userHasAccessToTheHeating = usersHeatingService.findUsersHeatingByHeatingId(heatingId)
                .stream()
                .anyMatch(userHeating -> userHeating.getUserId() == userId);
        log.info("HeatingControllerImpl#getHeatingConfiguration user has access to the heating - " + userHasAccessToTheHeating);
        if (!userHasAccessToTheHeating) {
            throw new IllegalArgumentException("User has no access to the heating or user with the id " + userId + " does not exist");
        }

        Heating heating = heatingService.findHeatingById(heatingId);
        log.info("HeatingControllerImpl#getHeatingConfiguration user heating by heating id - " + heating);
        GetMethod getMethod = new GetMethod("http://" + heating.getHost() + ":" + heating.getPort());
        HeatingConfiguration heatingConfiguration = null;
        try {
            CLIENT.executeMethod(getMethod);
            heatingConfiguration =
                    objectMapper.readValue(getMethod.getResponseBodyAsString(), HeatingConfiguration.class);
        } catch (IOException e) {
            log.warn("HeatingControllerImpl#getHeatingConfiguration cannot connect to emulator", e);
        }
        log.info("HeatingControllerImpl#getHeatingConfiguration current heating configuration - " + heatingConfiguration);
        log.debug("HeatingControllerImpl#getHeatingConfiguration finished");
        return heatingConfiguration;
    }

    @Override
    @RequestMapping(value = "/users/info/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<HeatingUsersInfo> getHeatingInfoWithoutAssignedUsersByUserId(@PathVariable long userId) {
        log.debug("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId starts");
        log.info("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId user id - " + userId);
        List<UserHeating> usersHeating = usersHeatingService.findUsersHeatingByUserId(userId);
        log.info("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId users heating - " + usersHeating);
        List<Heating> heatings = usersHeating.stream().map(toHeating()).collect(Collectors.toList());
        log.info("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId heatings - " + heatings);
        List<HeatingUsersInfo> infoList = new ArrayList<>();

        for (Heating heating : heatings) {
            GetMethod getMethod = new GetMethod("http://" + heating.getHost() + ":" + heating.getPort());
            HeatingConfiguration heatingConfiguration = null;
            try {
                CLIENT.executeMethod(getMethod);
                heatingConfiguration =
                    objectMapper.readValue(getMethod.getResponseBodyAsString(), HeatingConfiguration.class);
            } catch (IOException e) {
                log.warn("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId cannot connect to emulator", e);
            }
            infoList.add(new HeatingUsersInfo(heating, heatingConfiguration));
        }
        log.info("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId user heating completed info - " + infoList);
        log.debug("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId finished");
        return infoList;
    }

    @Override
    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    public @ResponseBody List<HeatingUsersInfo> getAllHeatingUsersInfo() {
        log.debug("HeatingControllerImpl#getAllHeatingUsersInfo starts");
        List<Heating> heatings = heatingService.findAll();
        log.info("HeatingControllerImpl#getAllHeatingUsersInfo heatings - " + heatings);
        List<HeatingUsersInfo> infoList = new ArrayList<>();

        for (Heating heating : heatings) {
            List<UserHeating> usersHeating = usersHeatingService.findUsersHeatingByHeatingId(heating.getId());
            log.info("HeatingControllerImpl#getAllHeatingUsersInfo users heating - " + usersHeating);
            List<User> users = usersHeating.stream().map(toUser()).collect(Collectors.toList());
            log.info("HeatingControllerImpl#getAllHeatingUsersInfo users - " + users);
            CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(REQUEST_CONFIG).build();

            HttpGet getMethod = new HttpGet("http://" + heating.getHost() + ":" + heating.getPort());
            HeatingConfiguration heatingConfiguration = null;
            try {
                CloseableHttpResponse response = client.execute(getMethod);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                response.getEntity().writeTo(os);
                String responseBodyAsString = os.toString();
                heatingConfiguration =
                        objectMapper.readValue(responseBodyAsString, HeatingConfiguration.class);
            } catch (IOException e) {
                log.warn("HeatingControllerImpl#getAllHeatingUsersInfo cannot connect to emulator", e);
            } finally {
                close(client);
            }
            infoList.add(new HeatingUsersInfo(heating, heatingConfiguration, users));
        }
        log.info("HeatingControllerImpl#getAllHeatingUsersInfo user heating completed info - " + infoList);
        log.debug("HeatingControllerImpl#getAllHeatingUsersInfo finished");
        return infoList;
    }

    private void close(CloseableHttpClient client) {
        try {
            client.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Heating updateHeating(@RequestBody Heating updatedHeating) {
        log.debug("HeatingControllerImpl#updateHeating starts");
        validator.validate(updatedHeating);
        Heating heating = heatingService.editHeatingById(updatedHeating);
        log.info("HeatingControllerImpl#updateHeating updated heating - " + heating);
        log.debug("HeatingControllerImpl#updateHeating finished");
        return heating;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Heating createHeating(@RequestBody Heating heating) {
        log.debug("HeatingControllerImpl#updateHeating starts");
        validator.validate(heating);
        Heating createdHeating = heatingService.createHeating(heating);
        log.info("HeatingControllerImpl#updateHeating created heating - " + createdHeating);
        log.debug("HeatingControllerImpl#updateHeating finished");
        return createdHeating;
    }

    @Override
    @RequestMapping(value = "/{heatingId}", method = RequestMethod.GET)
    public Heating findHeatingById(@PathVariable long heatingId) {
        log.debug("HeatingControllerImpl#findHeating starts");
        Heating foundHeating = heatingService.findHeatingById(heatingId);
        log.info("HeatingControllerImpl#findHeating found heating - " + foundHeating);
        log.debug("HeatingControllerImpl#findHeating finished");
        return foundHeating;
    }

    @Override
    @RequestMapping(value = "/users/info/{id}/description", method = RequestMethod.GET)
    public @ResponseBody List<HeatingUsersInfo> getHeatingUserInfoByUserIdAndDescription(
        @PathVariable(value = "id") long userId, @RequestParam("description") String description) {

        log.debug("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription starts");
        log.info("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription user id - " + userId);
        List<UserHeating> usersHeating = usersHeatingService.findUsersHeatingByUserId(userId);
        log.info("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription users heating - " + usersHeating);
        List<Heating> heatingsByDescription = heatingService.findHeatingsByDescription(description);
        log.info("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription heatings by description - " + heatingsByDescription);
        List<Heating> heatings = usersHeating.stream().map(toHeating()).filter(heatingsByDescription::contains).collect(Collectors.toList());
        log.info("HeatingControllerImpl#getHeatingInfoWithoutAssignedUsersByUserId heatings - " + heatings);
        List<HeatingUsersInfo> infoList = new ArrayList<>();

        for (Heating heating : heatings) {
            GetMethod getMethod = new GetMethod("http://" + heating.getHost() + ":" + heating.getPort());
            HeatingConfiguration heatingConfiguration = null;
            try {
                CLIENT.executeMethod(getMethod);
                heatingConfiguration =
                    objectMapper.readValue(getMethod.getResponseBodyAsString(), HeatingConfiguration.class);
            } catch (IOException e) {
                log.warn("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription cannot connect to emulator", e);
            }
            infoList.add(new HeatingUsersInfo(heating, heatingConfiguration));
        }
        log.info("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription user heating completed info - " + infoList);
        log.debug("HeatingControllerImpl#getHeatingUserInfoByUserIdAndDescription finished");
        return infoList;
    }

    @Override
    @RequestMapping(value = "/users/info/description", method = RequestMethod.GET)
    public @ResponseBody List<HeatingUsersInfo> getHeatingUserInfoByDescription(
        @RequestParam("description") String description) {

        log.debug("HeatingControllerImpl#getHeatingUserInfoByDescription starts");
        List<Heating> heatings = heatingService.findHeatingsByDescription(description);
        log.info("HeatingControllerImpl#getHeatingUserInfoByDescription heatings - " + heatings);
        List<HeatingUsersInfo> infoList = new ArrayList<>();

        for (Heating heating : heatings) {
            List<UserHeating> usersHeating = usersHeatingService.findUsersHeatingByHeatingId(heating.getId());
            log.info("HeatingControllerImpl#getHeatingUserInfoByDescription users heating - " + usersHeating);
            List<User> users = usersHeating.stream().map(toUser()).collect(Collectors.toList());
            log.info("HeatingControllerImpl#getHeatingUserInfoByDescription users - " + users);
            CloseableHttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(REQUEST_CONFIG).build();

            HttpGet getMethod = new HttpGet("http://" + heating.getHost() + ":" + heating.getPort());
            HeatingConfiguration heatingConfiguration = null;
            try {
                CloseableHttpResponse response = client.execute(getMethod);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                response.getEntity().writeTo(os);
                String responseBodyAsString = os.toString();
                heatingConfiguration =
                    objectMapper.readValue(responseBodyAsString, HeatingConfiguration.class);
            } catch (IOException e) {
                log.warn("HeatingControllerImpl#getHeatingUserInfoByDescription cannot connect to emulator", e);
            } finally {
                close(client);
            }
            infoList.add(new HeatingUsersInfo(heating, heatingConfiguration, users));
        }
        log.info("HeatingControllerImpl#getHeatingUserInfoByDescription user heating completed info - " + infoList);
        log.debug("HeatingControllerImpl#getHeatingUserInfoByDescription finished");
        return infoList;
    }

    private Function<UserHeating, User> toUser() {
        return userHeating -> userService.findUserById(userHeating.getUserId());
    }

    private Function<UserHeating, Heating> toHeating() {
        return userHeating -> heatingService.findHeatingById(userHeating.getHeatingId());
    }
}
