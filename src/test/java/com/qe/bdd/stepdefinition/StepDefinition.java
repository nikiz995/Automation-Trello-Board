package com.qe.bdd.stepdefinition;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qe.bdd.context.BddProperties;

public class StepDefinition {


    protected WebDriver driver;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BddProperties bddProperties;

    @Autowired
    private Environment environment;

    @Autowired
    public Setup setup;

    public TestRestTemplate getRestTemplate() {
        return testRestTemplate;
    }

    public BddProperties getBddProperties() {
        return bddProperties;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false).configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    }



}
