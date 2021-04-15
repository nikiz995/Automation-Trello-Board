package com.qe.bdd.context;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


@CucumberContextConfiguration
@ContextConfiguration(classes = {BddConfig.class}, initializers = {ConfigFileApplicationContextInitializer.class} )
@TestPropertySource(properties = {"spring.config.location=classpath:bdd-test.yml"})
@EnableConfigurationProperties(BddProperties.class)
public class InitializeContext {

}
