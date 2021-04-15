package com.qe.bdd.stepdefinition;

import com.qe.bdd.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Setup {

    public Scenario scenario;
    private final Logger logger = LoggerFactory.getLogger(Setup.class);

    @Before
    public void setScenario(Scenario scenario) throws Exception {
        this.scenario = scenario;
    }

    @AfterStep("@ui")
    public void takeScreenshoot() throws Exception {
        attachScreenshot(WebDriverFactory.driver, getScenario());
    }

    @After("@ui")
    public void closeDriver(){
        WebDriverFactory.driver.close();
    }

    public void attachScreenshot(WebDriver driver, Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) WebDriverFactory.driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());

    }

    public Scenario getScenario() {
        return scenario;
    }


  /*  public String getReportConfigPath(){
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");

*/

    }
