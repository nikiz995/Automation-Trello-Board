package com.qe.bdd.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    public static WebDriver driver;

    public static WebDriver invokeDriver(final String browserName) throws Exception {

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            default:
        }
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public void clickHiddenElement(WebElement elem) {
        try {
            elem.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickByJS(WebElement elem) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click = true;", elem);
        } catch (Exception e) {
            System.out.println("For Element:" + elem + "\nException captured: " + e.toString());
        }

    }


}
