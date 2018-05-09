package ua.nure.gateway.service.model;

import org.junit.Before;
import org.junit.Test;
import ua.nure.gateway.service.validation.impl.ValidatorImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidationTest {

    private Validator validator;
    private ua.nure.gateway.service.validation.Validator customValidator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        customValidator = new ValidatorImpl(validator);
    }

    @Test(expected = ValidationException.class)
    public void validatorTest() {
        Heating heating = new Heating();
        heating.setHost("");
        heating.setPort(0);
        customValidator.validate(heating);
    }

    @Test
    public void heatingValidationTest() {
        Heating heating = new Heating();
        heating.setHost("");
        heating.setPort(0);
        Set<ConstraintViolation<Heating>> violations = validator.validate(heating);
        assertEquals(3, violations.size());
    }

    @Test
    public void heatingValidationTest1() {
        Heating heating = new Heating();
        heating.setDescription("Valid description");
        heating.setHost("ValidHost");
        heating.setPort(1024);
        Set<ConstraintViolation<Heating>> violations = validator.validate(heating);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void heatingConfigTest() {
        HeatingConfiguration heatingConfiguration = new HeatingConfiguration();
        heatingConfiguration.setAirTemperature(9.99f);
        heatingConfiguration.setAirHumidity(101.99f);
        heatingConfiguration.setAirExchange((byte)101);
        Set<ConstraintViolation<HeatingConfiguration>> violations = validator.validate(heatingConfiguration);
        assertEquals(3, violations.size());
    }

    @Test
    public void heatingConfigTest1() {
        HeatingConfiguration heatingConfiguration = new HeatingConfiguration();
        heatingConfiguration.setAirTemperature(10.99f);
        heatingConfiguration.setAirHumidity(100.99f);
        heatingConfiguration.setAirExchange((byte)100);
        Set<ConstraintViolation<HeatingConfiguration>> violations = validator.validate(heatingConfiguration);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void heatingUsersInfoTest() {
        Heating heating = new Heating();
        heating.setHost("");
        heating.setPort(0);
        HeatingConfiguration heatingConfiguration = new HeatingConfiguration();
        heatingConfiguration.setAirTemperature(9.99f);
        heatingConfiguration.setAirHumidity(101.99f);
        heatingConfiguration.setAirExchange((byte)101);
        User user = new User();
        user.setFullName("");
        user.setLogin("");
        user.setPassword("");
        List<User> users = Collections.singletonList(user);
        HeatingUsersInfo heatingUsersInfo = new HeatingUsersInfo();
        heatingUsersInfo.setHeating(heating);
        heatingUsersInfo.setHeatingConfiguration(heatingConfiguration);
        heatingUsersInfo.setUsers(users);
        Set<ConstraintViolation<HeatingUsersInfo>> violations = validator.validate(heatingUsersInfo);
        assertEquals(9, violations.size());
    }

    @Test
    public void heatingUsersInfoTest1() {
        Heating heating = new Heating();
        heating.setDescription("Valid description");
        heating.setHost("ValidHost");
        heating.setPort(1024);
        HeatingConfiguration heatingConfiguration = new HeatingConfiguration();
        heatingConfiguration.setAirTemperature(10.99f);
        heatingConfiguration.setAirHumidity(100.99f);
        heatingConfiguration.setAirExchange((byte)100);
        User user = new User();
        user.setFullName("Valid Full Name");
        user.setLogin("Valid Login");
        user.setPassword("Valid password");
        List<User> users = Collections.singletonList(user);
        HeatingUsersInfo heatingUsersInfo = new HeatingUsersInfo();
        heatingUsersInfo.setHeating(heating);
        heatingUsersInfo.setHeatingConfiguration(heatingConfiguration);
        heatingUsersInfo.setUsers(users);
        Set<ConstraintViolation<HeatingUsersInfo>> violations = validator.validate(heatingUsersInfo);
        assertTrue(violations.isEmpty());
    }
}
